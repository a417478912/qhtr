package com.qhtr.dao;

import com.qhtr.model.SellerAccount;

public interface SellerAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerAccount record);

    int insertSelective(SellerAccount record);

    SellerAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerAccount record);

    int updateByPrimaryKey(SellerAccount record);
    
    //以下是自定义方法
    /**
	 * 通过卖家id,查找账户
	 * @param storeId
	 * @return
	 */
	SellerAccount getAccountByStoreId(int storeId);
}