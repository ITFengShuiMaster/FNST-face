package com.fnst.face.controller;

import java.util.List;

import com.fnst.face.common.EasyUIResponse;
import com.fnst.face.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.Meeting;
import com.fnst.face.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;

	@GetMapping("/list")
	public String list() {
		List<Meeting> rows = (List<Meeting>)meetingService.listMeeting().getRows();
		EasyUIResponse<Meeting> response = new EasyUIResponse<>();
		response.setList(rows);
		response.setTotal(rows.size());
		return JsonUtil.objToJson(response).replace("list", "rows");
	}
	
	@GetMapping("")
	public ServerResponse get(@RequestParam("id") Long id) {
		return meetingService.getMeeting(id);
	}
	@GetMapping("/search")
	public ServerResponse search(@RequestParam("name") String name){
		return meetingService.getMeetings(name);
	}

	@PostMapping("/add")
    public ServerResponse create(Meeting meeting) {
		return meetingService.insertMeeting(meeting);
    }

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable Long id){
		return meetingService.deleteMeeting(id);
	}
}
