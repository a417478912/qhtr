package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Store;
import com.qhtr.service.StoreService;

@Controller
@RequestMapping("/app_store")
public class App_StoreController {
	@Resource
	public StoreService storeService;
	
	/**
	 * 通过地理位置获取店铺
	 * @param j
	 * @param location
	 * @param distance
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStoresByDistance")
	public Json getStoresByDistance(Json j,@RequestParam String location,Integer distance){
		if(distance == null || distance == 0){
			distance = 1000;
		}
		List<Store> stores = storeService.getStoresByDistance(location, distance);
		j.setData(stores);
		return j;
	}
}
