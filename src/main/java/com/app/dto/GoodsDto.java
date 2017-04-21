package com.app.dto;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.qhtr.model.Sku;

public class GoodsDto {
	public Goods goods;
	public List<GoodsClasses> goodsClasses;
	public List<Map<String,Object>> activityList;
	public List<Attr> attrList;
	public List<Sku> skuList;
	
	
	public List<Sku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public List<Attr> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<Attr> attrList) {
		this.attrList = attrList;
	}
	public List<GoodsClasses> getGoodsClasses() {
		return goodsClasses;
	}
	public void setGoodsClasses(List<GoodsClasses> goodsClasses) {
		this.goodsClasses = goodsClasses;
	}
	public List<Map<String, Object>> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Map<String, Object>> activityList) {
		this.activityList = activityList;
	}
}
