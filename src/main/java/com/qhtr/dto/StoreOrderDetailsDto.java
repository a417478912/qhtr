package com.qhtr.dto;

import java.util.List;

import com.qhtr.model.GoodsOrder;
import com.qhtr.model.StoreOrder;

public class StoreOrderDetailsDto {
	public StoreOrder stordeOrder;
	//收货人名字
	public String receivingName;
	//收货人手机号
	public String receivingPhone;
	//收货地址
	public String address;
	//商品订单
	public List<GoodsOrder> goodsOrderList;
	public StoreOrder getStordeOrder() {
		return stordeOrder;
	}
	public void setStordeOrder(StoreOrder stordeOrder) {
		this.stordeOrder = stordeOrder;
	}
	public String getReceivingName() {
		return receivingName;
	}
	public void setReceivingName(String receivingName) {
		this.receivingName = receivingName;
	}
	public String getReceivingPhone() {
		return receivingPhone;
	}
	public void setReceivingPhone(String receivingPhone) {
		this.receivingPhone = receivingPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<GoodsOrder> getGoodsOrderList() {
		return goodsOrderList;
	}
	public void setGoodsOrderList(List<GoodsOrder> goodsOrderList) {
		this.goodsOrderList = goodsOrderList;
	}
}
