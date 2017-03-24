package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.GoodsDto;
import com.github.pagehelper.PageHelper;
import com.qhtr.dao.AttrMapper;
import com.qhtr.dao.GoodsMapper;
import com.qhtr.dao.SkuMapper;
import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.model.Sku;
import com.qhtr.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private AttrMapper attrMapper;
	@Resource
	private SkuMapper skuMapper;
	
	/**
	 * @param sellerId
	 * @param type 类型: 0.全部  1. 新品上架  2. 特卖    3 店长推荐
	 * @return
	 */
	@Override
	public List<Goods> selectGoodsBySellerId(int storeId,int type) {
		Goods goods = new Goods();
			goods.setStoreId(storeId);
			goods.setStatus(2);
		if(type == 0){
		}else if(type == 1){
			goods.setIsNewPullOn(1);
		}else if(type == 2){
			goods.setIsSpecialSelling(1);
		}else if(type == 3){
			goods.setIsRecommendedByStoreManager(1);
		}
		List<Goods> selectByConditions = goodsMapper.selectByConditions(goods);
		return selectByConditions;
	}

	@Override
	public GoodsDto selectGoodsByGoodsId(int goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		Attr attr = new Attr();
		attr.setGoodsId(goodsId);
		List<Attr> attrList = attrMapper.selectByConditions(attr);
		
		Sku sku = new Sku();
		sku.setGoodsId(goodsId);
		List<Sku> skuList = skuMapper.selectByConditions(sku);
		
		GoodsDto dto = new GoodsDto();
		dto.setGoods(goods);
		dto.setAttrList(attrList);
		dto.setSkuList(skuList);
		return dto;
	}

	@Override
	public List<Goods> selectGoodsByCondition1(int page,int number) {
		PageHelper.startPage(page,number);
		return goodsMapper.selectGoodsByCondition1();
	}

	@Override
	public List<Goods> selectGoodsBySearch(String searchContent, int page, int num) {
		PageHelper.startPage(page, num);
		return goodsMapper.selectGoodsBySearch(searchContent);
	}

	@Override
	public List<Goods> selectGoodsByCondition(Goods goods,int page, int number) {
		PageHelper.startPage(page, number);
		return goodsMapper.selectByConditions(goods);
	}
}
