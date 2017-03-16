package com.qhtr.dto;

import java.util.List;

import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.model.Sku;

public class GoodsDto {
	public Goods goods;
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
}
