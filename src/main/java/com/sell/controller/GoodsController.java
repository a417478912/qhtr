package com.sell.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.GoodsDto;
import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.service.GoodsService;
import com.sell.param.GoodsParam;

@Controller
@RequestMapping("/sell_goods")
public class GoodsController {
	@Resource
	public GoodsService goodsService;
	
	/**
	 * 添加商品
	 * @param j
	 * @param goodsParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addGoods")
	public Json addGoods(Json j, @RequestBody String goods) {
		int result = goodsService.add(goods);
		if (result == 1) {
			j.setMessage("商品添加成功!");
		} else {
			j.setCode(0);
			j.setMessage("商品添加失败!");
			return j;
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
	public Json deleteGoods(Json j, @RequestParam int id) {
		int result = goodsService.delete(id);
		if (result == 1) {
			j.setMessage("商品删除成功!");
		} else {
			j.setCode(0);
			j.setMessage("商品删除失败!");
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
	public Json updateGoods(Json j, String goodsParam) {
		int result = goodsService.update(goodsParam);
		if (result == 1) {
			j.setMessage("商品修改成功!");
		} else {
			j.setCode(0);
			j.setMessage("商品修改失败!");
			return j;
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
	public Json getList(Json j,@RequestParam int storeId,@RequestParam int status){
		List<Goods> goodsList = goodsService.selectListByStoreAndType(storeId,status); 
		j.setData(goodsList);
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
}
