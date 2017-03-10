package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.UserMapper;
import com.qhtr.model.User;
import com.qhtr.service.UserService;
import com.qhtr.utils.MD5Utils;
/**
 * 
 * @author wjx
 *
 * @date  2017年3月7日
 */
@Service
public class UserServiceImpl implements UserService{
	@Resource
    private UserMapper userMapper;//注入dao
	
	@Override
	public int addUser(String phone,String password) {
		if(verifyPhone(phone) == 2){
			return 2;
		}
		
		User user = new User();
		user.setPhone(phone);
		user.setPassword(MD5Utils.getString(password));
		user.setCreateTime(new Date());
		return userMapper.insert(user);
	}

	@Override
	public User getUserById(int userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
	
	/**
	 * 验证手机号是否注册
	 * @param phone
	 * @return 2 ：已注册   1：未注册
	 */
	public int verifyPhone(String phone){
		User verifyUser = new User();
		verifyUser.setPhone(phone);
		List<User> users = userMapper.selectByConditions(verifyUser);
		if(!users.isEmpty()){
			return 2;
		}else{
			return 1;
		}
	}

	@Override
	public int login(String phone, String password) {
		User user = new User();
		user.setPhone(phone);
		user.setPassword(MD5Utils.getString(password));
		List<User> users = userMapper.selectByConditions(user);
		if(users.isEmpty()){
			return 0;
		}else{
			return 1;
		}
	}
}
	