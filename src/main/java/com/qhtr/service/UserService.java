package com.qhtr.service;

import com.qhtr.common.PageBean;
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
	   User login(String phone, String password);
	   
	   /**
	    * 条件查找用户
	    * @param user
	    * @return
	    */
	   public PageBean<User> getUserListByConditions(User user,int pageNum,int rows);
	   /**
	    * 修改用户信息
	    * @param id
	    * @param nickName
	    * @param sex
	    * @param birthday
	    * @return
	    */
	   int updateUser(int id, String nickName, String sex, String birthday,String avatar);
	   
	   int updatePwd(String phone,String pwd);
	   
	   /**
	    * 绑定手机
	    */
	   int addBindPhone(User user);
}
	