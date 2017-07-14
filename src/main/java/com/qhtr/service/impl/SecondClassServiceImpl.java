package com.qhtr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.GoodsMapper;
import com.qhtr.dao.SecondClassMapper;
import com.qhtr.model.SecondClass;
import com.qhtr.service.GoodsService;
import com.qhtr.service.SecondClassService;

@Service
public class SecondClassServiceImpl implements SecondClassService{

	@Resource
	private SecondClassMapper secondClassMapper;
	@Resource
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Map<String, Object>> getSecondClassListByCategoryId(int categoryId,int storeId) {
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		List<SecondClass> list = secondClassMapper.getSecondClassListByCategoryId(categoryId);
		if (!list.isEmpty()) {
			
			for (SecondClass secondClass : list) {
				
				Map<String, Object> map = new HashMap<>();
				map.put("secondClassId", secondClass.getId());
				map.put("secondClassName", secondClass.getName());
				
				// 查询各个二级分类中的商品数量
				Map<String, Integer> param = new HashMap<>();
				param.put("secondClassId", secondClass.getId());
				param.put("storeId", storeId);
				Integer count = goodsMapper.selectCountBySecondClassIdAndStoreId(param);
				map.put("goodsNum", count);
				
				mapList.add(map);
			}
		}
		
		return mapList;
	}
	
	@Override
	public SecondClass selectSecondClassByPrimaryKey(int secondClassId) {
		
		return secondClassMapper.selectSecondClassByPrimaryKey(secondClassId);
	}

	@Override
	public int deleteFromSecondClass(int[] goodsIds) {
		
		try {
			for (int goodsId : goodsIds) {
				secondClassMapper.deleteFromMidByGoodsId(goodsId);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int buildRelationshipBatch(int[] goodsIds, int secondClassId) {
		
		try {
			for (int goodsId : goodsIds) {
				
				Map<String , Integer> map = new HashMap<>();
				
				map.put("goodsId", goodsId);
				map.put("secondClassId", secondClassId);
				
				secondClassMapper.insertGoodsMidSecondClass(map);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
