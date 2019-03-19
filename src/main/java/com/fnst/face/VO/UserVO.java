package com.fnst.face.VO;

import com.fnst.face.entity.User;

import java.util.Date;

/**
 * @author Luyue
 * @date 2019/3/19 10:58
 **/
public class UserVO {

    private User user;

    private String onLineImgUrl;

    private Boolean isAttend;

    private Boolean isVisited;

    private Date updateTime;

    private Date createTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOnLineImgUrl() {
        return onLineImgUrl;
    }

    public void setOnLineImgUrl(String onLineImgUrl) {
        this.onLineImgUrl = onLineImgUrl;
    }

    public Boolean getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Boolean isAttend) {
        this.isAttend = isAttend;
    }

    public Boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Boolean isVisited) {
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
