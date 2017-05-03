package com.qhtr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qhtr.model.PayOrder;

public interface PayOrderService {
	/**
	 * 创建支付订单，返回支付单号（支付宝支付)
	 * @param goodsOrderId
	 * @return
	 */
	String addOrder(String orderCode,int userId);
	/**
	 * 创建支付订单，返回支付单号（微信支付)
	 * @param orderCode
	 * @param userId
	 * @param request
	 * @return
	 */
	String addOrder(String orderCode,int userId,HttpServletRequest request,HttpServletResponse response);

	List<PayOrder> selectByConditions(PayOrder poTem);

	int insert(PayOrder po);
	
	/**
	 * 通过单号查找订单
	 * @param code
	 * @return
	 */
	PayOrder selectByOrderCode(String code);
	
	int updateByConditions(PayOrder payOrder);
	int update(PayOrder payOrder);
}
