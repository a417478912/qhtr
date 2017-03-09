package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.SellerMapper;
import com.qhtr.model.Seller;
import com.qhtr.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	@Resource
	private SellerMapper sellerMapper;

	@Override
	public Seller getSellerById(int id) {
		return sellerMapper.selectByPrimaryKey(id);
	}

}
