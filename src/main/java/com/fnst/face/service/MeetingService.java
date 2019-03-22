package com.fnst.face.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fnst.face.common.ResponseCode;
import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.Meeting;
import com.fnst.face.mapper.MeetingMapper;

@Service
public class MeetingService {
	
	@Autowired
	private MeetingMapper meetingMapper;

	public ServerResponse listMeeting() {
		return ServerResponse.success(meetingMapper.selectAll());
	}

	public ServerResponse getMeetings(String name){
		return ServerResponse.success(meetingMapper.selectByName(name));
	}

	public ServerResponse getMeeting(Long id) {
		return ServerResponse.success(meetingMapper.selectByPrimaryKey(id));
	}
	
	public ServerResponse insertMeeting(Meeting meeting) {
		if (!validMeetingData(meeting)) {
			return ServerResponse.failure("参数不全或参数错误");
		}
		try {
			if (meetingMapper.insert(meeting) <= 0) {
				return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
		}
		return ServerResponse.success();
	}
	
	public ServerResponse deleteMeeting(Long id) {
		if (meetingMapper.deleteByPrimaryKey(id) <= 0) {
			return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
		}
		return ServerResponse.success();
	}
	
	public ServerResponse updateMeeting(Meeting meeting) {
		if (!validMeetingData(meeting)) {
			return ServerResponse.failure("参数不全或参数错误");
		}
		try {
            if (meetingMapper.updateByPrimaryKey(meeting) <= 0) {
                return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
        }
		return ServerResponse.success();
	}
	
	private boolean validMeetingData(Meeting meeting) {
		if (StringUtils.isBlank(meeting.getName())) {
			return false;
		}

		if (null == meeting.getMeetingTime() || !meeting.getMeetingTime().before(new Date()) ) {
			return false;
		}
		meeting.setUpdateTime(new Date());
		meeting.setCreateTime(new Date());
		return true;
	}
}
