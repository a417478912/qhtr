package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.service.UserService;

@Controller
@RequestMapping("/app_user")
public class App_UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 用户注册
	 * @param j
	 * @param phone
	 * @param password
	 * @param code
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Json register(Json j,@RequestParam String phone,@RequestParam String password,@RequestParam String phone_code,HttpServletRequest request){
		String theCode = (String)request.getSession().getAttribute("user_register_code");
		theCode = "1234";
		phone_code = "1234";
		if (theCode != null && theCode.equals(phone_code)) {
			int result = userService.addUser(phone, password);
			if(result == 2){
				j.setSuccess(false);
				j.setMessage("手机号已被注册!");
			}else if (result == 1) {
				j.setMessage("注册成功!");
			} else {
				j.setSuccess(false);
				j.setMessage("注册失败!");
			}
		}else{
			j.setSuccess(false);
			j.setMessage("验证码输入错误!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/login")
	public Json login(Json j,@RequestParam String phone,@RequestParam String password){
		int result = userService.login(phone,password);
		if(result == 1){
			j.setMessage("登录成功");
		}else{
			j.setSuccess(false);
			j.setMessage("登录失败");
		}
		return j;
	}
}
