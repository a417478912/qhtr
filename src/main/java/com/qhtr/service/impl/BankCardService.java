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
}
