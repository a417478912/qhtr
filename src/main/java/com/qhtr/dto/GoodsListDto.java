package com.qhtr.dto;

import java.math.BigDecimal;

import com.qhtr.model.Picture;

public class GoodsListDto {
	public int id;
	public Picture picture;
	public String name;
	public BigDecimal topPrice;
	public BigDecimal lowPrice;
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
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public BigDecimal getTopPrice() {
		return topPrice;
	}
	public void setTopPrice(BigDecimal topPrice) {
		this.topPrice = topPrice;
	}
	public BigDecimal getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}
}
