package com.qhtr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qhtr.common.PageBean;
import com.qhtr.model.User;
import com.qhtr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/userList")
	public String userList(HttpServletRequest request,@RequestParam(defaultValue="1") int pageNum,@RequestParam(defaultValue="20") int numPerPage,String name,String phone){
		User user = new User();
		if(StringUtils.isNotBlank(name)){
			user.setName(name);
		}
		if(StringUtils.isNotBlank(phone)){
			user.setPhone(phone);
		}
		PageBean<User> userList = userService.getUserListByConditions(user,pageNum,numPerPage);
		request.setAttribute("data", userList);
		request.setAttribute("user", user);
		return "user/userList";
	}
	
}
