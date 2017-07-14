package com.qhtr.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qhtr.dao.ActivityMapper;
import com.qhtr.dao.AttrMapper;
import com.qhtr.dao.GoodsClassesMapper;
import com.qhtr.dao.GoodsMapper;
import com.qhtr.dao.SecondClassMapper;
import com.qhtr.dao.SkuMapper;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Activity;
import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.qhtr.model.SecondClass;
import com.qhtr.model.Sku;
import com.qhtr.service.GoodsService;
import com.qhtr.service.SkuService;
import com.qhtr.utils.DateUtil;
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
	@Resource
	private SecondClassMapper secondClassMapper;
	
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
		
		Integer sc = secondClassMapper.selectSecondClassByGoodsId(goodsId);
		GoodsDto dto = new GoodsDto(goods);
		if (sc != null) {
			dto.setSecondClassId(sc);
		}
		dto.setActivityList(acList);
		dto.setGoodsClasses(gcList);
		dto.setAttrList(attrList);
		dto.setSkuList(skuList);
		return dto;
	}

	@Override
	public List<Goods> selectGoodsListByGoodAround(int page, int number) {
		PageHelper.startPage(page, number);
		return goodsMapper.selectGoodsListByGoodAround();
	}

	@Override
	public List<Goods> selectGoodsBySearch(String searchContent, int page, int num) {
		PageHelper.startPage(page, num);
		return goodsMapper.selectGoodsBySearch(searchContent);
	}

	@Override
	public List<Goods> selectGoodsByCondition(Goods goods, int page, int number) {
		return goodsMapper.selectByConditions(goods);
	}

	@Override
	public int add(GoodsParam goodsParam) {
		if(goodsParam.getThumb() == null || goodsParam.getActivityId() == null || goodsParam.getName() == null || goodsParam.getSku() == null || goodsParam.getStoreId() == 0 || goodsParam.getThumb() ==null){
			return -1;
		}
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
		
		// 商品分类
		int[] cArr = goodsParam.getClassId();
		if (cArr != null) {
			for (int s : cArr) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("goodsId", goods.getId());
				map.put("classId", s);
				goodsClassesMapper.insertGoodsMidGoodsClass(map);
			}
		}
		
		// 二级分类
		Integer secondClassId = goodsParam.getSecondClassId();
			if (secondClassId != null) {
				
				Map<String,Integer> map = new HashMap<>();
				map.put("goodsId", goods.getId());
				map.put("secondClassId", secondClassId);
				secondClassMapper.insertGoodsMidSecondClass(map);
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
	public List<Goods> selectListByStoreAndType(int storeId, int status,int page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("storeId", storeId + "");
		map.put("status", status + "");
		List<Goods> goodsList = goodsMapper.selectListByStoreAndType(map);
		return goodsList;
	}

	@Override
	public int delete(int id,int status) {
		
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		goods.setStatus(status);
			//删除商品需要删除和活动，以及分类的关系
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("goodsId", id);
			activityMapper.deleteByConditions(map);
			goodsClassesMapper.deleteFromMidByGoodsId(id);
			// 删除商品与二级分类之间的关系
			Integer selectSecondClassByGoodsId = secondClassMapper.selectSecondClassByGoodsId(id);
			if (selectSecondClassByGoodsId != null) {
				
				secondClassMapper.deleteFromMidByGoodsId(id);
			}
		
		return goodsMapper.updateByPrimaryKey(goods);
	}

	@Override
	public int update(GoodsParam goodsParam) {
		if(goodsParam.getThumb() == null || goodsParam.getActivityId() == null || goodsParam.getName() == null || goodsParam.getSku() == null || goodsParam.getStoreId() == 0 || goodsParam.getThumb() ==null){
			return -1;
		}
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
		// 活动分类  需要删除之前的活动关系
		Map<String,Integer> map1 = new HashMap<String,Integer>();
		map1.put("goodsId", goodsParam.getId());
		activityMapper.deleteByConditions(map1);
		
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
		
		
		// 商品分类  需要删除之前的分类关系
		goodsClassesMapper.deleteFromMidByGoodsId(goodsParam.getId());
		
		int[] cArr = goodsParam.getClassId();
		if (cArr != null) {
			for (int s : cArr) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("goodsId", goods.getId());
				map.put("classId", s);
				goodsClassesMapper.insertGoodsMidGoodsClass(map);
			}
		}
		
		if (goodsParam.getSecondClassId() != null) {
			
		// 删除之前的二级分类关系
		Integer secondClassId = secondClassMapper.selectSecondClassByGoodsId(goodsParam.getId());
		if (secondClassId != null) {
			
			secondClassMapper.deleteFromMidByGoodsId(goodsParam.getId());
		}
		
		// 添加新的二级分类关系
		Map<String,Integer> map = new HashMap<>();
		map.put("goodsId", goodsParam.getId());
		map.put("secondClassId", goodsParam.getSecondClassId());
		secondClassMapper.insertGoodsMidSecondClass(map);
		
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
					skuService.updateByPrimaryKeySelective(sku);
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
		}/* else {
			goods.setSort(0);
		}*/
		goods.setEditTime(new Date());
		goodsMapper.updateByPrimaryKey(goods);
		return 1;
	}

	@Override
	public int reshelf(Integer goodsId) {
		
		Goods goods = new Goods();
		
		goods.setId(goodsId);
		goods.setStatus(1);
		
		try {
			
			goodsMapper.updateByPrimaryKeySelective(goods);
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		 
	}

	@Override
	public Goods selectGoodsByPrimaryKey(int goodsId) {
		
		return goodsMapper.selectByPrimaryKey(goodsId);
	}

	@Override
	public void soldOut(Integer goodsId) {
		
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		goods.setStatus(2);
		//删除商品需要删除和活动，以及分类的关系
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("goodsId", goodsId);
		activityMapper.deleteByConditions(map);
		goodsClassesMapper.deleteFromMidByGoodsId(goodsId);
		
		goodsMapper.updateByPrimaryKey(goods);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++   下架成功     ++++++++++++++++++++++++++++++++++++++");
	}

	@Override
	public int selectCountByStoreIdAndStatus(int storeId, int status) {
		
		Goods goods = new Goods();
		goods.setStoreId(storeId);
		goods.setStatus(status);
		List<Goods> goodsList = goodsMapper.selectListByStoreIdAndStatus(goods);
		return goodsList.size();
	}
	
	@Override
	public List<Goods> selectListByClassIdAndStoreId(int classId, int storeId) {
		
		GoodsClasses gc = new GoodsClasses();
		gc.setId(classId);
		gc.setStoreId(storeId);
		
		return goodsMapper.selectListByClassIdAndStoreId(gc);
	}

	@Override
	public void updateGoodsByPrimaryKey(Goods goods) {
		goodsMapper.updateByPrimaryKeySelective(goods);
	}

	@Override
	public List<Goods> selectGoodsBySecondClassId(int secondClassId) {
		return goodsMapper.selectGoodsBySecondClassId(secondClassId);
	}

	@Override
	public List<Goods> selectListByStoreId(int storeId) {
		return goodsMapper.selectListByStoreId(storeId);
	}

	@Override
	public List<Goods> selectGoodsListByCategoryId(int categoryId) {
		List<Goods> goodsList = goodsMapper.selectGoodsByCategoryId(categoryId);
		return goodsList;
	}

	@Override
	public List<Goods> selectGoodsByStoreIdAndSecondClassId(int storeId, int secondClassId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("secondClassId", secondClassId);
		List<Goods> goodsList = goodsMapper.selectGoodsByStoreIdAndSecondClassId(map);
		return goodsList;
	}

	@Override
	public List<Goods> getGoodsListNotInSecondClass(int storeId) {
		return goodsMapper.getGoodsListNotInSecondClass(storeId);
	}

	@Override
	public List<Goods> selectNewProductByCategoryId(int categoryId) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<>();
		
		Date startTime = new Date();
		Date endTime = new Date();
		
		map.put("endTime", sdf.format(endTime));
		
		long time = endTime.getTime();
		startTime.setTime(time - (15*24*60*60*1000));
		
		map.put("categoryId", categoryId);
		map.put("startTime",sdf.format(startTime));
		
		for (Entry<String, Object> mEntry : map.entrySet()) {
			
			System.out.println("key : " + mEntry.getKey() + ",value : " + mEntry.getValue());
		}
		return goodsMapper.selectNewProductByCategoryId(map);
	}

	@Override
	public List<Goods> selectGoodsByCategoryId(int categoryId) {
		
		List<Goods> goodsList = goodsMapper.selectGoodsByCategoryId(categoryId);
		
		return goodsList;
	}

}