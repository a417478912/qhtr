package com.qhtr.service;

import java.util.List;

import com.app.dto.GoodsDto;
import com.qhtr.model.Goods;
import com.sell.param.GoodsParam;

public interface GoodsService {
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
	
	public int add(String goodsParam);
	public int delete(int id);
	public int update(String goodsParam);
	
	/**
	 * 通过卖家id和商品状态查找商品
	 * @param storeId
	 * @param type
	 * @return
	 */
	public List<Goods> selectListByStoreAndType(int storeId, int status);
	/**
	 * 置顶/取消置顶
	 * @param goodsId
	 * @return
	 */
	public int toTop(int goodsId);
}
