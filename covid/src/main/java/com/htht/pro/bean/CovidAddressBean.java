package com.htht.pro.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("covid.CovidAddress")
public class CovidAddressBean {

	private String addressId;
	private String name;
	private String lnglat;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLnglat() {
		return lnglat;
	}
	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
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
