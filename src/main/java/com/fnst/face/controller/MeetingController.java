package com.fnst.face.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fnst.face.common.EasyUIResponse;
import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.Meeting;
import com.fnst.face.service.MeetingService;
import com.fnst.face.util.JsonUtil;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;

	
	@RequestMapping("/list")
	@ResponseBody
	public String list() {
		ServerResponse sr = meetingService.listMeeting();
		EasyUIResponse<Meeting> response = new EasyUIResponse<>();
		List<Meeting> rows = (List<Meeting>)sr.getRows();
		response.setList(rows);
		response.setTotal(rows.size());
		return JsonUtil.objToJson(response).replace("list", "rows");
	}
	
	@GetMapping("")
	public ServerResponse get(@RequestParam("id") Long id) {
		return meetingService.getMeeting(id);
	}
	
	
	@PostMapping("/add")
    public ServerResponse create(Meeting meeting) {
		return meetingService.insertMeeting(meeting);
    }

    @RequestMapping("/delete")
    public ServerResponse delete(@RequestParam("id") Long id){

		return meetingService.deleteMeeting(id);
	}
}
