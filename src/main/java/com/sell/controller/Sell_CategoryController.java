package com.sell.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Category;
import com.qhtr.service.CategoryService;

@Controller
@RequestMapping("/sell_category")
public class Sell_CategoryController {
	@Resource
	public CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping(value="/getAll")
	public Json getAll(Json j){
		List<Category> cats = categoryService.selectAll();
		if(cats.isEmpty()){
			j.setCode(0);
			j.setMessage("查询失败!");
		}else{
			j.setData(cats);
		}
		return j;
	}
}
