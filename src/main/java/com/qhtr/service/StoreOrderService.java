package com.qhtr.service;

import com.qhtr.model.GoodsOrder;
import com.qhtr.model.StoreOrder;

public interface StoreOrderService {
	String insertByGoodsOrder(GoodsOrder go);
	
	int insert(StoreOrder record);

	StoreOrder selectByOrderCode(String soCode);

	StoreOrder selectById(int storeOrderId);
}
