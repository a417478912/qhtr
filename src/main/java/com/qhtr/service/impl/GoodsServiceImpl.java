package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.dto.GoodsDto;
import com.github.pagehelper.PageHelper;
import com.qhtr.dao.ActivityMapper;
import com.qhtr.dao.AttrMapper;
import com.qhtr.dao.GoodsClassesMapper;
import com.qhtr.dao.GoodsMapper;
import com.qhtr.dao.SkuMapper;
import com.qhtr.model.Activity;
import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.qhtr.model.Sku;
import com.qhtr.service.ActivityService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.SkuService;
import com.qhtr.utils.GenerationUtils;
import com.sell.param.GoodsParam;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private GoodsClassesMapper goodsClassesMapper;
	@Resource
	private AttrMapper attrMapper;
	@Resource
	private SkuMapper skuMapper;
	@Resource
	public SkuService skuService;
	@Resource
	public ActivityMapper activityMapper;

	@Override
	public GoodsDto selectGoodsByGoodsId(int goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		Attr attr = new Attr();
		attr.setGoodsId(goodsId);
		List<Attr> attrList = attrMapper.selectByConditions(attr);

		Sku sku = new Sku();
		sku.setGoodsId(goodsId);
		List<Sku> skuList = skuMapper.selectByConditions(sku);

		List<GoodsClasses> gcList = goodsClassesMapper.selectClassByGoodsId(goodsId);
		
		List<Map<String,Object>> acList = activityMapper.selectByGoodsId(goodsId);
		GoodsDto dto = new GoodsDto();
		dto.setActivityList(acList);
		dto.setGoodsClasses(gcList);
		dto.setGoods(goods);
		dto.setAttrList(attrList);
		dto.setSkuList(skuList);
		return dto;
	}

	@Override
	public List<Goods> selectGoodsByCondition1(int page, int number) {
		PageHelper.startPage(page, number);
		return goodsMapper.selectGoodsByCondition1();
	}

	@Override
	public List<Goods> selectGoodsBySearch(String searchContent, int page, int num) {
		PageHelper.startPage(page, num);
		return goodsMapper.selectGoodsBySearch(searchContent);
	}

	@Override
	public List<Goods> selectGoodsByCondition(Goods goods, int page, int number) {
		PageHelper.startPage(page, number);
		return goodsMapper.selectByConditions(goods);
	}

	@Override
	public int add(GoodsParam goodsParam) {
		Goods goods = new Goods();
		goods.setCollectNum(0);
		goods.setCreateTime(new Date());
		goods.setDetailPictures(goodsParam.getDetail_pictures());
		goods.setDetails(goodsParam.getDescript());
		goods.setGoodsCode(GenerationUtils.getGenerationCode("GOODS", goodsParam.getStoreId() + ""));
		goods.setName(goodsParam.getName());
		goods.setResultPicture(goodsParam.getResultPicture());
		goods.setSellNum(0);
		goods.setStatus(1);
		goods.setStoreId(goodsParam.getStoreId());
		goods.setThumb(goodsParam.getThumb());

		goodsMapper.insert(goods);
		// 活动分类
		int[] sArr = goodsParam.getActivityId();
		if (sArr != null) {
			for (int s1 : sArr) {
				Activity activity = new Activity();
				activity.setCreateTime(new Date());
				activity.setGoodsId(goods.getId());
				activity.setModelId(s1);
				activity.setStoreId(goodsParam.getStoreId());
				activityMapper.insert(activity);
			}
		}
		int[] cArr = goodsParam.getClassId();
		// 商品分类
		if (cArr != null) {
			for (int s : cArr) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("goodsId", goods.getId());
				map.put("classId", s);
				goodsClassesMapper.insertGoodsMidGoodsClass(map);
			}
		}
		// sku
		String skuStr = goodsParam.getSku();
		List<Sku> skus = JSONArray.parseArray(skuStr, Sku.class);
		if (!skus.isEmpty()) {
			for (Sku sku : skus) {
				sku.setGoodsId(goods.getId());
				skuService.insert(sku);
			}
		}
		return goods.getId();
	}

	@Override
	public List<Goods> selectListByStoreAndType(int storeId, int status) {
		Map<String, String> map = new HashMap<String, String>();
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
		Goods goods = new Goods();
		goods.setId(goodsParam.getId());
		goods.setDetailPictures(goodsParam.getDetail_pictures());
		goods.setDetails(goodsParam.getDescript());
		goods.setGoodsCode(GenerationUtils.getGenerationCode("GOODS", goodsParam.getStoreId() + ""));
		goods.setName(goodsParam.getName());
		goods.setResultPicture(goodsParam.getResultPicture());
		goods.setStoreId(goodsParam.getStoreId());
		goods.setThumb(goodsParam.getThumb());

		goodsMapper.updateByPrimaryKeySelective(goods);
		// 活动分类  需要删除之前的分类关系
		int[] sArr = goodsParam.getActivityId();
		if (sArr != null) {
			for (int s1 : sArr) {
				Map<String,Integer> map1 = new HashMap<String,Integer>();
				map1.put("storeId", goodsParam.getStoreId());
				map1.put("modelId", s1);
				activityMapper.deleteByStroeIdAndModelId(map1);
				
				Activity activity = new Activity();
				activity.setCreateTime(new Date());
				activity.setGoodsId(goods.getId());
				activity.setModelId(s1);
				activity.setStoreId(goodsParam.getStoreId());
				activityMapper.insert(activity);
			}
		}
		int[] cArr = goodsParam.getClassId();
		// 商品分类  需要删除之前的分类关系
		if (cArr != null) {
			for (int s : cArr) {
				Map<String,Integer> map2 = new HashMap<String,Integer>();
				map2.put("goodsId", goodsParam.getStoreId());
				map2.put("classId", s);
				goodsClassesMapper.deleteGoodsByClass(map2);
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("goodsId", goods.getId());
				map.put("classId", s);
				goodsClassesMapper.insertGoodsMidGoodsClass(map);
			}
		}
		// sku
		String skuStr = goodsParam.getSku();
		List<Sku> skus = JSONArray.parseArray(skuStr, Sku.class);
		if (!skus.isEmpty()) {
			for (Sku sku : skus) {
				if(sku.getId() == null || sku.getId() == 0){
					sku.setGoodsId(goods.getId());
					skuService.insert(sku);
				}else{
					skuService.update(sku);
				}
			}
		}
		return goodsParam.getId();
	}

	@Override
	public int toTop(int goodsId) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if (goods.getSort() == null || goods.getSort() == 0) {
			goods.setSort(1);
		} else {
			goods.setSort(0);
		}
		goodsMapper.updateByPrimaryKey(goods);
		return 0;
	}
}
