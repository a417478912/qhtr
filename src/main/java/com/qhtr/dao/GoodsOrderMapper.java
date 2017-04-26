package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.GoodsOrder;

public interface GoodsOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOrder record);

    int insertSelective(GoodsOrder record);

    GoodsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsOrder record);

    int updateByPrimaryKey(GoodsOrder record);
    
  //自定义方法
    List<GoodsOrder> selectByConditions(GoodsOrder record);
    
    /**
     * 卖家查询商品订单列表
     * @param map
     * @return
     */
	List<Map<String, Object>> selectMapByConditions(Map map);
}