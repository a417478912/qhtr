package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.GoodsListDto_App;
import com.app.dto.SearchDto;
import com.app.dto.StoreGoodsDto;
import com.qhtr.common.Json;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;
import com.qhtr.model.Store;
import com.qhtr.service.GoodsService;
import com.qhtr.service.StoreService;
/**
 * @author Harry
 * @Description 搜索商品的 Controller
 * @date  2017年6月2日
 */
@Controller
@RequestMapping("/app_search")
public class App_SearchController {
	
	@Resource
	public GoodsService goodsService;
	@Resource
	public StoreService storeService;
	
	/**
	 * 通过内容搜索
	 * @param j
	 * @param searchContent
	 * @param type 默认，0 商品&店铺   type=1 商品，type=2 店铺
	 * @param page 页数  
	 * @param num 数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/search")
	public Json search(Json j,@RequestParam String searchContent,@RequestParam(defaultValue="0") int type,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="4") int num){
		
		SearchDto dto = new SearchDto();
		
		if(type == 0){
			List<GoodsDto> goodsDtoList = new ArrayList<>();
			List<Goods> goodsList = goodsService.selectGoodsBySearch(searchContent,page,num);
			for (Goods goods : goodsList) {
				goodsDtoList.add(new GoodsDto(goods));
			}
			dto.setGoodsList(goodsDtoList);
			
			List<StoreGoodsDto> sgDto = this.getStoreGoodsDto(searchContent, page, num);
			dto.setStoreList(sgDto);
		}else if(type == 1){
			
			List<GoodsDto> goodsDtoList = new ArrayList<>(); 
			List<Goods> goodsList = goodsService.selectGoodsBySearch(searchContent,page,num);
			for (Goods goods : goodsList) {
				goodsDtoList.add(new GoodsDto(goods));
			}
			dto.setGoodsList(goodsDtoList);
			
		}else if(type == 2){
			
			List<StoreGoodsDto> sgDto = this.getStoreGoodsDto(searchContent, page, num);
			dto.setStoreList(sgDto);
		}
		j.setData(dto);
		return j;
	}
	
	public List<StoreGoodsDto> getStoreGoodsDto(String searchContent,int page,int num){
		
		List<Store> storeList = storeService.selectStoreBySearch(searchContent,page,num);
		List<StoreGoodsDto> sgDto = new ArrayList<StoreGoodsDto>();
		for (Store store : storeList) {
			
			Goods goodsTem = new Goods();
			goodsTem.setStoreId(store.getId());
			List<Goods> goodsList1 = goodsService.selectGoodsByCondition(goodsTem, page, 5);
			StoreGoodsDto dto1 = new StoreGoodsDto();
			dto1.setGoodsList(goodsList1);
			dto1.setStore(store);
			sgDto.add(dto1);
		}
		return sgDto;
	}
}
