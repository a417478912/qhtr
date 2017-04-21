package com.qhtr.service.impl;

import java.util.List;

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

	@Override
	public int insert(Sku sku) {
		return skuMapper.insert(sku);
	}

	@Override
	public int update(Sku sku) {
		return skuMapper.updateByPrimaryKey(sku);
	}

	@Override
	public int delete(int skuId) {
		Sku sku = skuMapper.selectByPrimaryKey(skuId);
		sku.setStatus(2);
		return skuMapper.updateByPrimaryKey(sku);
	}

	@Override
	public int updateByPrimaryKeySelective(Sku sku) {
		return skuMapper.updateByPrimaryKeySelective(sku);
	}

	@Override
	public List<Sku> selectListByGoodsId(Integer id) {
		Sku skuTem = new Sku();
		skuTem.setGoodsId(id);
		return skuMapper.selectByConditions(skuTem);
	}

}
