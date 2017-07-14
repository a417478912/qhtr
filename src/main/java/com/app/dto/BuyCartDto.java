package com.app.dto;

import java.util.List;

import com.qhtr.model.BuyCart;

public class BuyCartDto {
	
	public int storeId;
	public String storeAvatar;
	public String storeName;
	public List<BuyCart> buyCartList;
	
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
	public List<BuyCart> getBuyCartList() {
		return buyCartList;
	}
	public void setBuyCartList(List<BuyCart> buyCartList) {
		this.buyCartList = buyCartList;
	}
	public String getStoreAvatar() {
		return storeAvatar;
	}
	public void setStoreAvatar(String storeAvatar) {
		this.storeAvatar = storeAvatar;
	}
}
