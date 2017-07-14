package com.sell.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.PickUpCode;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.PickUpCodeService;
import com.qhtr.service.StoreOrderService;

@Controller
@RequestMapping(value="/sell_pickUpCode")
public class Sell_PickUpCodeController {

	@Resource
	private PickUpCodeService pickUpCodeService;
	@Resource
	private StoreOrderService storeOrderService;
	
	@ResponseBody
	@RequestMapping(value="/getStoreOrderIdByPickUpCode")
	public Json getStoreOrderIdByPickUpCode(Json j,String pickUpCode,int storeId){
		
		PickUpCode code = pickUpCodeService.getStoreOrderIdByPickUpCode(pickUpCode);
		if (code == null) {
			
			j.setMessage("该取货码无对应的订单信息 !");
			return j;
		}else{
			
			StoreOrder storeOrder = storeOrderService.selectById(code.getStoreOrderId());
			if (storeOrder != null) {
				
				Integer storeId2 = storeOrder.getStoreId();
				if (storeId2 != storeId) {
					
					j.setCode(0);
					j.setMessage("该取货码对应的订单不是这家店铺 !");
					return j;
				}else{
					
					Map<String, Integer> map = new HashMap<>();
					map.put("storeOrderId", code.getStoreOrderId());
					j.setData(map);
					j.setMessage("获取成功 !");
					return j;
				}
			}else{
				
				j.setCode(0);
				j.setMessage("该取货码无对应的订单信息 !");
				return j;
			}
		}
	}
}
