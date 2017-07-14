package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.model.SecondClass;

public interface SecondClassService {

	/**
	 * 通过行业分类获取二级分类列表
	 * @param categoryId
	 * @return
	 */
	List<Map<String, Object>> getSecondClassListByCategoryId(int categoryId,int storeId);

	/**
	 * 通过二级分类id查询二级分类
	 * @param secondClassId
	 * @return
	 */
	SecondClass selectSecondClassByPrimaryKey(int secondClassId);

	/**
	 * 将商品从二级分类中删除
	 * @param goodsIds
	 * @param secondClassId
	 * @return
	 */
	int deleteFromSecondClass(int[] goodsIds);

	/**
	 * 将商品批量的添加进二级分类
	 * @param goodsIds
	 * @param secondClassId
	 * @return
	 */
	int buildRelationshipBatch(int[] goodsIds, int secondClassId);

}
