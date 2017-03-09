package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.StoreMapper;
import com.qhtr.model.Store;
import com.qhtr.service.StoreService;

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

}
