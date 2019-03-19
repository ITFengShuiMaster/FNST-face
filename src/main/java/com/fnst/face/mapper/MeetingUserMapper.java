package com.fnst.face.mapper;

import com.fnst.face.entity.MeetingUser;
import com.fnst.face.entity.MeetingUserKey;

import java.util.List;

public interface MeetingUserMapper {
    int deleteByPrimaryKey(MeetingUserKey key);

    int insert(MeetingUser record);

    int insertSelective(MeetingUser record);

    MeetingUser selectByPrimaryKey(MeetingUserKey key);

    int updateByPrimaryKeySelective(MeetingUser record);

    int updateByPrimaryKey(MeetingUser record);

    List<MeetingUser> selectByMeetingId(Integer meetingId);
}