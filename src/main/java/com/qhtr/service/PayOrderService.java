package com.qhtr.service;

import java.util.List;

import com.qhtr.model.PayOrder;

public interface PayOrderService {
	/**
	 * 创建支付订单，返回支付单号
	 * @param goodsOrderId
	 * @param type
	 * @return
	 */
	String addOrder(String storeOrderCode, int type,int userId);

	List<PayOrder> selectByConditions(PayOrder poTem);

	int insert(PayOrder po);
	
}
