package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.CovidVaccineBean;
import com.htht.pro.mapper.CovidVaccineMapper;

@Service
public class CovidVaccineService {
	
	@Autowired
	private CovidVaccineMapper covidVaccineMapper;
	
	public List<CovidVaccineBean> getPageList(String userName,String addressId,int currentPageNo,int pageSize){
		return covidVaccineMapper.getPageList(userName,addressId,currentPageNo, pageSize);
	}
	
	public int getPageListCount(String userName,String addressId){
		
		return covidVaccineMapper.getPageListCount(userName,addressId);
	}
	
	public List<CovidVaccineBean> getAppPageList(String userId,int currentPageNo,int pageSize){
		return covidVaccineMapper.getAppPageList(userId,currentPageNo, pageSize);
	}
	
	public int getAppPageListCount(String userId){
		
		return covidVaccineMapper.getAppPageListCount(userId);
	}
	
	public int add(CovidVaccineBean b){
		b.setId(Utils.getId());
		return covidVaccineMapper.add(b);
	}
}
