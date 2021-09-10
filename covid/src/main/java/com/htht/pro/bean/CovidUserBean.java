package com.htht.pro.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("covid.CovidUser")
public class CovidUserBean {

	private String userId;
	private String userName;
	private String password;
	private String isInfect;
	private String nric;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsInfect() {
		return isInfect;
	}
	public void setIsInfect(String isInfect) {
		this.isInfect = isInfect;
	}
	
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
