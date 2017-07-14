package com.qhtr.dao;

import com.qhtr.model.PickUpCode;

public interface PickUpCodeMapper {

	/**
	 * 根据 storeOrderCode 获取取货码
	 * @param storeOrderCode
	 * @return
	 */
	PickUpCode getPickUpCode(int storeOrderId);
	
	/**
	 * 保存取货码
	 * @param pickUpCode
	 */
	void insertPickUpCode(PickUpCode pickUpCode);
	
	/**
	 * 删除取货码
	 * @param storeOrderCode
	 */
	void deletePickUpCode(int storeOrderId);

	/**
	 * 通过取货码查找订单
	 * @param pickUpCode
	 * @return
	 */
	PickUpCode getStoreOrderIdByPickUpCode(String pickUpCode);
}
