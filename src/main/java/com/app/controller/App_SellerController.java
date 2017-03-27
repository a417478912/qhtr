package com.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.StoreDto;
import com.qhtr.common.Json;
import com.qhtr.model.Store;
import com.qhtr.service.SellerService;
import com.qhtr.service.StoreService;

@Controller
@RequestMapping("/app_seller")
public class App_SellerController {
	@Resource
	private SellerService sellerService;
	@Resource
	private StoreService storeService;
	
	/**
	 * 通过商铺id ，查找商铺
	 * @param j
	 * @param sellerId
	 * @return
	 */
	@RequestMapping(value="/getStoreBysSoreId")
	@ResponseBody
	public Json getStoreBysSoreId(Json j,@RequestParam Integer storeId){
		Store s = storeService.getStoreBysSoreId(storeId);
		if(s == null){
			j.setCode(0);
		}else{
			j.setData(new StoreDto(s));
		}
		return j;
	}
	
	/**
	 * 通过卖家id ，查找商铺
	 * @param j
	 * @param sellerId
	 * @return
	 */
	@RequestMapping(value="/getStoreBysSellerId")
	@ResponseBody
	public Json getStoreBysSellerId(Json j,@RequestParam Integer sellerId){
		Store s = storeService.getStoreBySellerId(sellerId);
		if(s == null){
			j.setCode(0);
		}else{
			j.setData(new StoreDto(s));
		}
		return j;
	}
}
