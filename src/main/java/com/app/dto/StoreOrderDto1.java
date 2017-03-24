package com.app.dto;

import java.util.List;

import com.qhtr.model.BuyCart;

/**
 * 下单时的订单dto
 * @author wjx
 *
 * @date  2017年3月23日
 */
public class StoreOrderDto1 {
	public String storeOrder; 
	public String storeName;
	public String storeAvatar;
	public List<BuyCart> buyCartList;
	public String userRemark;
	public int expressPrice;
	public int couponId;
	public String couponName;
	public int couponMoney;
	public String phone;
	public String getStoreOrder() {
		return storeOrder;
	}
	public void setStoreOrder(String storeOrder) {
		this.storeOrder = storeOrder;
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
	public List<BuyCart> getBuyCartList() {
		return buyCartList;
	}
	public void setBuyCartList(List<BuyCart> buyCartList) {
		this.buyCartList = buyCartList;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public int getExpressPrice() {
		return expressPrice;
	}
	public void setExpressPrice(int expressPrice) {
		this.expressPrice = expressPrice;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public int getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(int couponMoney) {
		this.couponMoney = couponMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}