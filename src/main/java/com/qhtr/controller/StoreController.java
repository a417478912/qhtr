package com.qhtr.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qhtr.service.UserService;

@Controller
@RequestMapping("/user")
public class StoreController {
	@Resource
	private UserService userService;
	
	
}
