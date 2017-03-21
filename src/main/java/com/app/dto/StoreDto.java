package com.app.dto;

import com.qhtr.model.Store;

public class StoreDto {
    private Integer id;

    private Integer sellerId;

    private String name;

    private String phone;

    private String avatar;

    private String[] picture;

    private String details;
    
    public StoreDto(Store store){
    	this.id = store.getId();
    	this.sellerId = store.getSellerId();
    	this.name = store.getName();
    	this.phone = store.getPhone();
    	this.avatar = store.getAvatar();
    	if(store.getPicture1() != null){
    		this.picture = store.getPicture1().split(",");
    	}
    	this.details = store.getDetails();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String[] getPicture() {
		return picture;
	}

	public void setPicture(String[] picture) {
		this.picture = picture;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}