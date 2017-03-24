package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Coupon;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

	List<Coupon> selectByConditions(Coupon coupon);
}