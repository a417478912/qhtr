package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.GoodsDto;
import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.service.ActivityService;
import com.qhtr.service.GoodsService;

@Controller
@RequestMapping("/app_goods")
public class App_GoodsController {
	@Resource
	private GoodsService goodsService;
	@Resource
	private ActivityService activityService;
	
	/**
	 * 通过商品id  获取商品详情
	 * @param j
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsByGoodsId")
	public Json selectGoodsByGoodsId(Json j,@RequestParam int goodsId){
		GoodsDto dto = goodsService.selectGoodsByGoodsId(goodsId);
		if(dto != null){
			j.setData(dto);
		}else{
			j.setCode(0);
		}
		return j;
	}
	
	/**
	 * 查询活动商品  selectByStoreIdAndModelId
	 */
	@ResponseBody
	@RequestMapping(value="/selectByStoreIdAndModelId")
	public Json selectByStoreIdAndModelId(Json j,@RequestParam int storeId,@RequestParam int modelId){
		List<Goods> list = activityService.selectByStoreIdAndModelId(storeId,modelId);
		if(list != null){
			j.setData(list);
		}else{
			j.setCode(0);
		}
		return j;
	}
	
	
	
	/**
	 * 通过商铺id  获取商品列表
	 * @param j
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsBystoreId")
	public Json selectGoodsBystoreId(Json j,@RequestParam int storeId){
		List<Goods> list = goodsService.selectListByStoreAndType(storeId,1);
		if(list != null){
			j.setData(list);
		}else{
			j.setCode(0);
		}
		return j;
	}
}
