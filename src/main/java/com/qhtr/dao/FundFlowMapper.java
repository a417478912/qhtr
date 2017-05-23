package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.FundFlow;

public interface FundFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundFlow record);

    int insertSelective(FundFlow record);

    FundFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundFlow record);

    int updateByPrimaryKey(FundFlow record);
    
    
    //以下是自定义方法
    /**
	 * 查询卖家的各种资金数
	 * @param storeId
	 * @return
	 */
	List<Map<String, Object>> selectMoneysByStoreId(int storeId);
}