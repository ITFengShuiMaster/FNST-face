package com.fnst.face.mapper;

import com.fnst.face.entity.Meeting_User;
import com.fnst.face.entity.Meeting_UserKey;

public interface Meeting_UserMapper {
    int deleteByPrimaryKey(Meeting_UserKey key);

    int insert(Meeting_User record);

    int insertSelective(Meeting_User record);

    Meeting_User selectByPrimaryKey(Meeting_UserKey key);

    int updateByPrimaryKeySelective(Meeting_User record);

    int updateByPrimaryKey(Meeting_User record);
}