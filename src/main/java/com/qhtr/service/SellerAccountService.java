package com.qhtr.service;

import com.qhtr.model.SellerAccount;

public interface SellerAccountService {
	int insert(SellerAccount account);
	/**
	 * 添加卖家的openid
	 * @param storeId
	 * @param openId
	 * @return
	 */
	int updateOpenId(int storeId, String openId);
	/**
	 * 通过商铺ID，查找账户
	 * @param storeId
	 * @return
	 */
	SellerAccount getByStoreId(int storeId);
}
