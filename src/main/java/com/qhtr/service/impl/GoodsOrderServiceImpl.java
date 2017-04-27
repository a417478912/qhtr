package com.qhtr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
		return goodsOrderMapper.updateByPrimaryKey(goodsOrder);
	}

	@Override
	public GoodsOrder selectById(int id) {
		return goodsOrderMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int addGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderMapper.insert(goodsOrder);
	}

	@Override
	public List<Map<String, Object>> selectMapByConditions(Map map) {
		Page<?> startPage = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), 10);
		List<Map<String, Object>> list = goodsOrderMapper.selectMapByConditions(map);
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("total",startPage.getTotal());
		list.add(map1);
		return list;
	}

	@Override
	public int selectCountByConditions(Map<String, Object> map) {
		return goodsOrderMapper.selectCountByConditions(map);
	}
}
