package com.qhtr.service;

import java.util.List;

import com.qhtr.model.Coupon;

public interface CouponService {
	List<Coupon> selectByConditions(Coupon coupon);
}
