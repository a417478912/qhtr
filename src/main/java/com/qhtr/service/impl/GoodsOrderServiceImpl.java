package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.GoodsOrderMapper;
import com.qhtr.model.GoodsOrder;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.utils.GenerationUtils;

@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
	@Resource
	public GoodsOrderMapper goodsOrderMapper;
	
	@Override
	public GoodsOrder addGoodsOrder(GoodsOrder goodsOrder) {
		goodsOrder.setOrderCode(GenerationUtils.getGenerationCode("GO", goodsOrder.getUserId()+""));
		goodsOrder.setCreateTime(new Date());
		goodsOrder.setStatus(1);
		int result = goodsOrderMapper.insert(goodsOrder);
		if(result == 1){
			return goodsOrder;
		}else{
			return null;
		}
	}
	
	@Override
	public List<GoodsOrder> selectByCondictions(GoodsOrder goodsOrder) {
		return goodsOrderMapper.selectByConditions(goodsOrder);
	}

	@Override
	public int updateGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
	}
	
}
