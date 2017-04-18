package com.qhtr.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.ExpressMapper;
import com.qhtr.model.Express;
import com.qhtr.service.ExpressService;

@Service
public class ExpressServiceImpl implements ExpressService {
	@Resource
	public ExpressMapper expressMapper;
	
	@Override
	public int add(int orderId, String name, String code) {
		Express express = new Express();
		express.setCode(code);
		express.setName(name);
		express.setStoreOrderId(orderId);
		express.setCreateTime(new Date());
		return expressMapper.insert(express);
	}

}
