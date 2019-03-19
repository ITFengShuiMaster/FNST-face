package com.fnst.face.service;

import com.fnst.face.VO.UserVO;
import com.fnst.face.common.ResponseCode;
import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.MeetingUser;
import com.fnst.face.entity.User;
import com.fnst.face.mapper.MeetingMapper;
import com.fnst.face.mapper.MeetingUserMapper;
import com.fnst.face.mapper.UserMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private MeetingUserMapper meetingUserMapper;
    private UserMapper userMapper;
    private MeetingMapper meetingMapper;

    @Autowired
    public UserMeetingService(MeetingUserMapper meetingUserMapper, UserMapper userMapper, MeetingMapper meetingMapper) {
        this.meetingUserMapper = meetingUserMapper;
        this.userMapper = userMapper;
        this.meetingMapper = meetingMapper;
    }

    private UserVO initUserVo(MeetingUser mUser) {
        UserVO userVO = new UserVO();

        User user = getUser(mUser.getUserId());
        userVO.setUser(user);
        userVO.setOnLineImgUrl(mUser.getImgUrl());
        userVO.setIsAttend(mUser.getIsAttend());
        userVO.setIsVisited(mUser.getIsVisited());
        userVO.setCreateTime(mUser.getCreateTime());
        userVO.setUpdateTime(mUser.getUpdateTime());

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
}
