package com.fnst.face.service;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.MeetingUser;
import com.fnst.face.mapper.MeetingUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Luyue
 * @date 2019/3/18 17:04
 **/
@Service
public class UserMeetingService {

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    public ServerResponse listMeetingUser(Integer meetingId) {
        List<MeetingUser> lists = meetingUserMapper.selectByMeetingId(meetingId);
        // TODO 返回具体人员信息
        return ServerResponse.success(lists);
    }
}
