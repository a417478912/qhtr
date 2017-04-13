package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.sell.dto.GoodsClassesDto;

public interface GoodsClassesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsClasses record);

    int insertSelective(GoodsClasses record);

    GoodsClasses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsClasses record);

    int updateByPrimaryKey(GoodsClasses record);
    
    //以下为自定义方法
    List<Goods> selectGoodsByClassId(int classId);
    /**
     * 中间表中删除类别为id的 数据
     * @param id
     * @return
     */
	int deleteFromMidByClassId(int id);
	/**
	 * 条件查找
	 * @param gs
	 * @return
	 */
	List<GoodsClasses> selectByConditions(GoodsClasses gs);
	/**
	 * 条件查找1(查询出来的结果带商品数量)
	 * @param gs
	 * @return
	 */
	List<GoodsClassesDto> selectByConditions1(int storeId);
}