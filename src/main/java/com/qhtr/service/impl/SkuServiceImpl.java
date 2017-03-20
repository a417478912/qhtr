package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.SkuMapper;
import com.qhtr.model.Sku;
import com.qhtr.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService {
	@Resource
	public SkuMapper skuMapper;

	public Sku selectSkuBySkuId(Integer skuId) {
		return skuMapper.selectByPrimaryKey(skuId);
	}

}
