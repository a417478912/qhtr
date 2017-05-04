package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.IndexFindMapper;
import com.qhtr.dao.StoreMapper;
import com.qhtr.model.IndexFind;
import com.qhtr.model.Store;
import com.qhtr.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {
	@Resource
	public IndexFindMapper indexFindMapper;
	@Resource
	public StoreMapper storeMapper;
	
	@Override
	public List<IndexFind> findAll() {
		return indexFindMapper.findAll();
	}

	@Override
	public IndexFind getById(int id) {
		return indexFindMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<IndexFind> selectListByParentId(int id) {
		return indexFindMapper.selectListByParentId(id);
	}

	@Override
	public List<Store> getNewStoreList() {
		return storeMapper.getNewStoreList();
	}

}
