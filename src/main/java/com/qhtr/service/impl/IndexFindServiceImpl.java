package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.IndexFindMapper;
import com.qhtr.model.IndexFind;
import com.qhtr.service.IndexFindService;

@Service
public class IndexFindServiceImpl implements IndexFindService {
	@Resource
	public IndexFindMapper indexFindMapper;
	
	@Override
	public List<IndexFind> findAll() {
		return indexFindMapper.findAll();
	}

	@Override
	public IndexFind getById(int id) {
		return indexFindMapper.selectByPrimaryKey(id);
	}

}
