package com.qhtr.service;

import java.util.List;

import com.app.dto.GoodsDto;
import com.qhtr.model.Goods;

public interface GoodsService {
	/**
	 * 通过卖家id  获取商品信息
	 * @param type 类型: 0.全部  1. 新品上架  2. 特卖    3 店长推荐
	 */
	public List<Goods> selectGoodsBySellerId(int sellerId,int type);
	/**
	 * 通过商品id  获取商品详情
	 */
	public GoodsDto selectGoodsByGoodsId(int goodsId);
	/**
	 * 条件查找商品
	 */
	public List<Goods> selectGoodsByCondition(Goods goods,int page,int number);
	/**
	 * 周边好货
	 * page 第几页
	 * number 数量
	 */
	public List<Goods> selectGoodsByCondition1(int page,int number);
	/**
	 * 通过搜索内容搜索商品
	 * @param searchContent
	 * @return
	 */
	public List<Goods> selectGoodsBySearch(String searchContent, int page, int num);
}
