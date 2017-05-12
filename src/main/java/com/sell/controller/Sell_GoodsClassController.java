package com.sell.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.GoodsClasses;
import com.qhtr.service.GoodsClassService;
import com.sell.dto.GoodsClassesDto;

@Controller
@RequestMapping("/sell_goodsclass")
public class Sell_GoodsClassController {
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
	
	/**
	 * 通过分类查询商品
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsByClass")
	public Json getGoodsByClass(Json j,@RequestParam int storeId,@RequestParam int classId){
		List<Map<String,Object>> list = goodsClassService.getGoodsByClass(storeId,classId);
		GoodsClasses gc = goodsClassService.getById(classId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("classId", gc.getId());
		map.put("className", gc.getName());
		map.put("goodsList", list);
		j.setData(map);
		return j;
	}
	
	/**
	 * 通过分类查询(不在分类中的)商品
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsByClassExcept")
	public Json getGoodsByClassExcept(Json j,@RequestParam int storeId,@RequestParam int classId){
		List<Map<String,Object>> list = goodsClassService.getGoodsByClassExcept(storeId,classId);
		j.setData(list);
		return j;
	}
	
	/**
	 * 分类编辑商品/增加商品
	 */
	@ResponseBody
	@RequestMapping(value="/addGoodsByClass")
	public Json addGoodsByClass(Json j,@RequestParam int[] goodsIds,@RequestParam int classId){
		int result = goodsClassService.addGoodsByClass(goodsIds,classId);
		if(result == 0){
			j.setCode(0);
			j.setMessage("更新失败!");
		}else{
			j.setMessage("更新成功!");
		}
		return j;
	}
	
	/**
	 * 分类删除商品
	 */
	@ResponseBody
	@RequestMapping(value="/deleteGoodsByClass")
	public Json deleteGoodsByClass(Json j,@RequestParam String goodsIds,@RequestParam int classId){
		int result = goodsClassService.deleteGoodsByClass(goodsIds,classId);
		if(result == 0){
			j.setCode(0);
			j.setMessage("删除失败!");
		}else{
			j.setMessage("删除成功!");
		}
		return j;
	}
}
