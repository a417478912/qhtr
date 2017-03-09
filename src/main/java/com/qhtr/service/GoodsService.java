package com.qhtr.service;

import java.util.List;

import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;

public interface GoodsService {
	/**
	 * 通过卖家id  获取商品信息
	 */
	public List<Goods> selectGoodsBySellerId(int sellerId);
	/**
	 * 通过商品id  获取商品详情
	 */
	public GoodsDto selectGoodsByGoodsId(int goodsId);
}
