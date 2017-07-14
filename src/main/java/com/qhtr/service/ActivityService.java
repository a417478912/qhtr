package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.model.ActivityModel;
import com.qhtr.model.Goods;

public interface ActivityService {
	ActivityModel getModelByModelId(int id);
	
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
	/**
	 * 删除活动商品
	 * @param goodsIds
	 * @param modelId
	 * @return
	 */
	int deleteGoods(String goodsIds, int modelId);

	/**
	 * 查询活动分类中的商品个数
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	int selectCountByStoreIdAndModelId(int storeId, int modelId);
}
