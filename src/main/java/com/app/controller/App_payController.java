package com.app.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhtr.service.AlipayService;

@Controller
@RequestMapping("/app_pay")
public class App_payController {
	@Resource
	public AlipayService alipayService;
	
	@RequestMapping(value="/alipayResult")
	public void alipayResult(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		System.out.println("++++++++++++++++++++++++++++++++++进入支付回调+++++++++++++++++++++++++++++++++++++++++++++");
		alipayService.payResult(request, response);
	}
}
