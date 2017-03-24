package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.GoodsOrderMapper;
import com.qhtr.model.GoodsOrder;
import com.qhtr.service.GoodsOrderService;

@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
	@Resource
	public GoodsOrderMapper goodsOrderMapper;
	@Override
	public List<GoodsOrder> selectByCondictions(GoodsOrder goodsOrder) {
		return goodsOrderMapper.selectByConditions(goodsOrder);
	}

	@Override
	public int updateGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
	}

	@Override
	public GoodsOrder selectById(int id) {
		return goodsOrderMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int addGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderMapper.insert(goodsOrder);
	}

}
