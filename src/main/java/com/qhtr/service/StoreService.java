package com.qhtr.service;

import com.qhtr.model.Store;

public interface StoreService {
	/**
	 * 通过商铺id查找商铺
	 * @param storeId
	 * @return
	 */
	Store getStoreBysSoreId(Integer storeId);
	/**
	 * 通过卖家id  查找商铺
	 * @param sellerId
	 * @return
	 */
	Store getStoreBySellerId(Integer sellerId);

}
