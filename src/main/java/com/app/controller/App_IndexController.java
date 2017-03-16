package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.IndexDto;
import com.qhtr.common.Json;
import com.qhtr.model.Store;
import com.qhtr.service.GoodsService;
import com.qhtr.service.StoreService;


@Controller
@RequestMapping("/app_index")
public class App_IndexController {
	@Resource
	public GoodsService goodsService;
	@Resource
	public StoreService storeService;
	/**
	 * 附近好货
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsBySellerId")
	public Json getGoodsArount(Json j){
		j.setData(goodsService.selectGoodsByCondition1(1, 4));
		return j;
	}
	
	/**
	 * 首页
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getIndex")
	public Json getIndex(Json j){
		List<Store> storeList1 = storeService.getStoresByType(1, 1, 5);
		List<Store> storeList2 = storeService.getStoresByType(2, 1, 5);
		List<Store> storeList3 = storeService.getStoresByType(3, 1, 5);
		List<Store> storeList4 = storeService.getStoresByType(4, 1, 5);
		IndexDto dto = new IndexDto();
		dto.setGoodsList(goodsService.selectGoodsByCondition1(1, 4));
		dto.setStoreList1(storeList1);
		dto.setStoreList2(storeList2);
		dto.setStoreList3(storeList3);
		dto.setStoreList4(storeList4);
		j.setData(dto);
		return j;
	}
	
	/**
	 * 热店推荐
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getHotStores")
	public Json getHotStores(Json j,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="10") int num){
		List<Store> stores = storeService.getHotStores(page, num);
		j.setData(stores);
		return j;
	}
}
