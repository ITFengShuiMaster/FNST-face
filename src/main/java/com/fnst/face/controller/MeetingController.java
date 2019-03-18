package com.fnst.face.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.Meeting;
import com.fnst.face.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;
	
	
	@GetMapping("/list")
	public ServerResponse list() {
		return meetingService.listMeeting();
	}
	
	@GetMapping("")
	public ServerResponse get(@RequestParam("id") Long id) {
		return meetingService.getMeeting(id);
	}
	
	
	@PostMapping
    public ServerResponse create(Meeting meeting) {
        return meetingService.insertMeeting(meeting);
    }
}
