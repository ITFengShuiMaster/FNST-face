package com.fnst.face.entity;

public class MeetingUserKey {

    private Long meetingId;

    private Long userId;

    public MeetingUserKey () {

    }

    public MeetingUserKey (Long meetingId, Long userId) {
        this.meetingId = meetingId;
        this.userId = userId;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}