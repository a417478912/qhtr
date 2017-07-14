package com.qhtr.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.StoreOrder;

public class StoreOrderDetailsDto {
	
	public String expressDetail = "";
	public StoreOrder stordeOrder;
	public String storeName;
	public String storeAvatar;
	public String storeLocation;
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date shipmentTime;
	//商品订单
	public List<GoodsOrder> goodsOrderList;
	
	public StoreOrder getStordeOrder() {
		return stordeOrder;
	}
	public void setStordeOrder(StoreOrder stordeOrder) {
		this.stordeOrder = stordeOrder;
	}
	public List<GoodsOrder> getGoodsOrderList() {
		return goodsOrderList;
	}
	public void setGoodsOrderList(List<GoodsOrder> goodsOrderList) {
		this.goodsOrderList = goodsOrderList;
	}
	public String getExpressDetail() {
		return expressDetail;
	}
	public void setExpressDetail(String expressDetail) {
		this.expressDetail = expressDetail;
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
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public Date getShipmentTime() {
		return shipmentTime;
	}
	public void setShipmentTime(Date shipmentTime) {
		this.shipmentTime = shipmentTime;
	}
}
