package com.qhtr.service;

import java.util.List;
import java.util.Map;

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
	/**
	 * 卖家查询商品订单列表
	 * @param goTem
	 * @return
	 */
	List<Map<String, Object>> selectMapByConditions(Map map);
	
	/**
	 * 查询数量
	 */
	int selectCountByConditions(Map<String,Object> map);
}
