package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.PayOrder;

public interface PayOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    PayOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);

	List<PayOrder> selectByConditions(PayOrder poTem);
}