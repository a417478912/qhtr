package com.qhtr.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.ExpressMapper;
import com.qhtr.model.Express;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.Store;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.ExpressService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.StoreOrderService;

@Service
public class ExpressServiceImpl implements ExpressService {
	@Resource
	public ExpressMapper expressMapper;
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public StoreOrderService storeOrderService;
	
	@Override
	public int add(String orderId, String name, String code) {
		StoreOrder so = storeOrderService.selectById(Integer.parseInt(orderId.split(",")[0]));
		so.setStatus(30);
		storeOrderService.updateByCondition(so);
		Date date = new Date();
		for (String i : orderId.split(",")) {
			Express express = new Express();
			express.setCode(code);
			express.setName(name);
			express.setGoodsOrderId(Integer.parseInt(i));
			express.setStoreOrderId(so.getId());
			express.setCreateTime(date);
			expressMapper.insert(express);
			
			GoodsOrder go = goodsOrderService.selectById(Integer.parseInt(i));
			if(go.getStatus() == 20){
				go.setStatus(30);
					goodsOrderService.updateGoodsOrder(go);
			}
		}
		
		return 1;
	}

	@Override
	public int add(int orderId, String name, String code) {
		Express express = new Express();
		express.setCode(code);
		express.setName(name);
		express.setGoodsOrderId(0);
		express.setStoreOrderId(orderId);
		express.setCreateTime(new Date());
		expressMapper.insert(express);
		
		storeOrderService.sendOutGoods(orderId);
		return 1;
	}

	@Override
	public Express getByStoreOrderId(int storeOrderId) {
		return expressMapper.getByStoreOrderId(storeOrderId);
	}

	@Override
	public int delete(Integer id) {
		return expressMapper.deleteByPrimaryKey(id);
	}

}
