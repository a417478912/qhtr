package com.qhtr.dao;

import com.qhtr.model.CouponTemplate;

public interface CouponTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponTemplate record);

    int insertSelective(CouponTemplate record);

    CouponTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponTemplate record);

    int updateByPrimaryKey(CouponTemplate record);
}