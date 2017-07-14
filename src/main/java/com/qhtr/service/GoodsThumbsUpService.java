package com.qhtr.service;

public interface GoodsThumbsUpService {

	/**
	 * 点赞
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	int goodsThumbsUp(int userId, int goodsId);

	/**
	 * 查询是否已经点赞
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	int isThumbsUp(int userId, int goodsId);

	/**
	 * 取消赞
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	int delThumbsUp(int userId, int goodsId);

	/**
	 * 查询赞的数量
	 * @param goodsId
	 * @return
	 */
	int countThumbsUp(int goodsId);

}
