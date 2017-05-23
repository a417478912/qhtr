package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.FundFlowMapper;
import com.qhtr.model.FundFlow;
import com.qhtr.service.FundFlowService;
import com.qhtr.service.PayService;

@Service
public class FundFlowServiceImpl implements FundFlowService {
	@Resource
	public FundFlowMapper fundFlowMapper;
	
	@Override
	public int insertByUser(int userId, int type, int changeMoney, String reason) {
		FundFlow ff = new FundFlow();
		ff.setUserId(userId);
		ff.setChangeMoney(changeMoney);
		ff.setType(type);
		ff.setCreateTime(new Date());
		ff.setReason(reason);
		ff.setStoreId(0);
		return fundFlowMapper.insert(ff);
	}

	@Override
	public int insertByStore(int storeId, int type, int changeMoney, String reason) {
		FundFlow ff = new FundFlow();
		ff.setUserId(0);
		ff.setStoreId(storeId);
		ff.setChangeMoney(changeMoney);
		ff.setType(type);
		ff.setCreateTime(new Date());
		ff.setReason(reason);
		return fundFlowMapper.insert(ff);
	}

	@Override
	public List<Map<String, Object>> selectMoneysByStoreId(int storeId) {
		return fundFlowMapper.selectMoneysByStoreId(storeId);
	}
}
