package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.RefundOrderMapper;
import com.qhtr.model.RefundOrder;
import com.qhtr.service.RefundService;


@Service
public class RefundServiceImpl implements RefundService {
	@Resource
	public RefundOrderMapper refundOrderMapper;
	
	@Override
	public int addRefund(RefundOrder refundOrder) {
		
		RefundOrder roTem = new RefundOrder();
		roTem.setOrderCode(refundOrder.getOrderCode());
		List<RefundOrder> roList = refundOrderMapper.selectByConditions(roTem);
		if(!roList.isEmpty()){
			return 2;
		}
		
		refundOrder.setStatus(1);
		refundOrder.setCreateTime(new Date());
		return refundOrderMapper.insert(refundOrder);
	}

	@Override
	public int addExpressInfo(String orderCode, String expressName, String expressCode) {
		RefundOrder roTem = new RefundOrder();
		roTem.setOrderCode(orderCode);
		List<RefundOrder> roList = refundOrderMapper.selectByConditions(roTem);
		if(roList.isEmpty() || roList.size() >1){
			return 0;
		}
		RefundOrder ro = roList.get(0);
		if(ro.getStatus() != 2){
			return 2;
		}
		ro.setExpressName(expressName);
		ro.setExpressCode(expressCode);
		ro.setExpressTime(new Date());
		return refundOrderMapper.updateByPrimaryKey(ro);
	}

}
