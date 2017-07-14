package com.qhtr.dao;

import com.qhtr.model.StoreThumbsUp;

public interface StoreThumbsUpMapper {

	/**
	 * 查询是否已点赞
	 * @param stu
	 * @return
	 */
	StoreThumbsUp isThumbsUp(StoreThumbsUp stu);
	
	/**
	 * 点赞
	 * @param stu
	 */
	void thumbsUp(StoreThumbsUp stu);
	
	/**
	 * 取消赞
	 * @param stu
	 */
	void delThumbsUp(StoreThumbsUp stu);
	
	/**
	 * 查询赞的数量
	 * @param storeId
	 * @return
	 */
	Integer countThumbsUp(Integer storeId);

}
