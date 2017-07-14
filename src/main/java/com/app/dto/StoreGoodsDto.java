package com.app.dto;

import java.util.List;

import com.qhtr.model.Goods;
import com.qhtr.model.Store;

public class StoreGoodsDto {
	
	public Store store;
	public List<Goods> goodsList;
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}
