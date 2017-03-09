package com.qhtr.dto;

import java.util.List;

import com.qhtr.model.Attr;
import com.qhtr.model.Goods;

public class GoodsDto {
	private Goods goods;
	private List<Attr> attrList;
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
