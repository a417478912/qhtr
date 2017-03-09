package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Sku;

public interface SkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sku record);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);
    
    //以下是自定义方法
    List<Sku> selectByConditions(Sku sku);
}