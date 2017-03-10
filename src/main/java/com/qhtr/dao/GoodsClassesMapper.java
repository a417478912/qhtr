package com.qhtr.dao;

import com.qhtr.model.GoodsClasses;

public interface GoodsClassesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsClasses record);

    int insertSelective(GoodsClasses record);

    GoodsClasses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsClasses record);

    int updateByPrimaryKey(GoodsClasses record);
}