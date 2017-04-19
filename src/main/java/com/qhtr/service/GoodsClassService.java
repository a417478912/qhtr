package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Goods;
import com.sell.dto.GoodsClassesDto;

public interface GoodsClassService {

	int add(String name, int storeId);

	int delete(int id);

	int update(int storeId, String name,Integer sort);

	List<GoodsClassesDto> selectListByStoreId(int storeId);
	/**
	 * 通过店铺和分类查找商品
	 * @param storeId
	 * @param classId
	 * @return
	 */
	List<Map<String,Object>> getGoodsByClass(int storeId, int classId);
}
