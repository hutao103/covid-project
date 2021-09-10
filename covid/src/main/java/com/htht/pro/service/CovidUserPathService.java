package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.CovidUserPathBean;
import com.htht.pro.mapper.CovidUserPathMapper;

@Service
public class CovidUserPathService {
	
	@Autowired
	private CovidUserPathMapper covidUserPathMapper;
	
	public List<CovidUserPathBean> getPageList(String userName,String addressName,String isCarryVirus,String type,int currentPageNo,int pageSize){
		return covidUserPathMapper.getPageList(userName,addressName,isCarryVirus,type,currentPageNo, pageSize);
	}
	
	public int getPageListCount(String userName,String addressName,String isCarryVirus,String type){
		
		return covidUserPathMapper.getPageListCount(userName,addressName,isCarryVirus,type);
	}
	
	public List<CovidUserPathBean> getAppPageList(String userId,int currentPageNo,int pageSize){
		return covidUserPathMapper.getAppPageList(userId,currentPageNo, pageSize);
	}
	
	public int getAppPageListCount(String userId){
		
		return covidUserPathMapper.getAppPageListCount(userId);
	}
	
	public int add(CovidUserPathBean b){
		b.setPathId(Utils.getId());
		return covidUserPathMapper.add(b);
	}
}
