package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.AdminMapper;
import com.qhtr.model.Admin;
import com.qhtr.service.AdminService;
import com.qhtr.utils.MD5Utils;

@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminMapper adminMapper;
	@Override
	public Admin getValidation(String name, String password) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(MD5Utils.getString(password));
		List<Admin> admins = adminMapper.selectByConditions(admin);
		if(admins.isEmpty()){
			return null;
		}else{
			return admins.get(0);
		}
	}

}
