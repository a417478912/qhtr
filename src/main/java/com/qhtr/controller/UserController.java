package com.qhtr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.dao.UserMapper;
import com.qhtr.model.User;
import com.qhtr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private UserMapper userMapper;
	@RequestMapping(value="/index")
	public String index(Model model,HttpServletRequest request){
		System.out.println(userMapper.selectByPrimaryKey(1).getAvatar());
		model.addAttribute("content", userMapper.selectByPrimaryKey(1).getAvatar());
		return "index";
	}
	
	@RequestMapping(value="/aa")
	public String aa(HttpServletRequest request){
		String a = request.getParameter("content");
		User user =	userMapper.selectByPrimaryKey(1);
		user.setAvatar(a);
		userMapper.updateByPrimaryKey(user);
		return "redirect:index.do";
	}
}
