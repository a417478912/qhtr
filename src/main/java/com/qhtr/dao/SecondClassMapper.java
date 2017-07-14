package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.SecondClass;

public interface SecondClassMapper {

	List<SecondClass> getSecondClassListByCategoryId(int categoryId);

	/**
	 * 使商品和二级分类简历关系
	 * @param map
	 */
	void insertGoodsMidSecondClass(Map<String, Integer> map);

	/**
	 * 删除商品和二级分类之间的关系
	 * @param id
	 */
	void deleteFromMidByGoodsId(int id);

	
	/**
	 * 通过二级分类的id查询二级分类
	 * @param secondClassId
	 * @return
	 */
	SecondClass selectSecondClassByPrimaryKey(int secondClassId);

	/**
	 * 通过商品id查询二级分类id
	 * @param goodsId
	 * @return
	 */
	Integer selectSecondClassByGoodsId(int goodsId);

}
