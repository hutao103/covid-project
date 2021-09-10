package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.CovidMeetingInfectedBean;
import com.htht.pro.mapper.CovidMeetingInfectedMapper;

@Service
public class CovidMeetingInfectedService {
	
	@Autowired
	private CovidMeetingInfectedMapper covidMeetingInfectedMapper;
	
	public List<CovidMeetingInfectedBean> getPageList(String userName,String addressName,int currentPageNo,int pageSize){
		return covidMeetingInfectedMapper.getPageList(userName,addressName,currentPageNo, pageSize);
	}
	
	public int getPageListCount(String userName,String addressName){
		
		return covidMeetingInfectedMapper.getPageListCount(userName,addressName);
	}
	
	public List<CovidMeetingInfectedBean> getAppPageList(String userId,int currentPageNo,int pageSize){
		return covidMeetingInfectedMapper.getAppPageList(userId,currentPageNo, pageSize);
	}
	
	public int getAppPageListCount(String userId){
		
		return covidMeetingInfectedMapper.getAppPageListCount(userId);
	}
	
	public int add(CovidMeetingInfectedBean b){
		b.setId(Utils.getId());
		return covidMeetingInfectedMapper.add(b);
	}
}
