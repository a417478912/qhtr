package com.qhtr.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.qhtr.common.PageBean;
import com.qhtr.dao.UserMapper;
import com.qhtr.model.User;
import com.qhtr.service.UserService;
import com.qhtr.utils.FileUploadUtils;
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
		user.setStatus(1);
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
	public User login(String phone, String password) {
		User user = new User();
		user.setPhone(phone);
		user.setPassword(MD5Utils.getString(password));
		List<User> users = userMapper.selectByConditions(user);
		if(users.isEmpty()){
			return null;
		}else{
			return users.get(0);
		}
	}

	@Override
	public PageBean<User> getUserListByConditions(User user,int pageNum,int rows) {
		PageHelper.startPage(pageNum,rows);
		List<User> userList = userMapper.selectByConditions(user);
		return new PageBean<User>(userList);
	}

	@Override
	public int updateUser(int id, String nickName, String sex, String birthday,String avatar) throws IOException {
		String path= "";
		if(avatar != null){
			path = FileUploadUtils.saveFromBase64String(avatar,"userAvatar");
		}else{
			path = "";
		}
		User user = new User();
		user.setId(id);
		if(StringUtils.isNotBlank(nickName)){
			user.setNickName(nickName);
		}
		if(StringUtils.isNotBlank(sex)){
			user.setSex(sex);
		}
		if(StringUtils.isNotBlank(birthday)){
			user.setBirthday(birthday);;
		}
		if(StringUtils.isNotBlank(path)){
			User oldUser = userMapper.selectByPrimaryKey(id);
			if(!FileUploadUtils.deleteFile(oldUser.getAvatar())){
				return 0;
			};
			user.setAvatar(path);
		}
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updatePwd(String phone, String pwd) {
		User userTem = new User();
		userTem.setPhone(phone);
		List<User> userList = userMapper.selectByConditions(userTem);
		if(userList.isEmpty()){
			return 0;
		}else{
			User user = userList.get(0);
			user.setPassword(MD5Utils.getString(pwd));
			return userMapper.updateByPrimaryKey(user);
		}
	}

	@Override
	public int addBindPhone(User user) {
		User userTem = new User();
		userTem.setPhone(user.getPhone());
		List<User> list = userMapper.selectByConditions(userTem);
		if(!list.isEmpty()){
			user.setId(list.get(0).getId());
			return userMapper.updateByPrimaryKeySelective(user);
		}else{
			return userMapper.insert(user);
		}
	}
}
	