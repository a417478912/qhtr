package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qhtr.service.SkuService;
import com.qhtr.utils.GenerationUtils;
import com.sell.param.GoodsParam;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private AttrMapper attrMapper;
	@Resource
	private SkuMapper skuMapper;
	@Resource
	public SkuService skuService;
	
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

	@Override
	public int add(GoodsParam goodsParam) {
		Goods goods = new Goods();
		goods.setCreateTime(new Date());
		goods.setCollectNum(0);
		goods.setDetails(goodsParam.getDescript());
		goods.setGoodsClassesId(goodsParam.getGoodsClassId());
		goods.setGoodsCode(GenerationUtils.getGenerationCode("GOODS", goodsParam.getStoreId()+""));
		goods.setName(goodsParam.getName());
		goods.setSellNum(0);
		goods.setStatus(1);
		goods.setStoreId(goodsParam.getStoreId());
		goods.setThumb(goodsParam.getPicture());
		goodsMapper.insert(goods);
		
		List<Sku> skus = goodsParam.getSkus();
		for (Sku sku : skus) {
			sku.setGoodsId(goods.getId());
			skuService.insert(sku);
		}
		return 1;
	}

	@Override
	public List<Goods> selectListByStoreAndType(int storeId, int status) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("storeId", storeId + "");
		map.put("status", status + "");
		return goodsMapper.selectListByStoreAndType(map);
	}

	@Override
	public int delete(int id) {
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		goods.setStatus(3);
		return goodsMapper.updateByPrimaryKey(goods);
	}

	@Override
	public int update(GoodsParam goodsParam) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsParam.getId());
		goods.setDetails(goodsParam.getDescript());
		goods.setGoodsClassesId(goodsParam.getGoodsClassId());
		goods.setGoodsCode(GenerationUtils.getGenerationCode("GOODS", goodsParam.getStoreId()+""));
		goods.setName(goodsParam.getName());
		goods.setStoreId(goodsParam.getStoreId());
		goods.setThumb(goodsParam.getPicture());
		if(goodsParam.getStatus() != 0){
			goods.setStatus(goodsParam.getStatus());
		}
		goodsMapper.updateByPrimaryKey(goods);
		
		List<Sku> skus = goodsParam.getSkus();
		for (Sku sku : skus) {
			if (sku.getId() == 0) {
				sku.setGoodsId(goods.getId());
				skuService.insert(sku);
			} else {
				skuService.update(sku);
			}
		}
		return 0;
	}

	@Override
	public int toTop(int goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(goods.getSort() == null || goods.getSort() == 0){
			goods.setSort(1);
		}else{
			goods.setSort(0);
		}
		goodsMapper.updateByPrimaryKey(goods);
		return 0;
	}
}
