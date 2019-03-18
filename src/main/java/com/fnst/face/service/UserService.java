package com.fnst.face.service;

import com.fnst.face.common.ResponseCode;
import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.User;
import com.fnst.face.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Luyue
 * @date 2019/3/17 16:24
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse insertUser(User user) {
        if (!validUserData(user)) {
            return ServerResponse.failure("参数不全或参数错误");
        }

        try {
            if (userMapper.insertReturnId(user) <= 0) {
                return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
        }
        return ServerResponse.success();
    }

    private boolean validUserData(User user) {
        if (StringUtils.isBlank(user.getName())) {
            return false;
        }

        if (StringUtils.isBlank(user.getImgUrl())) {
            return false;
        }

        if (StringUtils.isBlank(user.getJobNumber())) {
            return false;
        }

        if (user.getSex() == null) {
            user.setSex(false);
        }

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return true;
    }
}
