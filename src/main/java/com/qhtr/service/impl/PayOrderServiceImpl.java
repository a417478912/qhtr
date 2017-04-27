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
	public String addOrder(String orderCode, int type, int userId) {
		StoreOrder so = storeOrderService.selectByOrderCode(orderCode);
		PayOrder po = new PayOrder();
		po.setCreateTime(new Date());
		po.setOrderCode(GenerationUtils.getGenerationCode("PO", userId + ""));
		po.setStatus(10);
		po.setUserId(userId);
		po.setPayType(type);
		po.setTotalPrice(so.getResultPrice());
		if (type == 1) {
			int result = payOrderMapper.insert(po);
			if (result == 1) {
				return po.getOrderCode();
			} else {
				return null;
			}
		} else if (type == 2) {
			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String appid = "";
		}
		return null;
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
