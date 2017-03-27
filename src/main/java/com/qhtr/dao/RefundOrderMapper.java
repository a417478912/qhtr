package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.RefundOrder;

public interface RefundOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RefundOrder record);

    int insertSelective(RefundOrder record);

    RefundOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundOrder record);

    int updateByPrimaryKey(RefundOrder record);
    
  //以下为自定义内容
    List<RefundOrder> selectByConditions(RefundOrder record);
}