package com.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.GoodsThumbsUp;
import com.qhtr.service.GoodsThumbsUpService;

/**
 * @author Harry
 * @Description 点赞相关操作
 * @date  2017年6月27日
 */
@Controller
@RequestMapping(value="/app_goodsThumbsUp")
public class App_GoodsThumbsUpController {

	@Resource
	private GoodsThumbsUpService gtus;
	/**
	 * 给商品点赞
	 * @param j
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/goodsThumbsUp")
	public Json goodsThumbsUp(Json j,@RequestParam int userId,@RequestParam int goodsId){
		
		int result = gtus.goodsThumbsUp(userId,goodsId);
		if (result == 0) {
			
			j.setCode(0);
			j.setMessage("点赞失败 !");
		}else{
			
			j.setMessage("点赞成功 !");
		}
		return j;
	}
	
	/**
	 * 查询是否已赞
	 * @param j
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isThumbsUp")
	public Json isThumbsUp(Json j,@RequestParam int userId ,@RequestParam int goodsId){
		
		int result = gtus.isThumbsUp(userId,goodsId);
		if (result == 0) {
			
			j.setData(1);
			j.setMessage("已点赞 !");
		}else{
			
			j.setData(0);
			j.setMessage("未点赞 !");
		}
		return j;
	}
	
	/**
	 * 取消赞
	 * @param j
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cancelThumbsUp")
	public Json cancelThumbsUp(Json j,@RequestParam int userId,@RequestParam int goodsId){
		
		int result = gtus.delThumbsUp(userId,goodsId);
		if (result == 1) {
			
			j.setMessage("取消成功 !");
		}else{
			
			j.setCode(0);
			j.setMessage("取消失败 !");
		}
		return j;
	}
	
	/**
	 * 查询赞的数量
	 * @param j
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/countThumbsUp")
	public Json countThumbsUp(Json j,@RequestParam int goodsId){
		
		int count = gtus.countThumbsUp(goodsId);
		j.setData(count);
		return j;
	}
	
}
