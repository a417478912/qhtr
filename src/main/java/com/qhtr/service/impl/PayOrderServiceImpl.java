package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.PayOrderMapper;
import com.qhtr.model.PayOrder;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;

@Service
public class PayOrderServiceImpl implements PayOrderService {
	@Resource
	public PayOrderMapper payOrderMapper;
	@Resource
	public StoreOrderService storeOrderService;

	@Override
	public String addOrder(String orderCode, int type,int userId) {
		PayOrder po = this.selectByOrderCode(orderCode);
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

	@Override
	public PayOrder selectByOrderCode(String code) {
		PayOrder po = new PayOrder();
		po.setOrderCode(code);
		List<PayOrder> list = payOrderMapper.selectByConditions(po);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public int updateByConditions(PayOrder payOrder) {
		return payOrderMapper.updateByPrimaryKeySelective(payOrder);
	}

	@Override
	public int update(PayOrder payOrder) {
		return payOrderMapper.updateByPrimaryKey(payOrder);
	}

}
