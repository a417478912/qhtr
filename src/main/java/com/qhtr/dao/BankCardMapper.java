package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.BankCard;

public interface BankCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);

	BankCard selectByBankCardQuery(BankCard cardQuery);

	List<BankCard> selectByStoreId(int storeId);
}