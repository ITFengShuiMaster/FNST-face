package com.fnst.face.controller;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.User;
import com.fnst.face.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luyue
 * @date 2019/3/17 16:23
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ServerResponse create(User user, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        return userService.insertUser(user, path);
    }

    @GetMapping("/{id}")
    public ServerResponse get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public ServerResponse get() {
        return userService.listUsers();
    }

    @PutMapping
    public ServerResponse update(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public ServerResponse remove(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
