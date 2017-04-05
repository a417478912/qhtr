package com.sell.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.model.Store;
import com.qhtr.service.SellerService;
import com.qhtr.service.StoreService;

@Controller
@RequestMapping("/sell_stroe")
public class StoreController {
	@Resource
	public SellerService sellService;
	@Resource
	public StoreService storeService;
	
	/**
	 * 卖家注册
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/registerSeller")
	public Json registerSeller(Json j, @RequestParam String phone, @RequestParam String password,
			@RequestParam String phone_code, HttpServletRequest request) throws ParseException {
		System.out.println("++++" + phone + "++++++++");
		@SuppressWarnings("unchecked")
		Map<String, String> theCode = (Map<String, String>) request.getSession()
				.getAttribute(Constants.USER_RIGIST_CODE);
		if (theCode == null) {
			j.setCode(0);
			j.setMessage("没有发送验证码!");
			return j;
		} else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long createTime = df.parse(theCode.get("time")).getTime();
			Long nowTime = df.parse(df.format(new Date())).getTime();
			if (nowTime - createTime > 5 * 60 * 1000) {
				request.getSession().removeAttribute(Constants.USER_RIGIST_CODE);
				j.setCode(0);
				j.setMessage("验证码超时!");
				return j;
			}
		}
		String code = (String) theCode.get("code");
		String thePhone = (String) theCode.get("phone");
		if (code == null || thePhone == null || !code.equals(phone_code) || !thePhone.equals(phone)) {
			j.setCode(0);
			j.setMessage("手机号或者验证码输入错误!");
		} else {
			int result = sellService.addRegister(phone, password);
			if (result == 2) {
				j.setCode(0);
				j.setMessage("手机号已被注册!");
			} else if (result == 1) {
				j.setMessage("注册成功!");
			} else {
				j.setCode(0);
				j.setMessage("注册失败!");
			}

		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/registerStore")
	public Json registerStore(Json j,Store store){
		int result = storeService.insert(store);
		if(result == 1){
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("storeId", store.getId());
			j.setData(map);
			j.setMessage("注册成功!");
		}else{
			j.setCode(0);
			j.setMessage("注册失败!");
		}
		
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateStore")
	public Json updateStore(Json j,Store store){
		int result = storeService.updateByConditions(store);
		if(result == 1){
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("storeId", store.getId());
			j.setData(map);
			j.setMessage("注册成功!");
		}else{
			j.setCode(0);
			j.setMessage("注册失败!");
		}
		
		return j;
	}
}
