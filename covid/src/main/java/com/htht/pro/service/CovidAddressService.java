package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.CovidAddressBean;
import com.htht.pro.mapper.CovidAddressMapper;

@Service
public class CovidAddressService {
	
	@Autowired
	private CovidAddressMapper covidAddressMapper;
	
	public List<CovidAddressBean> getPageList(String name,int currentPageNo,int pageSize){
		return covidAddressMapper.getPageList(name,currentPageNo, pageSize);
	}
	
	public int getPageListCount(String name){
		
		return covidAddressMapper.getPageListCount(name);
	}
	
	public List<CovidAddressBean> findList(){
		return covidAddressMapper.findList();
	}
	
	public List<CovidAddressBean> findListByName(String name){
		return covidAddressMapper.findListByName(name);
	}
	
	public CovidAddressBean findListByAddressId(String addressId){
		return covidAddressMapper.findListByAddressId(addressId);
	}
	
	public int add(CovidAddressBean b){
		b.setAddressId(Utils.getId());
		return covidAddressMapper.add(b);
	}
	
	public int update(CovidAddressBean b){
		return covidAddressMapper.update(b);
	}
	
	
	public int deleteByAddressId(String addressId){
		return covidAddressMapper.deleteByAddressId(addressId);
	}
}
