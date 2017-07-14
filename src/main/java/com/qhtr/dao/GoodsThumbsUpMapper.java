package com.qhtr.dao;

import com.qhtr.model.GoodsThumbsUp;

public interface GoodsThumbsUpMapper {

	/**
	 * 通过条件查询是否点赞
	 * @param gtu
	 * @return
	 */
	GoodsThumbsUp selectGoodsThumbsUpByCondition(GoodsThumbsUp gtu);

	/**
	 * 点赞
	 * @param gtu
	 */
	void thumbsUp(GoodsThumbsUp gtu);

	/**
	 * 取消赞
	 * @param gtu
	 */
	void delThumbsUp(GoodsThumbsUp gtu);

	/**
	 * 查询点赞的数量
	 * @param goodsId
	 * @return
	 */
	int countThumbsUp(int goodsId);

}
