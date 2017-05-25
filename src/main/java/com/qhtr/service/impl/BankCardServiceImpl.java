package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.BankCardMapper;
import com.qhtr.dao.BankMapper;
import com.qhtr.dao.WithdrawMapper;
import com.qhtr.model.Bank;
import com.qhtr.model.BankCard;
import com.qhtr.model.FundFlow;
import com.qhtr.model.SellerAccount;
import com.qhtr.model.Withdraw;
import com.qhtr.service.FundFlowService;
import com.qhtr.service.SellerAccountService;

@Service
public class BankCardServiceImpl implements BankCardService {
	@Resource
	public BankMapper bankMapper;
	@Resource
	public BankCardMapper bankCardMapper;
	@Resource
	public WithdrawMapper withdrawMapper;
	@Resource
	public SellerAccountService sellerAccountService;
	@Resource
	public FundFlowService fundFlowService;
	
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

	@Override
	public int insertWithdrawApply(int storeId, int money, Integer bankCardId) {
		//账户减去金额
		SellerAccount sa = sellerAccountService.getByStoreId(storeId);
		if(money > sa.getAccountMoney()){
			return -1;
		}
		sa.setAccountMoney(sa.getAccountMoney() - money);
		sellerAccountService.update(sa);
		
		//增加资金流水
		FundFlow ff = new FundFlow();
		fundFlowService.insertByStore(storeId, 31, -money, "卖家提现");
				
		//添加提现申请
		Withdraw wd = new Withdraw();
		wd.setBankcardId(bankCardId);
		wd.setCreateTime(new Date());
		wd.setPayType(3);
		wd.setStatus(1);
		wd.setStoreId(storeId);
		wd.setTotalPrice(money);
		withdrawMapper.insert(wd);
		
		return 1;
	}

}
