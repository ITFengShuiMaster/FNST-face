package com.fnst.face.mapper;

import java.util.List;

import com.fnst.face.entity.Meeting;

public interface MeetingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Meeting record);

    int insertSelective(Meeting record);

    Meeting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Meeting record);

    int updateByPrimaryKey(Meeting record);
    
    List<Meeting> selectAll();
    
    int insertReturnId(Meeting meeting);

    List<Meeting> selectByName(String name);
}