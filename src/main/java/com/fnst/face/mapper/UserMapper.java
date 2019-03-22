package com.fnst.face.mapper;

import com.fnst.face.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertReturnId(User user);

    User selectByUserNumber(String userNumber);

    List<User> selectAll();
    List<User> selectByName(String name);
}