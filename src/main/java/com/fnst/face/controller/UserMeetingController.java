package com.fnst.face.controller;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.MeetingUser;
import com.fnst.face.service.UserMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public ServerResponse list(@PathVariable Long meetingId) {
        return userMeetingService.listMeetingUser(meetingId);
    }

    @PostMapping
    public ServerResponse addUserInMeeting(MeetingUser meetingUser) {
        return userMeetingService.insertUserInMeeting(meetingUser);
    }

    @PostMapping("/signIn/{meetingId}")
    public ServerResponse signIn(String onlineImgFaceToken, String onlineImgFaceBase64_2, @PathVariable Long meetingId,  HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        return userMeetingService.signIn(path, onlineImgFaceToken, onlineImgFaceBase64_2, meetingId);
    }
}
