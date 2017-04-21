package com.qhtr.service;

import com.qhtr.model.Sku;

public interface SkuService {

	Sku selectSkuBySkuId(Integer skuId);
	
	int insert(Sku sku);

	int update(Sku sku);
	int updateByPrimaryKeySelective(Sku sku);
	
	int delete(int skuId);

}
