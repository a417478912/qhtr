package com.qhtr.service;

import java.util.List;
import java.util.Map;

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
	/**
	 * 查找不在分类中的商品
	 * @param storeId
	 * @param classId
	 * @return
	 */
	List<Map<String, Object>> getGoodsByClassExcept(int storeId, int classId);

	int addGoodsByClass(int[] goodsIds, int classId);
}
