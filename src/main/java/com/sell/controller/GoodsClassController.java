package com.sell.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.service.GoodsClassService;
import com.sell.dto.GoodsClassesDto;

@Controller
@RequestMapping("/sell_goodsclass")
public class GoodsClassController {
	@Resource
	public GoodsClassService goodsClassService;

	/**
	 * 添加分类
	 * @param j
	 * @param name
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Json add(Json j,@RequestParam String name,@RequestParam int storeId){
		int result = goodsClassService.add(name,storeId);
		if(result == 1){
			j.setMessage("添加成功!");
		}else{
			j.setCode(0);
			j.setMessage("添加失败!");
		}
		return j;
	}

	/**
	 * 删除分类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Json delete(Json j,@RequestParam int id){
		int result = goodsClassService.delete(id);
		if(result == 1){
			j.setMessage("删除成功!");
		}else{
			j.setCode(0);
			j.setMessage("删除失败!");
		}
		return j;
	}
	
	/**
	 * 编辑分类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Json update(Json j,String name,@RequestParam int id,Integer sort){
		int result = goodsClassService.update(id,name,sort);
		if(result == 1){
			j.setMessage("编辑成功!");
		}else{
			j.setCode(0);
			j.setMessage("编辑失败!");
		}
		return j;
	}
	
	/**
	 * 查询分类列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList")
	public Json getList(Json j,@RequestParam int storeId){
		List<GoodsClassesDto> list= goodsClassService.selectListByStoreId(storeId);
		j.setData(list);
		return j;
	}
}
