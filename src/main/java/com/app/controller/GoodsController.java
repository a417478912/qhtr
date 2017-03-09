package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;
import com.qhtr.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 通过卖家id获取商品信息
	 * @param j
	 * @param sellerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsBySellerId")
	public Json selectGoodsBySellerId(Json j,@RequestParam int sellerId){
		List<Goods> goods = goodsService.selectGoodsBySellerId(sellerId);
		if(goods != null){
			j.setData(goods);
		}else{
			j.setSuccess(false);
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
			j.setSuccess(false);
		}
		return j;
	}
}
