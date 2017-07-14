package com.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.service.StoreThumbsUpService;

/**
 * 店铺点赞
 * @author Harry
 * @Description TODO
 * @date  2017年6月28日
 */
@Controller
@RequestMapping(value="/app_storeThumbsUp")
public class App_StoreThumbsUpController {

	@Resource
	private StoreThumbsUpService stus;
	
	/**
	 * 查询店铺是否已点赞
	 * @param j
	 * @param storeId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isThumbsUp")
	public Json isThumbsUp(Json j,@RequestParam int storeId,@RequestParam int userId){
		
		int result = stus.isThumbsUp(storeId,userId);
		if (result == 1) {
			
			j.setData(1);
			j.setMessage("已赞 !");
			
			
		}else{
			
			j.setData(0);
			j.setMessage("未赞 !");
		}
		return j;
	}
	
	/**
	 * 点赞
	 * @param j
	 * @param storeId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/thumbsUp")
	public Json thumbsUp(Json j,@RequestParam int storeId,@RequestParam int userId){
		
		int result = stus.storeThumbsUp(userId,storeId);
		if (result == 0) {
			
			j.setCode(0);
			j.setMessage("点赞失败 !");
		}else{
			
			j.setMessage("点赞成功 !");
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
	public Json cancelThumbsUp(Json j,@RequestParam int userId,@RequestParam int storeId){
		
		int result = stus.delThumbsUp(userId,storeId);
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
	public Json countThumbsUp(Json j,@RequestParam int storeId){
		
		int count = stus.countThumbsUp(storeId);
		j.setData(count);
		return j;
	}
}
