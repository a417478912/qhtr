package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CouponMapper;
import com.qhtr.model.Coupon;
import com.qhtr.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
	@Resource
	public CouponMapper couponMapper;

	@Override
	public List<Coupon> selectByConditions(Coupon coupon) {
		return couponMapper.selectByConditions(coupon);
	}

}
