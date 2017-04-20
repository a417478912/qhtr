package com.sell.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.service.ActivityService;

@Controller
@RequestMapping("/sell_activity")
public class ActivityController {
	@Resource
	public ActivityService activityService;
	
	/**
	 * 通过商铺id 获取营销活动列表
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList")
	public Json getList(Json j,@RequestParam int storeId){
		List<Map<String,String>> list = activityService.selectListByStoreId(storeId);
		j.setData(list);
		return j;
	}
	
	/**
	 * 添加活动商品
	 * @param j
	 * @param goodsIds
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addGoods")
	public Json addGoods(Json j,@RequestParam int[] goodsIds,@RequestParam int storeId,@RequestParam int modelId){
		int result = activityService.addGoods(goodsIds,storeId,modelId);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
	
	/**
	 * 删除活动商品
	 * @param j
	 * @param goodsIds
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteGoods")
	public Json deleteGoods(Json j,@RequestParam int goodsIds,@RequestParam int modelId){
		int result = activityService.deleteGoods(goodsIds,modelId);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
	
	/**
	 * 查询活动商品列表
	 * @param j
	 * @param goodsIds
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsList")
	public Json getGoodsList(Json j,@RequestParam int storeId,@RequestParam int modelId){
		List<Goods> goodsList = activityService.selectByStoreIdAndModelId(storeId, modelId);
		j.setData(goodsList);
		return j;
	}
	
	/**
	 * 查询不在此活动中的商品列表
	 * @param j
	 * @param goodsIds
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsListExcept")
	public Json getGoodsListExcept(Json j,@RequestParam int storeId,@RequestParam int modelId){
		List<Goods> goodsList = activityService.selectByStoreIdAndModelIdExcept(storeId, modelId);
		j.setData(goodsList);
		return j;
	}
}
