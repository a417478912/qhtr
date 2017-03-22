package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.StoreOrderDto;
import com.qhtr.dao.StoreOrderMapper;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.GenerationUtils;

@Service
public class StoreOrderServiceImpl implements StoreOrderService {
	@Resource
	public StoreOrderMapper storeOrderMapper;
	@Resource
	public GoodsOrderService goodsOrderService;
	@Override
	public String insertByGoodsOrder(GoodsOrder go) {
		StoreOrder so = new StoreOrder();
		so.setOrderCode(GenerationUtils.getGenerationCode("SO", go.getUserId()+""));
		so.setStoreId(go.getStoreId());
		so.setUserId(go.getUserId());
		so.setTotalPrice(go.getPrice() * go.getNum());
		so.setStatus(1);
		so.setCreateTime(new Date());
		storeOrderMapper.insert(so);
		go.setStoreOrderCode(so.getOrderCode());
		goodsOrderService.addGoodsOrder(go);
		return so.getOrderCode();
		
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

	@Override
	public int updateByCondition(StoreOrder record) {
		return storeOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<StoreOrderDto> getOrdersByUser(int userId, int status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		if(status != 0){
			map.put("status", status);
		}
		return storeOrderMapper.selectByUser(map);
	}
}
