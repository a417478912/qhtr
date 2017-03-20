package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.StoreOrder;

public interface StoreOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreOrder record);

    int insertSelective(StoreOrder record);

    StoreOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreOrder record);

    int updateByPrimaryKey(StoreOrder record);
    
    //自定义方法
    List<StoreOrder> selectByConditions(StoreOrder record);
}