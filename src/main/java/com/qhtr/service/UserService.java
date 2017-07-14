package com.qhtr.service;

import java.io.IOException;
import java.util.List;

import com.qhtr.common.PageBean;
import com.qhtr.model.User;

public interface UserService {
	
	   /**
	    * 添加用户//用户注册
	    * @param user
	    */
	   int addUser(String phone,String password);
	   int addUser(User user);
	   
	   /**
	    * 根据用户id获取用户
	    * @param userId
	    * @return
	    */
	   User getUserById(int userId);
	   /**
	    * 条件查找用户
	    */
	    List<User> getUsersByConditions(User user);
	   /**
	    * 用户登录验证
	    * @param phone
	    * @param password
	    * @return
	    */
	   User login(String phone, String password);
	   
	   /**
	    * 条件查找用户（管理后台查询）
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
	 * @throws IOException 
	    */
	   int updateUser(int id, String nickName, String sex, String birthday,String avatar) throws IOException;
	   
	   /**
	    * 条件查找
	    * @param user
	    * @return
	    */
	   List<User> selectByConditions(User user);
	   
	   int updatePwd(String phone,String pwd);
	   
	   /**
	    * 绑定手机
	    */
	   int addBindPhone(int id,String phone);
	   
	 
	    
	    int updateUser(User user);
	    int updateUserByConditions(User user);
	    /**
	     * 三方登陆增加账号
	     * @param user
	     * @param type
	     * @return
	     */
	    
	    /**
	     * 根据userId查询用户
	     * @param userId
	     * @return
	     */
		User selectByPrimaryKey(Integer userId);
}
	