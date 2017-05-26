package com.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.service.FeedBackService;

@Controller
@RequestMapping("/app_feedback")
public class App_FeedBackController {
	@Resource
	public FeedBackService feedBackService;
	
	@ResponseBody
	@RequestMapping(value="/insert")
	public Json insert(Json j,@RequestParam int userId,@RequestParam String content){
		int result = feedBackService.insert(userId, content);
		if(result == 1){
			j.setMessage("留言成功!");
		}else{
			j.setCode(0);
			j.setMessage("留言失败!");
		}
		return j;
	}
}
