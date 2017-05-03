package com.sell.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhtr.utils.CookieUtils;

@Controller
@RequestMapping("/sell_index")
public class Sell_IndexController{
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>(); 
		Cookie sellerPhone = CookieUtils.getCookieByName(request,"sellerPhone");
		if(sellerPhone != null){
			map.put("login", sellerPhone.getValue());
		}
		Cookie storeId = CookieUtils.getCookieByName(request,"storeId");
		if(storeId != null){
			map.put("store", storeId.getValue());
		}
		request.setAttribute("user", map);
		return "seller/index";
	}
}
