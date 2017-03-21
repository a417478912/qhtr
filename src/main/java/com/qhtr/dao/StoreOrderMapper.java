package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.app.dto.StoreOrderDto;
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
    /**
     * 用户查找订单/买家
     * @param map
     * @return
     */
    List<StoreOrderDto> selectByUser(Map<String,Object> map);
}