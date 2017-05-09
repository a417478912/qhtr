package com.qhtr.dao;

import com.qhtr.model.FundFlow;

public interface FundFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundFlow record);

    int insertSelective(FundFlow record);

    FundFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundFlow record);

    int updateByPrimaryKey(FundFlow record);
}