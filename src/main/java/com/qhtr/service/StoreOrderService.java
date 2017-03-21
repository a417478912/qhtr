package com.qhtr.service;

import java.util.List;

import com.app.dto.StoreOrderDto;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.StoreOrder;

public interface StoreOrderService {
	String insertByGoodsOrder(GoodsOrder go);
	
	int insert(StoreOrder record);

	StoreOrder selectByOrderCode(String soCode);

	StoreOrder selectById(int storeOrderId);
	
	int updateByCondition(StoreOrder record);
	
	/**
	 * 用户查询订单
	 * @param userId
	 * @param status
	 * @return
	 */
	List<StoreOrderDto> getOrdersByUser(int userId, int status);
}
