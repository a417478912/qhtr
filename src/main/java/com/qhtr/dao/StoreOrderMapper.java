package com.qhtr.dao;

import com.qhtr.model.StoreOrder;

public interface StoreOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreOrder record);

    int insertSelective(StoreOrder record);

    StoreOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreOrder record);

    int updateByPrimaryKey(StoreOrder record);
}