package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.StoreOrderMapper;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.GenerationUtils;

@Service
public class StoreOrderServiceImpl implements StoreOrderService {
	@Resource
	public StoreOrderMapper storeOrderMapper;
	
	@Override
	public String insertByGoodsOrder(GoodsOrder go) {
		StoreOrder so = new StoreOrder();
		so.setOrderCode(GenerationUtils.getGenerationCode("SO", go.getUserId()+""));
		so.setSellerId(go.getSellerId());
		so.setStoreId(go.getStoreId());
		so.setUserId(go.getUserId());
		so.setTotalPrice(go.getPrice() * go.getNum());
		so.setStatus(1);
		so.setCreatTime(new Date());
		int result = storeOrderMapper.insert(so);
		if(result == 1){
			return so.getOrderCode();
		}else{
			return null;
		}
	}

	@Override
	public StoreOrder selectByOrderCode(String soCode) {
		StoreOrder soTem = new StoreOrder();
		soTem.setOrderCode(soCode);
		List<StoreOrder> soList = storeOrderMapper.selectByConditions(soTem);
		if(soList.isEmpty()){
			return null;
		}else{
			return soList.get(0);
		}
	}

	@Override
	public StoreOrder selectById(int storeOrderId) {
		return storeOrderMapper.selectByPrimaryKey(storeOrderId);
	}

	@Override
	public int insert(StoreOrder record) {
		return storeOrderMapper.insert(record);
	}
}
