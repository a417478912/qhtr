package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.dto.GoodsListDto_App;
import com.app.dto.IndexDto;
import com.app.dto.StoreListDto_App;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.model.IndexFind;
import com.qhtr.model.Store;
import com.qhtr.service.GoodsService;
import com.qhtr.service.IndexService;
import com.qhtr.service.StoreService;


@Controller
@RequestMapping("/app_index")
public class App_IndexController {
	@Resource
	public GoodsService goodsService;
	@Resource
	public StoreService storeService;
	@Resource
	public IndexService indexService;
	
	
	
	/**
	 * 上面的横向行业分类栏，查找商品
	 * @param j
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCategoryGoods")
	public Json getCategoryGoods(Json j,@RequestParam int categoryId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//上面的banner,StoreList
				List<StoreListDto_App> storeDtoList = new ArrayList<StoreListDto_App>();
				PageHelper.startPage(1, 4);
				List<Store> storeList = storeService.getAll();
				for (Store store : storeList) {
					storeDtoList.add(new StoreListDto_App(store));
				}
				resultMap.put("bannerList", storeDtoList);
				
		//下面的商品
				List<GoodsListDto_App> goodsDtoList = new ArrayList<GoodsListDto_App>();
				List<Store> storeList1 = storeService.getStoresByType(categoryId, 1, 2);
				for (Store store : storeList1) {
					Goods goodsTem = new Goods();
					goodsTem.setStoreId(store.getId());
					List<Goods> goodsList1 = goodsService.selectGoodsByCondition(goodsTem, 1, 1);
					if(!goodsList1.isEmpty()){
						goodsDtoList.add(new GoodsListDto_App(goodsList1.get(0)));
					}
				}
				resultMap.put("goodsListUp", goodsDtoList);
				
				
				List<GoodsListDto_App> goodsDtoList2 = new ArrayList<GoodsListDto_App>();
				List<Store> storeList2 = storeService.getStoresByType(categoryId, 1,3);
				for (Store store : storeList2) {
					Goods goodsTem = new Goods();
					goodsTem.setStoreId(store.getId());
					List<Goods> goodsList2 = goodsService.selectGoodsByCondition(goodsTem, 1, 1);
					if(!goodsList2.isEmpty()){
						goodsDtoList2.add(new GoodsListDto_App(goodsList2.get(0)));
					}
				}
				resultMap.put("goodsListDown", goodsDtoList);
				
				
				List<GoodsListDto_App> goodsDtoList3 = new ArrayList<GoodsListDto_App>();
				List<Store> storeList3 = storeService.getStoresByType(categoryId, 1,5);
				for (Store store : storeList3) {
					Goods goodsTem = new Goods();
					goodsTem.setStoreId(store.getId());
					List<Goods> goodsList3 = goodsService.selectGoodsByCondition(goodsTem, 1, 1);
					if(!goodsList3.isEmpty()){
						goodsDtoList3.add(new GoodsListDto_App(goodsList3.get(0)));
					}
				}
				resultMap.put("recommendationList", goodsDtoList);
				j.setData(resultMap);
				return j;
	}
	
	
	/**
	 * 附近好货
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsArount")
	public Json getGoodsArount(Json j,@RequestParam(defaultValue="1") int page){
		List<GoodsListDto_App> dtoList = new ArrayList<GoodsListDto_App>();
		List<Goods> goodsList = goodsService.selectGoodsListByGoodAround(page, 4);
		for (Goods goods : goodsList) {
			dtoList.add(new GoodsListDto_App(goods));
		}
		j.setData(dtoList);
		return j;
	}
	
	/**
	 * 附近特卖
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getArountSpecialSell")
	public Json getArountSpecialSell(Json j){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//上面的banner,StoreList
		List<StoreListDto_App> storeDtoList = new ArrayList<StoreListDto_App>();
		PageHelper.startPage(1, 4);
		List<Store> storeList = storeService.getAll();
		for (Store store : storeList) {
			storeDtoList.add(new StoreListDto_App(store));
		}
		resultMap.put("bannerList", storeDtoList);

		
		//品牌特卖
		List<GoodsListDto_App> brandSaleList = new ArrayList<GoodsListDto_App>();
		List<Goods> goodsList1 = goodsService.selectGoodsByCondition(new Goods(), 1, 3);
		for (Goods goods : goodsList1) {
			brandSaleList.add(new GoodsListDto_App(goods));
		}
		resultMap.put("brandSaleList", brandSaleList);
		
		
		//精品集锦
		List<GoodsListDto_App> highQualityGoodsList = new ArrayList<GoodsListDto_App>();
		List<Goods> goodsList2 = goodsService.selectGoodsByCondition(new Goods(), 2, 3);
		for (Goods goods : goodsList2) {
			highQualityGoodsList.add(new GoodsListDto_App(goods));
		}
		resultMap.put("highQualityGoodsList", highQualityGoodsList);
		
		j.setData(resultMap);
		return j;
	}
	
	/**
	 * 新店首发
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getNewStoreList")
	public Json getNewStoreList(Json j,@RequestParam(defaultValue="1") int page){
		PageHelper.startPage(page, 10);
		List<Store> stores = indexService.getNewStoreList();
		List<StoreListDto_App> dtoList = new ArrayList<StoreListDto_App>();
		for (Store store : stores) {
			dtoList.add(new StoreListDto_App(store));
		}
		j.setData(dtoList);
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
		List<StoreListDto_App> dtoList = new ArrayList<StoreListDto_App>();
		for (Store store : stores) {
			dtoList.add(new StoreListDto_App(store));
		}
		j.setData(dtoList);
		return j;
	}
	
	
	/**
	 * 发现列表
	 */
	@ResponseBody
	@RequestMapping(value = "/findAllList")
	public Json findAllList(Json j,@RequestParam(defaultValue="1") int page) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		
		Page<?> startPage = PageHelper.startPage(page,10);
		List<IndexFind> list = indexService.findAll();
		if (!list.isEmpty()) {
			for (IndexFind indexFind : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", indexFind.getId());
				map.put("name", indexFind.getName());
				map.put("picture", indexFind.getPicture());
				map.put("keyword", indexFind.getKeyword());
				mapList.add(map);
			}
		}
		map1.put("list", mapList);
		map1.put("totalNum", startPage.getTotal());
		map1.put("totalPages", startPage.getPages());
		j.setData(map1);
		return j;
	}
	
	/**
	 * 发现详情
	 */
	@ResponseBody
	@RequestMapping(value="/findDetails")
	public Json findDetails(Json j, @RequestParam int id) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		IndexFind indexF = indexService.selectFindByFindId(id);
		List<IndexFind> indexFindList = new ArrayList<IndexFind>();
		indexFindList.add(indexF);
		indexFindList.addAll(indexService.selectListByParentId(id));
		for (IndexFind indexf : indexFindList) {
			Map<String, Object> findMap = new HashMap<String, Object>();
			findMap.put("id", indexf.getId());
			findMap.put("name", indexf.getName());
			findMap.put("picture", indexf.getPicture());
			findMap.put("keyword", indexf.getKeyword());
			
			String websiteBannerStr = indexf.getWebsiteBanner();
			if (StringUtils.isNotBlank(websiteBannerStr)) {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject jObj = JSONObject.parseObject(websiteBannerStr);
				map.put("type", jObj.get("type"));
				map.put("url", jObj.get("url"));
				findMap.put("websiteBanner", map);
			}
			
			String str = indexf.getContent();
			if (StringUtils.isNotBlank(str)) {
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				JSONArray jArray = JSONArray.parseArray(str);
				for (int i = 0; i < jArray.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();

					JSONObject jObj = jArray.getJSONObject(i);
					Object content = jObj.get("content");
					Object url = jObj.get("url");
					if (content != null) {
						map.put("content", content);
					}
					if (url != null) {
						map.put("url", url);
					}
					mapList.add(map);
				}
				findMap.put("content", mapList);
			}
			
			resultList.add(findMap);
		}
		
		
		j.setData(resultList);
		return j;
	}
	
}
