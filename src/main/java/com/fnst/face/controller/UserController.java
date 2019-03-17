package com.fnst.face.controller;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luyue
 * @date 2019/3/17 16:23
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public ServerResponse list() {
        return userService.listUser();
    }
}
