package com.app.dto;

import java.util.List;

import com.qhtr.model.Category;
import com.qhtr.model.Goods;
import com.qhtr.model.Store;

public class IndexDto {
	public List<Category> categorys;
	/**
	 *  首页分类   周边好货
	 */
	public List<Goods> goodsList;
	
	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Store> storeList1;
	
	public List<Store> storeList2;
	
	public List<Store> storeList3;
	
	public List<Store> storeList4;

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public List<Store> getStoreList1() {
		return storeList1;
	}

	public void setStoreList1(List<Store> storeList1) {
		this.storeList1 = storeList1;
	}

	public List<Store> getStoreList2() {
		return storeList2;
	}

	public void setStoreList2(List<Store> storeList2) {
		this.storeList2 = storeList2;
	}

	public List<Store> getStoreList3() {
		return storeList3;
	}

	public void setStoreList3(List<Store> storeList3) {
		this.storeList3 = storeList3;
	}

	public List<Store> getStoreList4() {
		return storeList4;
	}

	public void setStoreList4(List<Store> storeList4) {
		this.storeList4 = storeList4;
	}
	
}
