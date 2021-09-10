package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.CovidUserBean;
import com.htht.pro.mapper.CovidUserMapper;

@Service
public class CovidUserService {
	
	@Autowired
	private CovidUserMapper covidUserMapper;
	
	public List<CovidUserBean> getPageList(String userName,int currentPageNo,int pageSize){
		return covidUserMapper.getPageList(userName,currentPageNo, pageSize);
	}
	
	public int getPageListCount(String userName){
		
		return covidUserMapper.getPageListCount(userName);
	}
	
	public int add(CovidUserBean b){
		b.setUserId(Utils.getId());
		return covidUserMapper.add(b);
	}
	
	public int update(CovidUserBean b){
		return covidUserMapper.add(b);
	}
	
	public List<CovidUserBean> findListByNric(String nric){
		return covidUserMapper.findListByNric(nric);
	}
	
	public CovidUserBean findByUserId(String userId){
		return covidUserMapper.findByUserId(userId);
	}
	
	public CovidUserBean findUserByNric(String nric,String password){
		
		return covidUserMapper.findUserByNric(nric, password);
	}
}
