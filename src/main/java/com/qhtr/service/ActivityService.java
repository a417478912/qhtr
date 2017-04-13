package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Goods;

public interface ActivityService {

	List<Map<String,String>> selectListByStoreId(int storeId);

	int addGoods(int[] goodsIds, int storeId, int modelId);
	
	/**
	 * 通过商铺id 和 活动模版id 查找活动商品
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	List<Goods> selectByStoreIdAndModelId(int storeId, int modelId);

}
