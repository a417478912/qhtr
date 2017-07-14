package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.qhtr.dao.SellerAccountMapper;
import com.qhtr.model.SellerAccount;
import com.qhtr.service.SellerAccountService;

@Service
public class SellerAccountServiceImpl implements SellerAccountService {
	@Resource
	public SellerAccountMapper sellerAccountMapper;
	
	@Override
	public int insert(SellerAccount account) {
		return sellerAccountMapper.insert(account);
	}

	@Override
	public int updateOpenId(int storeId, String openId) {
		
		SellerAccount sa = sellerAccountMapper.getAccountByStoreId(storeId);
		if(StringUtils.isNotBlank(sa.getOpenId())){
			return -1;
		}
		sa.setOpenId(openId);
		return sellerAccountMapper.updateByPrimaryKey(sa);
	}

	@Override
	public SellerAccount getByStoreId(int storeId) {
		return sellerAccountMapper.getAccountByStoreId(storeId);
	}

	@Override
	public int update(SellerAccount account) {
		return sellerAccountMapper.updateByPrimaryKey(account);
	}

	@Override
	public int getCanWithdrawalMoney(int storeId) {
		
		if (sellerAccountMapper.getCanWithdrawalMoney(storeId) == null) {
			return 0;
		}else{
			return sellerAccountMapper.getCanWithdrawalMoney(storeId);
		}
	}

}
