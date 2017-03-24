package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CouponTemplateMapper;
import com.qhtr.model.CouponTemplate;
import com.qhtr.service.CouponTemplateService;

@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {
	@Resource
	public CouponTemplateMapper couponTemplateMapper;

	@Override
	public CouponTemplate getById(Integer couponId) {
		return couponTemplateMapper.selectByPrimaryKey(couponId);
	}
}
