package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    
    //自定义方法
    List<Store> selectByConditions(Store store);
}