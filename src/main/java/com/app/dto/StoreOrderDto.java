package com.app.dto;

import java.util.List;

/**
 * 查询的时的订单dto
 * @author wjx
 *
 * @date  2017年3月23日
 */
public class StoreOrderDto {
	List<GoodsOrderDto> goodsOrders;
	public String storeOrderCode;
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
	public String getStoreOrderCode() {
		return storeOrderCode;
	}
	public void setStoreOrderCode(String storeOrderCode) {
		this.storeOrderCode = storeOrderCode;
	}
}