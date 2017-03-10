package com.qhtr.service;

import com.qhtr.model.User;

public interface UserService {
	
	   /**
	    * 添加用户//用户注册
	    * @param user
	    */
	   int addUser(String phone,String password);
	   
	   /**
	    * 根据用户id获取用户
	    * @param userId
	    * @return
	    */
	   User getUserById(int userId);
	   /**
	    * 用户登录验证
	    * @param phone
	    * @param password
	    * @return
	    */
	   int login(String phone, String password);
}
	