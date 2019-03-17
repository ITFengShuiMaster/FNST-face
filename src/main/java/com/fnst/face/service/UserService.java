package com.fnst.face.service;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.User;
import com.fnst.face.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Luyue
 * @date 2019/3/17 16:24
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse listUser() {
        return ServerResponse.success(userMapper.selectAll());
    }
}
