package com.qhtr.service;

import java.util.List;

import com.sell.dto.GoodsClassesDto;

public interface GoodsClassService {

	int add(String name, int storeId);

	int delete(int id);

	int update(int storeId, String name,Integer sort);

	List<GoodsClassesDto> selectListByStoreId(int storeId);
}
