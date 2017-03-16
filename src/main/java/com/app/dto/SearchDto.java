package com.app.dto;

import java.util.List;

import com.qhtr.model.Goods;

public class SearchDto {
	List<Goods> goodsList;
	List<StoreGoodsDto> storeList;
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public List<StoreGoodsDto> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<StoreGoodsDto> storeList) {
		this.storeList = storeList;
	}
}
