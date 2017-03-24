package com.app.param;

import java.util.List;

import com.app.dto.StoreOrderDto1;
/**
 * 购物车提交订单需要的参数
 * @author wjx
 *
 * @date  2017年3月24日
 */
public class Param1 {
	public String orderCode;
	public String userRemark;
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
}
