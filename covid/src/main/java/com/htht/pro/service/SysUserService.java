package com.htht.pro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htht.pro.Utils;
import com.htht.pro.bean.SysUserBean;
import com.htht.pro.mapper.SysUserMapper;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public SysUserBean findUserByUserName(String userName,String password){
		
		return sysUserMapper.findUserByUserName(userName, password);
	}
	
	public List<SysUserBean> getPageList(String userName,int currentPageNo,int pageSize){
		
		return sysUserMapper.getPageList(userName, currentPageNo, pageSize);
	}
	
	public int getPageListCount(String userName){
		
		return sysUserMapper.getPageListCount(userName);
	}
	
	public int deleteUserById(String userId){
		return sysUserMapper.deleteUserById(userId);
	}
	
	public int add(SysUserBean b){
		b.setUserId(Utils.getId());
		return sysUserMapper.add(b);
	}
	
	public List<SysUserBean> findListByUserName(String userName){
		
		return sysUserMapper.findListByUserName(userName);
	}
	
	public SysUserBean findListByUserId(String userId){
		return sysUserMapper.findListByUserId(userId);
	}
	
	public int update(SysUserBean b){
		return sysUserMapper.update(b);
	}
}
