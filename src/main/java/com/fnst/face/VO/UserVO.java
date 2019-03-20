package com.fnst.face.VO;

import com.fnst.face.entity.MeetingUser;
import com.fnst.face.entity.User;

import java.util.Date;

/**
 * @author Luyue
 * @date 2019/3/19 10:58
 **/
public class UserVO {

    private User user;
    private MeetingUser meetingUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MeetingUser getMeetingUser() {
        return meetingUser;
    }

    public void setMeetingUser(MeetingUser meetingUser) {
        this.meetingUser = meetingUser;
    }
}
