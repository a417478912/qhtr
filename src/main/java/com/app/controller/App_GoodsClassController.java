package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.GoodsListDto_App;
import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.service.GoodsClassService;
import com.sell.dto.GoodsClassesDto;
/**
 * @author wjx
 * 处理商品分类的 Controller
 * @date  2017年6月2日
 */
@Controller
@RequestMapping("/app_goodsClass")
public class App_GoodsClassController {
	@Resource
	public GoodsClassService goodsClassService;
	
	/**
	 * 获取商品分类
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoosClassList")
	public Json getGoosClassList(Json j,@RequestParam int storeId ){
		List<GoodsClassesDto> list = goodsClassService.selectListByStoreId(storeId);
		j.setData(list);
		return j;
	}
	
	/**
	 * 获取分类中的商品
	 * @param j
	 * @param classId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsByClass")
	public Json getGoodsByClass(Json j,@RequestParam int classId){
		List<GoodsListDto_App> dtoList = new ArrayList<GoodsListDto_App>();
		List<Goods> list = goodsClassService.getGoodsByClass_App(classId);
		for (Goods goods : list) {
			dtoList.add(new GoodsListDto_App(goods));
		}
		j.setData(dtoList);
		return j;
	}
	
}
