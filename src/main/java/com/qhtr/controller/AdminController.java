package com.qhtr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qhtr.common.Constants;
import com.qhtr.model.Admin;
import com.qhtr.service.AdminService;
import com.qhtr.utils.MD5Utils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminService;
	
	@RequestMapping(value="/loginUI")
	public String loginUI(){
		return "admin/login";
	}
	
	@RequestMapping(value="/login")
	public String login(@RequestParam String name,@RequestParam String password,HttpServletRequest request){
		Admin admin = adminService.getValidation(name,password);
		if(admin == null){
			return "admin/login";
		}else{
			request.getSession().setAttribute(Constants.ADMIN_KEY, admin);
			return "redirect:indexUI.do";
		}
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	@RequestMapping(value="/logOut")
	public String logOut(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.ADMIN_KEY);
		return "admin/login";
	}
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/indexUI")
	public String indexUI(){
		return "index";
	}
	
	@RequestMapping(value="/index1")
	public String index1(){
		return "index1";
	}
	@RequestMapping(value="/index2")
	public String index2(){
		return "index2";
	}
}
