package com.app.dto;

public class GoodsOrderDto {
	public int goodsOrderId;
	public String thumb;
	public String skuDetails;
	public int price;
	public int num;
	public int storeOrderId;
	public String goodsName;
	
	
	public int getGoodsOrderId() {
		return goodsOrderId;
	}
	public void setGoodsOrderId(int goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getSkuDetails() {
		return skuDetails;
	}
	public void setSkuDetails(String skuDetails) {
		this.skuDetails = skuDetails;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getStoreOrderId() {
		return storeOrderId;
	}
	public void setStoreOrderId(int storeOrderId) {
		this.storeOrderId = storeOrderId;
	}
	
}
