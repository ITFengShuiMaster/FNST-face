package com.fnst.face.service;

import com.fnst.face.VO.UserVO;
import com.fnst.face.VO.face.Face;
import com.fnst.face.common.CommonVar;
import com.fnst.face.common.ResponseCode;
import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.MeetingUser;
import com.fnst.face.entity.User;
import com.fnst.face.mapper.MeetingMapper;
import com.fnst.face.mapper.MeetingUserMapper;
import com.fnst.face.mapper.UserMapper;
import com.fnst.face.util.Base64ImageUtil;
import com.fnst.face.util.FaceCompareUtil;
import com.fnst.face.util.JsonUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Luyue
 * @date 2019/3/18 17:04
 **/
@Service
public class UserMeetingService {
    private static final Logger log = LoggerFactory.getLogger(UserMeetingService.class);

    @Value("${img.path}")
    private String imgPath;

    private MeetingUserMapper meetingUserMapper;
    private UserMapper userMapper;
    private MeetingMapper meetingMapper;
    private FileService fileService;

    @Autowired
    public UserMeetingService(MeetingUserMapper meetingUserMapper, UserMapper userMapper, MeetingMapper meetingMapper, FileService fileService) {
        this.meetingUserMapper = meetingUserMapper;
        this.userMapper = userMapper;
        this.meetingMapper = meetingMapper;
        this.fileService = fileService;
    }

    private UserVO initUserVo(MeetingUser mUser) {
        UserVO userVO = new UserVO();

        User user = getUser(mUser.getUserId());
        userVO.setUser(user);
        userVO.setMeetingUser(mUser);

        return userVO;
    }

    public ServerResponse listMeetingUser(Long meetingId) {
        if (meetingId == null) {
            return ServerResponse.failure(ResponseCode.PARAM_IS_BLANK);
        }

        if (meetingMapper.selectByPrimaryKey(meetingId) == null) {
            return ServerResponse.failure("没有该场会议");
        }

        List<MeetingUser> lists = meetingUserMapper.selectByMeetingId(meetingId);
        return ServerResponse.success(listRealUser(lists));
    }

    private List<UserVO> listRealUser(List<MeetingUser> lists) {
        List<UserVO> userVOS = Lists.newArrayList();
        for (MeetingUser mUser : lists) {
            UserVO userVO = initUserVo(mUser);
            userVOS.add(userVO);
        }
        return userVOS;
    }

    private User getUser(Long id) {
        if (id == null) {
            return null;
        }

        return userMapper.selectByPrimaryKey(id);
    }

    public ServerResponse insertUserInMeeting(MeetingUser meetingUser) {
        if (userMapper.selectByPrimaryKey(meetingUser.getUserId()) == null) {
            return ServerResponse.failure(ResponseCode.USER_NOT_EXIST);
        }

        if (meetingMapper.selectByPrimaryKey(meetingUser.getMeetingId()) == null) {
            return ServerResponse.failure("没有该场会议");
        }

        meetingUser.setCreateTime(new Date());
        meetingUser.setUpdateTime(new Date());
        meetingUser.setIsAttend(false);
        meetingUser.setIsVisited(false);
        meetingUser.setImgUrl("");
        try {
            if (meetingUserMapper.insert(meetingUser) <= 0) {
                return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
            }
        } catch (Exception e) {
            log.error("{} is error", e);
            return ServerResponse.failure(ResponseCode.RESULE_DATA_REPEAT);
        }
        return ServerResponse.success();
    }

    public ServerResponse signIn(String path, String imgBase64_2, Long meetingId) {
        List<User> users = userMapper.selectAll();
        List<MeetingUser> lists = meetingUserMapper.selectByMeetingId(meetingId);
        List<UserVO> userVOS = listRealUser(lists);

        return checkUser(path, imgBase64_2, userVOS, users);
    }

    private ServerResponse checkUser(String path, String imgBase64_2, List<UserVO> userVOS, List<User> users) {
        Double confidence = null;
        UserVO realUserVO = new UserVO();

        for (User user : users) {
            String imgBase64_1 = Base64ImageUtil.imageToBase64ByOnline(user.getImgUrl());
            String resJson = FaceCompareUtil.compare(imgBase64_1, imgBase64_2);
            Face face = JsonUtil.json2Object(resJson, Face.class);

            if (face.getConfidence() == null) {
                return ServerResponse.failure(ResponseCode.FACE_ERROR);
            }

            if (confidence == null) {
                confidence = face.getConfidence();
                realUserVO.setUser(user);
            } else {
                if (confidence.compareTo(face.getConfidence()) < 0) {
                    confidence = face.getConfidence();
                    realUserVO.setUser(user);
                }
            }
        }

        if (confidence == null || confidence < CommonVar.CONFIDENCE_RES) {
            return ServerResponse.failure(ResponseCode.FACE_NONE_IN_FNST);
        }

        // 判断是否是访客
        for (UserVO userVO : userVOS) {
            if (userVO.getUser().getJobNumber().equals(realUserVO.getUser().getJobNumber()) && !userVO.getMeetingUser().getIsAttend()) {
                userVO.getMeetingUser().setIsAttend(true);
                String fileName = fileService.saveImgFile(imgBase64_2, path);
                if (!StringUtils.isBlank(fileName)) {
                    userVO.getMeetingUser().setImgUrl(imgPath + fileName);
                }
                realUserVO.setMeetingUser(userVO.getMeetingUser());
                meetingUserMapper.updateByPrimaryKeySelective(userVO.getMeetingUser());
            } else if (!userVO.getUser().getJobNumber().equals(realUserVO.getUser().getJobNumber())){
                MeetingUser meetingUser = initMeetingUser(userVO.getMeetingUser().getMeetingId(), realUserVO.getUser().getId());
                String fileName = fileService.saveImgFile(path, imgBase64_2);
                if (!StringUtils.isBlank(fileName)) {
                    meetingUser.setImgUrl(imgPath + fileName);
                }
                realUserVO.setMeetingUser(meetingUser);
                meetingUserMapper.insert(meetingUser);
            }
        }

        return ServerResponse.success(realUserVO);
    }

    private MeetingUser initMeetingUser(Long meetingId, Long userId) {
        MeetingUser meetingUser = new MeetingUser();
        meetingUser.setIsAttend(false);
        meetingUser.setIsVisited(true);
        meetingUser.setImgUrl("");
        meetingUser.setUpdateTime(new Date());
        meetingUser.setCreateTime(new Date());
        meetingUser.setMeetingId(meetingId);
        meetingUser.setUserId(userId);
        return meetingUser;
    }
}
