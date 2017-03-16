package com.app.dto;

import java.util.List;

import com.qhtr.model.BuyCart;

public class BuyCartDto {
	public String storeId;
	public String storeName;
	public List<BuyCart> buyCartList;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public List<BuyCart> getBuyCartList() {
		return buyCartList;
	}
	public void setBuyCartList(List<BuyCart> buyCartList) {
		this.buyCartList = buyCartList;
	}
}
