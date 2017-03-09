package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.AttrMapper;
import com.qhtr.dao.GoodsMapper;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private AttrMapper attrMapper;
	
	@Override
	public List<Goods> selectGoodsBySellerId(int sellerId) {
		Goods goods = new Goods();
		goods.setSellerId(sellerId);
		goods.setStatus(2);
		List<Goods> selectByConditions = goodsMapper.selectByConditions(goods);
		return selectByConditions;
	}

	@Override
	public GoodsDto selectGoodsByGoodsId(int goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		Attr attr = new Attr();
		attr.setGoodsId(goodsId);
		List<Attr> attrList = attrMapper.selectByConditions(attr);
		GoodsDto dto = new GoodsDto();
		dto.setGoods(goods);
		dto.setAttrList(attrList);
		return dto;
	}

}
