package com.qhtr.service.impl;

import java.util.List;

import com.qhtr.model.Bank;
import com.qhtr.model.BankCard;

public interface BankCardService {
	/**
	 * 查询银行列表
	 * @return
	 */
	List<Bank> getBankList();
	
	int insert(BankCard bankCard);
	
	int delete(int id);
	/**
	 * 添加提现申请
	 * @param storeId
	 * @param money
	 * @param bankCardId
	 * @return
	 */
	int insertWithdrawApply(int storeId, int money, Integer bankCardId);

	/**
	 * 通过id查询银行卡
	 * @param bankCardId
	 * @return
	 */
	BankCard selectBankCardById(Integer bankCardId);

	/**
	 * 通过银行卡id和店铺id查询银行卡
	 * @param cardQuery
	 * @return
	 */
	BankCard selectBankCardByIdAndStoreId(BankCard cardQuery);

	/**
	 * 通过店铺id查询银行卡列表
	 * @param storeId
	 * @return
	 */
	List<BankCard> selectBankCardByStoreId(int storeId);

	/**
	 * 修改银行卡信息
	 * @param bankCard
	 * @return
	 */
	int updateBankCard(BankCard bankCard);
}
