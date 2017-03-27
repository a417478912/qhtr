package com.app.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.RefundOrder;
import com.qhtr.service.RefundService;

@Controller
@RequestMapping("/app_refund")
public class App_RefundController {
	@Resource
	public RefundService refundService;
	
	@ResponseBody
	@RequestMapping(value="/addRefund")
	public Json addRefund(Json j,RefundOrder refundOrder){
		if(StringUtils.isBlank(refundOrder.getOrderCode())){
			j.setMessage("订单号不能为空!");
			return j;
		}
		int result = refundService.addRefund(refundOrder);
		if(result == 1){
			j.setMessage("申请退款成功!");
		}else if(result == 0){
			j.setCode(0);
			j.setMessage("申请失败!");
		}else if(result == 2){
			j.setCode(0);
			j.setMessage("此订单还有未完成的处理!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/addExpressInfo")
	public Json addExpressInfo(Json j,String orderCode,String expressName,String expressCode){
		int result = refundService.addExpressInfo(orderCode,expressName,expressCode);
		if(result == 1){
			j.setMessage("成功!");
		}else if(result == 0){
			j.setCode(0);
			j.setMessage("失败!");
		}else if(result == 2){
			j.setCode(0);
			j.setMessage("状态异常!");
		}
		return j;
	}
}
