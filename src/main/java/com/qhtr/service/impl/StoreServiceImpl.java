package com.qhtr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.qhtr.dao.StoreMapper;
import com.qhtr.model.Store;
import com.qhtr.service.StoreService;
import com.qhtr.utils.DistributionUtils;

@Service
public class StoreServiceImpl implements StoreService {
	@Resource
	private StoreMapper storeMapper;
	@Override
	public Store getStoreBysSoreId(Integer storeId) {
		return storeMapper.selectByPrimaryKey(storeId);
	}
	@Override
	public Store getStoreBySellerId(Integer sellerId) {
		Store store = new Store();
		store.setSellerId(sellerId);
		List<Store> selectByConditions = storeMapper.selectByConditions(store);
		if(selectByConditions == null){
			return null;
		}else{
			return selectByConditions.get(0);
		}
	}
	@Override
	public List<Store> getStoresByDistance(String location, int distance) {
		if(distance == 0){
			distance = 1000;
		}
		List<Store> allStores = storeMapper.selectByConditions(new Store());
		List<Store> stores = new ArrayList<Store>();
		for (Store store : allStores) {
			String[] s = store.getLocation().split(",");
			String[] s1 = location.split(",");
			if( DistributionUtils.getDistance(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s1[0]), Double.parseDouble(s1[1])) <= distance){
				stores.add(store);
			}
		}
		return stores;
	}
	@Override
	public List<Store> getStoresByType(int type, int page, int num) {
		Store s = new Store();
		s.setCategoreId(type);
		PageHelper.startPage(page, num);
		return storeMapper.selectByConditions(s);
	}
	@Override
	public List<Store> getHotStores(int page, int num) {
		PageHelper.startPage(page, num);
		return storeMapper.selectHotStore();
	}
	@Override
	public List<Store> selectStoreBySearch(String searchContent, int page, int num) {
		PageHelper.startPage(page, num);
		return storeMapper.selectStoreBySearch(searchContent);
	}

}
