package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.BuyCartDto;
import com.qhtr.common.Json;
import com.qhtr.model.BuyCart;
import com.qhtr.service.BuyCartService;

@Controller
@RequestMapping("/app_buycart")
public class App_BuyCartController {
	@Resource
	public BuyCartService buyCartService;
	
	/**
	 * 通过用户id查询购物车
	 * @param j
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectCartsByUserId")
	public Json selectCartsByUserId(Json j,@RequestParam int userId){
		List<BuyCartDto> carts = buyCartService.selectCartsByUserId(userId);
		if(carts.isEmpty()){
			j.setSuccess(false);
		}else{
			j.setData(carts);
		}
		return j;
	}
	
	/**
	 * 添加购物车
	 * @param j
	 * @param cart
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addBuyCart",method=RequestMethod.POST)
	public Json addBuyCart(Json j,BuyCart cart){
		int result = buyCartService.addById(cart);
		if(result == 0){
			j.setSuccess(false);
			j.setMessage("添加购物车失败!");
		}else{
			j.setMessage("添加购物车成功!");
		}
		return j;
	}
	
	/**
	 * 删除购物车商品
	 * @param j
	 * @param cartId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBuyCart",method=RequestMethod.POST)
	public Json deleteBuyCart(Json j,@RequestParam int cartId){
		int result = buyCartService.deleteById(cartId);
		if(result == 0){
			j.setSuccess(false);
			j.setMessage("删除失败!");
		}else{
			j.setMessage("删除成功!");
		}
		return j;
	}
	
	/**
	 * 清空/批量删除购物车商品
	 * @param j
	 * @param cartId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBatchBuyCart",method=RequestMethod.POST)
	public Json deleteBatchBuyCart(Json j,@RequestParam int[] ids){
		int result = buyCartService.deleteByIds(ids);
		if(result == 0){
			j.setSuccess(false);
			j.setMessage("删除失败!");
		}else{
			j.setMessage("删除成功!");
		}
		return j;
	}
	
	/**
	 * 修改购物车数据
	 * @param j
	 * @param cart
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBuyCart",method=RequestMethod.POST)
	public Json updateBuyCart(Json j,BuyCart cart){
		int result = buyCartService.updateById(cart);
		if(result == 0){
			j.setSuccess(false);
			j.setMessage("修改失败!");
		}else{
			j.setMessage("修改成功!");
		}
		return j;
	}
	
	/**
	 * 批量修改购物车数据
	 * @param j
	 * @param cart
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBatchBuyCart",method=RequestMethod.POST)
	public Json updateBatchBuyCart(Json j,@RequestBody List<BuyCart> cart){
		int result = buyCartService.updateBatch(cart);
		if(result == 0){
			j.setSuccess(false);
			j.setMessage("修改失败!");
		}else{
			j.setMessage("修改成功!");
		}
		return j;
	}
}
