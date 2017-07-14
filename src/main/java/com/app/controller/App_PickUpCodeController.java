package com.app.controller;

import java.util.Random;

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
@RequestMapping(value="/app_pickUp") // /app_pickUp/getPickUpCode.do
public class App_PickUpCodeController {
	
	@Resource
	private PickUpCodeService pickUpCodeService;
	@Resource
	private StoreOrderService storeOrderService;
	
	@ResponseBody
	@RequestMapping(value="/getPickUpCode")
	public Json getPickUpCode(Json j,int storeOrderId){
		StoreOrder storeOrder = storeOrderService.selectById(storeOrderId);
		
		if (storeOrder == null) {
			
			j.setMessage("无此订单 !");
			j.setCode(0);
			return j;
		}else{
			
			// 如果订单配送类型不是自取或者订单状态不是已付款待自取,返回错误信息
			if (storeOrder.getDistributionType() != 2 || storeOrder.getStatus() != 21) {
				
				j.setMessage("订单状态异常 !");
				return j;
			}else{
				
				PickUpCode code = pickUpCodeService.getPickUpCode(storeOrderId);
				// 如果暂无取货码,生成取货码
				if (code == null) {
					
					StringBuilder sb = new StringBuilder();
					// 生成十位随机数
					Random r = new Random();
					for (int i = 0; i < 10; i++) {
						sb.append(r.nextInt(10) + "");
					}
					String pickUpCode = sb.toString();
					
					PickUpCode upCode = new PickUpCode();
					upCode.setPickUpCode(pickUpCode);
					upCode.setStoreOrderId(storeOrderId);
					
					int result = pickUpCodeService.insertPickUpCode(upCode);
					if (result == -1) {
						j.setCode(0);
						j.setMessage("取货码生成失败 !");
						return j;
					}
					j.setData(pickUpCode);
					j.setMessage("取货码获取成功 !");
					return j;
				}else{
					// 有取货码,直接返回
					String pickUpCode = code.getPickUpCode();
					j.setData(pickUpCode);
					j.setMessage("取货码获取成功 !");
					return j;
				}
			}
		}
	}
}
