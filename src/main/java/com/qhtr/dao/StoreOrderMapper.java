package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.QueryCount;
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
     * 查询订单列表
     * @param record
     * @return
     */
    List<Map<String,Object>> selectMapByConditions(StoreOrder record);
    /**
     * 用户查找订单/买家
     * @param map
     * @return
     */
    List<com.app.dto.StoreOrderDto_App> selectByUser(Map<String,Object> map);
    /*
     * 查询数量 
     */
    int selectCountByConditions(Map<String,Object> map);
    
    List<Map<String,Object>> selectListByStoreIdAndStatus(StoreOrder record);

    /**
     * 查询某段时间的订单
     * @param qc
     * @return
     */
	List<StoreOrder> selectListByTime(QueryCount qc);

}