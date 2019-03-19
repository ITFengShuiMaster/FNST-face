package com.fnst.face.controller;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.service.UserMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luyue
 * @date 2019/3/18 16:02
 **/
@RestController
@RequestMapping("/u_meeting")
public class UserMeetingController {

    @Autowired
    private UserMeetingService userMeetingService;

    @GetMapping("/{meetingId}")
    public ServerResponse list(@PathVariable Integer meetingId) {
        return userMeetingService.listMeetingUser(meetingId);
    }
}
