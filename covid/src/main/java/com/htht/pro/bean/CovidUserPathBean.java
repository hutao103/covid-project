package com.htht.pro.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("covid.CovidUserPath")
public class CovidUserPathBean {

	private String pathId;
	private String userId;
	private String isCarryVirus;
	private String type;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;

	private String addressName;
	private String userName;
	private String isInfect;
	private String startTime;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getIsInfect() {
		return isInfect;
	}
	public void setIsInfect(String isInfected) {
		this.isInfect = isInfected;
	}
	public String getPathId() {
		return pathId;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsCarryVirus() {
		return isCarryVirus;
	}
	public void setIsCarryVirus(String isCarryVirus) {
		this.isCarryVirus = isCarryVirus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
