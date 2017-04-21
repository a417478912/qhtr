package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.dto.StoreDto;
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
	public Json getStoresByDistance(Json j,@RequestParam String longitude,@RequestParam String latitude,@RequestParam(defaultValue="1000")String accuracy){
		List<StoreDto> stores = storeService.getStoresByDistance(longitude,latitude,accuracy);
		j.setData(stores);
		return j;
	}
	
	/**
	 * 通过id获取店铺信息
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStoreById")
	public Json getStoreById(Json j,@RequestParam int id){
		Store storeTem = new Store();
		storeTem.setId(id);
		StoreDto dto = storeService.getStoreById(id);
		j.setData(dto);
		return j;
	}
}
