package com.fnst.face.entity;

import java.util.Date;

public class MeetingUser extends MeetingUserKey {
    private Byte isAttend;

    private Byte isVisited;

    private Date updateTime;

    private Date createTime;

    private String imgUrl;

    public Byte getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Byte isAttend) {
        this.isAttend = isAttend;
    }

    public Byte getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Byte isVisited) {
        this.isVisited = isVisited;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}