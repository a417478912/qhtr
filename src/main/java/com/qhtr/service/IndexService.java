package com.qhtr.service;

import java.util.List;

import com.qhtr.model.IndexFind;
import com.qhtr.model.Store;

public interface IndexService {

	List<IndexFind> findAll();

	IndexFind getById(int id);
	
	/**
	 * 通过父类id查询
	 * @param id
	 * @return
	 */
	List<IndexFind> selectListByParentId(int id);
	
	/**
	 * 新店首发
	 * @return
	 */
	List<Store> getNewStoreList();

}
