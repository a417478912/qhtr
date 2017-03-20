package com.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.GoodsOrder;
import com.qhtr.service.GoodsOrderService;

@Controller
@RequestMapping("/app_order")
public class App_OrderController {
	@Resource
	public GoodsOrderService goodsOrderService;
	
	/**
	 * 下单
	 * @param j
	 * @param goodsOrder
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addGoodsOrder")
	public Json addGoodsOrder(Json j,GoodsOrder goodsOrder){
		GoodsOrder go = goodsOrderService.addGoodsOrder(goodsOrder);
		if(go != null){
			j.setData(go);
			j.setMessage("下单成功!");
		}else{
			j.setSuccess(false);
			j.setMessage("下单失败!");
		}
		return j;
	}
	
}
