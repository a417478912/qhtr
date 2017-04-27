package com.sell.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Store;
import com.qhtr.service.StoreService;
import com.sell.dto.StoreDto_Sell;

@Controller
@RequestMapping("/sell_stroe")
public class StoreController {
	@Resource
	public StoreService storeService;
	
	/**
	 * 卖家注册
	 * 
	 *//*
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
			int result = storeService.addRegister(phone, password);
			if (result == -1) {
				j.setCode(0);
				j.setMessage("手机号已被注册!");
			} else if (result == 0) {
				j.setCode(0);
				j.setMessage("注册失败!");
			} else {
				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("storeId", result);
				j.setData(map);
				j.setMessage("注册成功!");
			}

		}
		return j;
	}*/
	
	/**
	 * 卖家注册
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/registerSeller")
	public Json registerSeller(Json j, @RequestParam String phone, @RequestParam String password,
			@RequestParam String phone_code, HttpServletRequest request) throws ParseException {
			int result = storeService.addRegister(phone, password);
			if (result == -1) {
				j.setCode(0);
				j.setMessage("手机号已被注册!");
			} else if (result == 0) {
				j.setCode(0);
				j.setMessage("注册失败!");
			} else {
				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("storeId", result);
				j.setData(map);
				j.setMessage("注册成功!");
			}
		return j;
	}
	
	/**
	 * 登陆
	 * @param j
	 * @param phone
	 * @param password
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public Json login(Json j,@RequestParam String phone,@RequestParam String password,HttpServletResponse response){
		int result = storeService.login(phone,password,response);
		if(result == 0){
			j.setCode(0);
			j.setMessage("账号或密码错误!");
		}else{
			j.setMessage("登陆成功!");
		}
		return j;
	}
	
	/**
	 * 修改商家信息
	 * @param j
	 * @param store
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateStore")
	public Json updateStore(Json j,Store store){
		if(store.getPhone() != null || store.getPassword() != null){
			j.setCode(0);
			j.setMessage("参数错误");
			return j;
		}
		int result = storeService.updateByConditions(store);
		if(result == 1){
			j.setMessage("修改成功!");
		}else{
			j.setCode(0);
			j.setMessage("修改失败!");
		}
		
		return j;
	}
	
	/**
	 * 修改密码
	 * @param j
	 * @param phone
	 * @param password
	 * @param phone_code
	 * @param request
	 * @return
	 * @throws ParseException
	 *//*
	@ResponseBody
	@RequestMapping(value="/changePassword")
	public Json changePassword(Json j,@RequestParam String phone,@RequestParam String password,@RequestParam String phone_code,HttpServletRequest request) throws ParseException{
		System.out.println("++++" + phone + "++++++++");
		@SuppressWarnings("unchecked")
		Map<String, String> theCode = (Map<String, String>) request.getSession()
				.getAttribute(Constants.USER_CHANGE_PWD_CODE);
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
			int result = storeService.updatePassword(phone, password);
			if (result == 1) {
				j.setMessage("修改成功!");
			} else if (result == 0) {
				j.setCode(0);
				j.setMessage("修改失败!");
			} 
		}
		return j;
		
	}*/
	
	/**
	 * 修改密码
	 * @param j
	 * @param phone
	 * @param password
	 * @param phone_code
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/changePassword")
	public Json changePassword(Json j,@RequestParam String phone,@RequestParam String password,@RequestParam String phone_code,HttpServletRequest request) throws ParseException{
			int result = storeService.updatePassword(phone, password);
			if (result == 1) {
				j.setMessage("修改成功!");
			} else if (result == 0) {
				j.setCode(0);
				j.setMessage("修改失败!");
			} 
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/getStore")
	public Json getStore(Json j,String phone,Integer id){
		if(phone == null && id == null){
			j.setCode(0);
			j.setMessage("参数错误!");
			return j;
		}
		Store storeTem = new Store();
		storeTem.setPhone(phone);
		storeTem.setId(id);
		Store store = storeService.getStoreByIdOrPhone(storeTem);
		if(store!=null){
			j.setData(new StoreDto_Sell(store));
		}else{
			j.setMessage("没有查到信息!");
		}
		return j;
	}
	
	/**
	 * 查询商铺的 购买用户信息
	 */
	@ResponseBody
	@RequestMapping(value="/getUserList")
	public Json getUserList(Json j,@RequestParam int storeId){
		List<Map<String,Object>> list = storeService.getUserListByStoreId(storeId);
		j.setData(list);
		return j;
	}
}
