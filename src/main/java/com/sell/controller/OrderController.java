package com.sell.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.StoreOrderService;

@Controller
@RequestMapping("/sell_order")
public class OrderController {
	@Resource
	public StoreOrderService storeOrderService;
	
	@ResponseBody
	@RequestMapping("/getOrderList")
	public Json getOrderList(Json j,@RequestParam int storeId,@RequestParam int status){
		StoreOrder so = new StoreOrder();
		so.setStoreId(storeId);
		so.setStatus(status);
		List<Map<String,Object>> list = storeOrderService.selectMapByConditions(so);
		j.setData(list);
		return j;
	}
}
