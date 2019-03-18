package com.fnst.face.entity;

import java.util.Date;

public class Meeting_User extends Meeting_UserKey {
    private Byte isAttend;

    private Byte isVisited;

    private Date updateTime;

    private Date createTime;

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
}