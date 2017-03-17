package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Address;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
    
  //以下是自定义方法
    List<Address> selectByConditions(Address record);
}