package com.qhtr.service;

import com.qhtr.model.PickUpCode;

public interface PickUpCodeService {

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
	int insertPickUpCode(PickUpCode pickUpCode);
	
	/**
	 * 删除取货码
	 * @param storeOrderCode
	 */
	int deletePickUpCode(int storeOrderId);

	/**
	 * 验证取货码
	 * @param pickUpCode
	 * @return
	 */
	PickUpCode getStoreOrderIdByPickUpCode(String pickUpCode);
}
