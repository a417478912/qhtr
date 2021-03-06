package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CollectMapper;
import com.qhtr.dao.GoodsMapper;
import com.qhtr.dao.StoreMapper;
import com.qhtr.dto.CollectDto;
import com.qhtr.model.Collect;
import com.qhtr.model.Goods;
import com.qhtr.model.Store;
import com.qhtr.service.CollectService;
import com.qhtr.service.StoreService;


@Service("CollectService")
public class CollectServiceImpl implements CollectService{
	@Resource
	public CollectMapper collectMapper;
	@Resource
	public StoreMapper storeMapper;
	@Resource
	public GoodsMapper goodsMapper;
	
	@Override
	public List<CollectDto> selectByUserid(int userId) {
		return collectMapper.selectByUserid(userId);
	}

	@Override
	public int addCollect(int userId, int goodsId) {
		//检查是否已经收藏
		int isCollect = this.selectIsCollect(userId,goodsId);
		if(isCollect != 0){
			return -1;
		}
		
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		Store store = storeMapper.selectByPrimaryKey(goods.getStoreId());
		Collect collect = new Collect();
		collect.setCategoryId(store.getCategoryId());
		collect.setCreateTime(new Date());
		collect.setGoodsId(goodsId);
		collect.setUserId(userId);
		collectMapper.insert(collect);
		return collect.getId();
	}

	@Override
	public int deleteCollect(int collectId) {
		return collectMapper.deleteByPrimaryKey(collectId);
	}

	@Override
	public List<Map<String, Object>> selectByRecentCollect(int userId, int categoryId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		if(categoryId != 0){
			map.put("categoryId", categoryId);
		}
		return collectMapper.selectByRecentCollect(map);
	}

	@Override
	public int selectIsCollect(int userId, int goodsId) {
		//检查是否已经收藏
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("goodsId", goodsId);
		List<Collect> list = collectMapper.selectByConditions(map);
		if(!list.isEmpty()){
			return list.get(0).getId();
		}
		return 0;
	}
}
