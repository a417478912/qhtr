package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.BankCardMapper;
import com.qhtr.dao.BankMapper;
import com.qhtr.model.Bank;
import com.qhtr.model.BankCard;

@Service
public class BankCardServiceImpl implements BankCardService {
	@Resource
	public BankMapper bankMapper;
	@Resource
	public BankCardMapper bankCardMapper;
	
	@Override
	public List<Bank> getBankList() {
		return bankMapper.getBankList();
	}

	@Override
	public int insert(BankCard bankCard) {
		return bankCardMapper.insert(bankCard);
	}

	@Override
	public int delete(int id) {
		return bankCardMapper.deleteByPrimaryKey(id);
	}

}
