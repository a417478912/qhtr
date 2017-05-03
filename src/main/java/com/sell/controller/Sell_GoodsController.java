package com.sell.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;
import com.qhtr.model.Picture;
import com.qhtr.model.Sku;
import com.qhtr.service.GoodsService;
import com.qhtr.service.PictureService;
import com.qhtr.service.SkuService;
import com.sell.dto.GoodsListDto_Sell;
import com.sell.param.GoodsParam;

@Controller
@RequestMapping("/sell_goods")
public class Sell_GoodsController {
	@Resource
	public GoodsService goodsService;
	@Resource
	public SkuService skuService;
	@Resource
	public PictureService pictureService;
	/**
	 * 添加商品
	 * @param j
	 * @param goodsParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addGoods")
	public Json addGoods(Json j,GoodsParam goods) {
		int result = goodsService.add(goods);
		if (result == -1) {
			j.setCode(1);
			j.setMessage("参数不足!");
		} else if(result == 0){
			j.setCode(0);
			j.setMessage("商品添加失败!");
			return j;
		} else {
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("goodsId", result);
			j.setData(map);
			j.setMessage("商品添加成功!");
		}
		return j;
	}
	
	/**
	 * 删除商品
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteGoods")
	public Json deleteGoods(Json j, @RequestParam int id,@RequestParam int status) {
		int result = goodsService.delete(id,status);
		if (result == 1) {
			j.setMessage("商品状态修改成功!");
		} else {
			j.setCode(0);
			j.setMessage("商品状态修改失败!");
			return j;
		}
		return j;
	}
	
	/**
	 * 修改商品
	 * @param j
	 * @param goodsParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateGoods")
	public Json updateGoods(Json j, GoodsParam goodsParam) {
		int result = goodsService.update(goodsParam);
		if (result == -1) {
			
		} else if(result == 0){
			j.setCode(0);
			j.setMessage("商品修改失败!");
			return j;
		}else{
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("goodsId", result);
			j.setData(map);
			j.setMessage("商品修改成功!");
		}
		return j;
	}
	
	
	/**
	 * 商品列表
	 * @param j
	 * @param storeId
	 * @param type  1正常商品 2已下架
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList")
	public Json getList(Json j,@RequestParam int storeId,@RequestParam(defaultValue="1") int status){
		List<GoodsListDto_Sell> dtoList = new ArrayList<GoodsListDto_Sell>();
		List<Goods> goodsList = goodsService.selectListByStoreAndType(storeId,status); 
		for (Goods goods : goodsList) {
			dtoList.add(new GoodsListDto_Sell(goods));
		}
		j.setData(dtoList);
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
	
	/**
	 * 置顶/取消置顶
	 */
	@ResponseBody
	@RequestMapping(value="/toTop")
	public Json toTop(Json j,@RequestParam int goodsId){
		int result = goodsService.toTop(goodsId);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
	
	/**
	 * 删除sku
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSku")
	public Json deleteSku(Json j,@RequestParam int skuId){
		int result = skuService.delete(skuId);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
}