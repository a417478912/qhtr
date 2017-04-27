package com.qhtr.service;

import com.qhtr.model.Express;

public interface ExpressService {
	/**
	 * 小订单
	 * @param orderId
	 * @param name
	 * @param code
	 * @return
	 */
	int add(String orderId, String name, String code);
	/**
	 * 大订单
	 */
	int add(int orderId, String name, String code);
	
	Express getByStoreOrderId(int storeOrderId);
	
	int delete(Integer id);
}
