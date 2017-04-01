package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Seller;

public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

	List<Seller> selectByConditions(Seller record);
}