package com.qhtr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.GoodsClassesMapper;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.qhtr.service.GoodsClassService;
import com.sell.dto.GoodsClassesDto;

@Service
public class GoodsClassServiceImpl implements GoodsClassService {
	@Resource
	public GoodsClassesMapper goodsClassesMapper;
	
	@Override
	public int add(String name, int storeId) {
		GoodsClasses gs = new GoodsClasses();
		gs.setName(name);
		gs.setStoreId(storeId);
		return goodsClassesMapper.insert(gs);
	}

	@Override
	public int delete(int id) {
		int result1 = goodsClassesMapper.deleteFromMidByClassId(id);
		int result2 = goodsClassesMapper.deleteByPrimaryKey(id);
		if(result1 == 1 || result2== 1){
			return 1;
		}
		return 0;
	}

	@Override
	public int update(int id, String name,Integer sort) {
		GoodsClasses gs = new GoodsClasses();
		if(name != null){
			gs.setName(name);
		}
		gs.setId(id);
		if(sort != null){
			gs.setSort(sort);
		}
		return goodsClassesMapper.updateByPrimaryKeySelective(gs);
	}

	@Override
	public List<GoodsClassesDto> selectListByStoreId(int storeId) {
		return goodsClassesMapper.selectByConditions1(storeId);
	}

	@Override
	public List<Map<String,Object>> getGoodsByClass(int storeId, int classId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("storeId", storeId);
		map.put("classId", classId);
		return goodsClassesMapper.getGoodsByClass(map);
	}

	@Override
	public List<Map<String, Object>> getGoodsByClassExcept(int storeId, int classId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("storeId", storeId);
		map.put("classId", classId);
		return goodsClassesMapper.getGoodsByClassExcept(map);
	}

	@Override
	public int addGoodsByClass(int[] goodsIds, int classId) {
		for (int i : goodsIds) {
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("storeId", i);
			map.put("classId", classId);
			int result = goodsClassesMapper.insertGoodsMidGoodsClass(map);
			if(result == 0)
				return 0;
		}
		return 1;
	}

	@Override
	public int deleteGoodsByClass(int goodsIds, int classId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("goodsIds", goodsIds);
		map.put("classId", classId);
		return goodsClassesMapper.deleteGoodsByClass(map);
	}

}
