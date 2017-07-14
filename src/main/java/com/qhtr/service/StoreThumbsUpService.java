package com.qhtr.service;

public interface StoreThumbsUpService {

	/**
	 * 查询是否已赞
	 * @param storeId
	 * @param userId
	 * @return
	 */
	int isThumbsUp(int storeId, int userId);

	/**
	 * 点赞
	 * @param userId
	 * @param storeId
	 * @return
	 */
	int storeThumbsUp(int userId, int storeId);

	/**
	 * 取消赞
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	int delThumbsUp(int userId, int goodsId);

	/**
	 * 查询订单状态
	 * @param storeId
	 * @return
	 */
	int countThumbsUp(int storeId);

}
