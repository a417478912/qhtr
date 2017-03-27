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
import com.qhtr.service.GoodsService;

@Controller
@RequestMapping("/app_goods")
public class App_GoodsController {
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 通过卖家id获取商品信息
	 * @param j
	 * @param sellerId
	 * @param type 类型: 0.全部  1. 新品上架  2. 特卖    3 店长推荐
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsBySellerId")
	public Json selectGoodsBySellerId(Json j,@RequestParam int sellerId,Integer type){
		if(type == null) type =0;
		List<Goods> goods = goodsService.selectGoodsBySellerId(sellerId,type);
		if(goods != null){
			j.setData(goods);
		}else{
			j.setCode(0);
		}
		return j;
	}
	
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
}
