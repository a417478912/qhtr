package com.qhtr.service;

import com.qhtr.model.User;

public interface UserService {
	
	   /**
	    * 添加用户
	    * @param user
	    */
	   int addUser(User user) throws Exception;
	   
	   /**
	    * 根据用户id获取用户
	    * @param userId
	    * @return
	    */
	   User getUserById(int userId);
}
	