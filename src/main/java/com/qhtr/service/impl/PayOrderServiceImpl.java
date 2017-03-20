package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.PayOrderMapper;
import com.qhtr.model.PayOrder;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.GenerationUtils;

@Service
public class PayOrderServiceImpl implements PayOrderService {
	@Resource
	public PayOrderMapper payOrderMapper;
	@Resource
	public StoreOrderService storeOrderService;

	@Override
	public String addOrder(int storeOrderId, int type,int userId) {
		PayOrder po = new PayOrder();
		po.setOrderCode(GenerationUtils.getGenerationCode("PO", userId+""));
		StoreOrder so = storeOrderService.selectById(storeOrderId);
		po.setTotalPrice(so.getTotalPrice());
		po.setStatus(1);
		po.setUserId(userId);
		po.setCreateTime(new Date());
		po.setPayType(type);
		int result = payOrderMapper.insert(po);
		if(result == 1){
			return po.getOrderCode();
		}else{
			return null;
		}
	}

	@Override
	public List<PayOrder> selectByConditions(PayOrder poTem) {
		return payOrderMapper.selectByConditions(poTem);
	}

	@Override
	public int insert(PayOrder po) {
		return payOrderMapper.insert(po);
	}

}
