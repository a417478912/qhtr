package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.UserMapper;
import com.qhtr.model.User;
import com.qhtr.service.UserService;
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
	public int addUser(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User getUserById(int userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
}
	