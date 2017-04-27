package com.qhtr.dao;

import com.qhtr.model.Express;

public interface ExpressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Express record);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);
    /**
     * 通过订单id 查找
     * @param storeOrderId
     * @return
     */
	Express getByStoreOrderId(int storeOrderId);
}