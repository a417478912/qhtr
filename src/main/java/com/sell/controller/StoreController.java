package com.sell.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;

@Controller
@RequestMapping("/sell_stroe")
public class StoreController {
	
	@ResponseBody
	@RequestMapping(value="/get")
	public Json get(Json j){
		System.out.println("Xxx");
		return j;
	}
}
