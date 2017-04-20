package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Goods;

public interface ActivityService {

	List<Map<String,String>> selectListByStoreId(int storeId);

	/**
	 * 活动中添加/编辑商品
	 * @param goodsIds
	 * @param modelId
	 * @return
	 */
	int addGoods(int[] goodsIds, int storeId, int modelId);
	
	/**
	 * 通过商铺id 和 活动模版id 查找活动商品
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	List<Goods> selectByStoreIdAndModelId(int storeId, int modelId);
	/**
	 * 查询不在此活动中的商品
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	List<Goods> selectByStoreIdAndModelIdExcept(int storeId, int modelId);
}
