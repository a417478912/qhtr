package com.qhtr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.ActivityMapper;
import com.qhtr.dao.ActivityModelMapper;
import com.qhtr.model.Activity;
import com.qhtr.model.ActivityModel;
import com.qhtr.model.Goods;
import com.qhtr.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Resource
	public ActivityMapper activityMapper;
	@Resource
	public ActivityModelMapper activityModelMapper;
	
	@Override
	public List<Map<String,String>> selectListByStoreId(int storeId) {
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		List<ActivityModel> amList = activityModelMapper.selectAll();
		Map<String,String> map = new HashMap<String,String>();
		for (ActivityModel am : amList) {
			map.put("id", am.getId() + "");
			map.put("name", am.getName());
			Map<String,String> mapParam = new HashMap<String,String>();
			mapParam.put("modelId", am.getId() + "");
			mapParam.put("storeId", storeId + "");
			int count = activityMapper.getGoodsNumByModelId(mapParam);
			map.put("goodsNum", count + "");
		}
		return mapList;
	}

	@Override
	public int addGoods(int[] goodsIds, int storeId, int modelId) {
		for (int gId : goodsIds) {
			Activity activity = new Activity();
			activity.setCreateTime(new Date());
			activity.setGoodsId(gId);
			activity.setStoreId(storeId);
			activity.setModelId(modelId);
			int result1 = activityMapper.insert(activity);
			if(result1 == 0){
				return 0;
			}
		}
		return 1;
	}
	@Override
	public List<Goods> selectByStoreIdAndModelId(int storeId, int modelId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("modelId", modelId + "");
		map.put("storeId", storeId + "");
		return activityMapper.selectByStoreIdAndModelId(map);
	}

	@Override
	public List<Goods> selectByStoreIdAndModelIdExcept(int storeId, int modelId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("modelId", modelId + "");
		map.put("storeId", storeId + "");
		return activityMapper.selectByStoreIdAndModelIdExcept(map);
	}

	@Override
	public int deleteGoods(int goodsIds, int modelId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("modelId", modelId);
		map.put("goodsId", goodsIds);
		return activityMapper.deleteByConditions(map);
	}
}
