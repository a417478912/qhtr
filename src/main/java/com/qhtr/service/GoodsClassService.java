package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.sell.dto.GoodsClassesDto;

public interface GoodsClassService {
	GoodsClasses getById(int id);

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
	/**
	 * 从分类中删除商品
	 * @param goodsIds
	 * @param classId
	 * @return
	 */
	int deleteGoodsByClass(String goodsIds, int classId);
	/**
	 * 买家版，通过店铺id和分类id查询商品列表
	 */
	List<Goods> getGoodsByClass_App(int classId);

	/**
	 * 查询分类中的商品数量
	 * @param goodsClass1
	 * @return
	 */
	int selectCountByClassIdAndStoreId(GoodsClasses goodsClass1);

	
}
