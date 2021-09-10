package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.CovidMonitorBean;
import com.htht.pro.mapper.CovidMonitorMapper;

@Service
public class CovidMonitorService {
	
	@Autowired
	private CovidMonitorMapper covidMonitorMapper;
	
	public List<CovidMonitorBean> getPageList(String userName,String addressId,int currentPageNo,int pageSize){
		return covidMonitorMapper.getPageList(userName,addressId,currentPageNo, pageSize);
	}
	
	public int getPageListCount(String userName,String addressId){
		
		return covidMonitorMapper.getPageListCount(userName,addressId);
	}
	
	public List<CovidMonitorBean> getAppPageList(String userId,int currentPageNo,int pageSize){
		return covidMonitorMapper.getAppPageList(userId,currentPageNo, pageSize);
	}
	
	public int getAppPageListCount(String userId){
		
		return covidMonitorMapper.getAppPageListCount(userId);
	}
	
	public int add(CovidMonitorBean b){
		b.setId(Utils.getId());
		return covidMonitorMapper.add(b);
	}
	
	public int deleteByUserId(String userId){
		return covidMonitorMapper.deleteByUserId(userId);
	}
}
