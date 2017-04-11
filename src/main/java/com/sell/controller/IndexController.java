package com.sell.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhtr.utils.CookieUtils;

@Controller
@RequestMapping("/sell_index")
public class IndexController{
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request){
		request.setAttribute("user", CookieUtils.getCookieByName(request,"user"));
		return "seller/index";
	}
}
