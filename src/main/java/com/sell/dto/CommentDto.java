package com.sell.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;

public class CommentDto {
	public int id;
	public int userId;
	public String userName;
	public int storeId;
	public String storeName;
	@JsonSerialize(using = CustomDateSerializer.class) 
	public Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
