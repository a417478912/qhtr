package com.qhtr.service;

import java.util.List;

import com.qhtr.model.GoodsOrder;

public interface GoodsOrderService {
	/**
	 * 条件查找
	 * @param goodsOrder
	 * @return
	 */
	List<GoodsOrder> selectByCondictions(GoodsOrder goodsOrder);
	/**
	 *  更新订单
	 */
	
	int updateGoodsOrder(GoodsOrder goodsOrder);
	/**
	 * 添加订单
	 * @param goodsOrder
	 * @return
	 */
	int addGoodsOrder(GoodsOrder goodsOrder);
	
	GoodsOrder selectById(int id);
}
