package com.app.dto;

import com.qhtr.model.Picture;

public class GoodsListDto {
	public int id;
	public Picture picture;
	public String name;
	public int topPrice;
	public int lowPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTopPrice() {
		return topPrice;
	}
	public void setTopPrice(int topPrice) {
		this.topPrice = topPrice;
	}
	public int getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
}
