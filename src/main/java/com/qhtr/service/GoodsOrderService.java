package com.qhtr.service;

import java.util.List;

import com.app.dto.BuyCartDto;
import com.qhtr.model.GoodsOrder;

public interface GoodsOrderService {
	/**
	 * 添加商品订单
	 * @param goodsOrder
	 * @return
	 */
	String addGoodsOrder(GoodsOrder goodsOrder);
	/**
	 * 从购物车结算
	 * @param userId
	 * @param ids
	 * @param distribution_type
	 * @return
	 */
	List<BuyCartDto> selectFromBuyCartIds(int userId, int[] ids);
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
	
	GoodsOrder selectById(int id);
	/**
	 * 购物车确认订单
	 * @param userId
	 * @param ids
	 * @param userRemark
	 * @param distributionType
	 * @return
	 */
	String addGoodsOrders(int userId, int[] ids, String[] userRemark, int distributionType,int addressId);
	
}
