package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.HomePage;
import com.qhtr.service.HomePageService;

/**
 * @author Harry
 * @Description 获取首页轮播的四张图片的 Controller
 * @date  2017年6月5日
 */
@Controller
@RequestMapping("/app_homePage")// /app_homePage/getHomePagePic
public class App_HomePageController {
	
	@Resource
	public HomePageService homePageService;
	
	/**
	 * 获取轮播图
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getHomePagePic",method=RequestMethod.POST)
	public Json getHomePagePic(Json j){
		
		List<HomePage> list = homePageService.getHomePagePic();
		
		if (list != null && list.size() != 0) {
			
			j.setData(list);
			j.setMessage("获取成功!");
			
		}else{
			
			j.setCode(0);
			j.setMessage("获取失败!");
			
		}
		return j;
	}
}
