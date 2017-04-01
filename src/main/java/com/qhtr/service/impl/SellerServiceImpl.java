package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.SellerMapper;
import com.qhtr.model.Seller;
import com.qhtr.model.User;
import com.qhtr.service.SellerService;
import com.qhtr.utils.MD5Utils;

@Service
public class SellerServiceImpl implements SellerService {
	@Resource
	private SellerMapper sellerMapper;

	@Override
	public Seller getSellerById(int id) {
		return sellerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addRegister(String phone, String password) {
		if(verifyPhone(phone) == 2){
			return 2;
		}
		
		Seller seller = new Seller();
		seller.setPhone(phone);
		seller.setPassword(MD5Utils.getString(password));
		seller.setCreateTime(new Date());
		return sellerMapper.insert(seller);
	}
	
	/**
	 * 验证手机号是否注册
	 * @param phone
	 * @return 2 ：已注册   1：未注册
	 */
	public int verifyPhone(String phone){
		Seller seller = new Seller();
		seller.setPhone(phone);
		List<Seller> sellers = sellerMapper.selectByConditions(seller);
		if(!sellers.isEmpty()){
			return 2;
		}else{
			return 1;
		}
	}

}
