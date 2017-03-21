package com.app.dto;

import java.util.List;

public class StoreOrderDto {
	List<GoodsOrderDto> goodsOrders;
	public int storeOrderId;
	public int storeId;
	public String storeName;
	public String storeAvatar;
	public int status;
	public int totalPrice;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public List<GoodsOrderDto> getGoodsOrders() {
		return goodsOrders;
	}
	public void setGoodsOrders(List<GoodsOrderDto> goodsOrders) {
		this.goodsOrders = goodsOrders;
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
	public String getStoreAvatar() {
		return storeAvatar;
	}
	public void setStoreAvatar(String storeAvatar) {
		this.storeAvatar = storeAvatar;
	}
	public int getStoreOrderId() {
		return storeOrderId;
	}
	public void setStoreOrderId(int storeOrderId) {
		this.storeOrderId = storeOrderId;
	}
}
