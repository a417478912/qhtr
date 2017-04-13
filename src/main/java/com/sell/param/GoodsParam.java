package com.sell.param;

import java.util.List;

import com.qhtr.model.Sku;

public class GoodsParam {
	public int id;
	public int storeId;
	public String picture;
	public String name;
	public List<Sku> skus;
	public String descript;
	public int goodsClassId;
	public int[] activity;
	public int status;
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Sku> getSkus() {
		return skus;
	}
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public int[] getActivity() {
		return activity;
	}
	public void setActivity(int[] activity) {
		this.activity = activity;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
