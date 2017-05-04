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
import com.app.dto.IndexDto;
import com.app.dto.StoreListDto_App;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qhtr.common.Json;
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
	 * 附近好货
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsArount")
	public Json getGoodsArount(Json j){
		j.setData(goodsService.selectGoodsByCondition1(1, 4));
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
		List<IndexFind> indexFindList = indexService.selectListByParentId(id);
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
