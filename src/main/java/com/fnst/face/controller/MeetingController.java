package com.fnst.face.controller;

import java.util.List;
import java.util.Map;

import com.fnst.face.common.EasyUIResponse;
import com.fnst.face.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<Meeting> rows = (List<Meeting>)meetingService.listMeeting().getData();
		EasyUIResponse<Meeting> response = new EasyUIResponse<>();
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

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable Long id){
		return meetingService.deleteMeeting(id);
	}
}
