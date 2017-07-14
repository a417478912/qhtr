package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qhtr.common.Json;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.StoreOrderService;

@Controller
@RequestMapping(value="/app_receiveGoods")// /app_receiveGoods/toReceiveCode.do
public class App_ReceiveGoods {

	@Resource
	private StoreOrderService storeOrderService;
	
	@RequestMapping(value="/toReceiveCode")
	public String toReceiveCode(Json j,HttpServletRequest req,int orderId){
		StoreOrder storeOrder = storeOrderService.selectById(orderId);
		Integer storeId = storeOrder.getStoreId();
		req.setAttribute("name", "111");
		return "ReceiveCode";
	}
}
