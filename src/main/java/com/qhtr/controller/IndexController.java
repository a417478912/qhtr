package com.qhtr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhtr.common.Json;
import com.qhtr.model.IndexFind;
import com.qhtr.service.IndexFindService;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Resource
	public IndexFindService indexFindService;
	
	
	public String findList(Json j,HttpServletRequest request){
		List<IndexFind> list = indexFindService.findAll();
		request.setAttribute("list", list);
		return "/index/find";
	}
	
	@RequestMapping(value="/addFind")
	public Json addFind(Json j){
		return j;
	}
}
