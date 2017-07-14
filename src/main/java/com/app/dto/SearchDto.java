package com.app.dto;

import java.util.List;

import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;

public class SearchDto {
	
	List<GoodsDto> goodsList;
	List<StoreGoodsDto> storeList;
	
	
	public List<StoreGoodsDto> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<StoreGoodsDto> storeList) {
		this.storeList = storeList;
	}
	public List<GoodsDto> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GoodsDto> goodsList) {
		this.goodsList = goodsList;
	}
}
