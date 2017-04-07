package com.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.utils.SmsUtils;

@Controller
@RequestMapping("/app_sms")
public class App_SmsController {
	/**
	 * 用户注册
	 * @param j
	 * @param phone
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="userRegistSms")
	public Json userRegistSms(Json j,@RequestParam String phone,HttpServletRequest request) throws ParseException{
		System.out.println("++"+phone+"++");
		@SuppressWarnings("unchecked")
		Map<String,String> theCode = (Map<String, String>)request.getSession().getAttribute(Constants.USER_RIGIST_CODE);
		if(theCode != null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Long createTime = df.parse(theCode.get("time")).getTime();
			Long nowTime = df.parse(df.format(new Date())).getTime();
			if(nowTime - createTime < 60*1000){
				j.setCode(0);
				j.setMessage("请1分钟后再试!");
				return j;
			}
		}
		
		String result = SmsUtils.send(phone, request,1);
		if(StringUtils.isBlank(result)){
			j.setCode(0);
			j.setMessage("发送失败!");
		}else if(result.equals("success")){
			j.setMessage("发送成功!");
		}
		return j;
	}
	
	/**
	 * 修改密码短信
	 * @param j
	 * @param phone
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="changePwd")
	public Json changePwd(Json j,@RequestParam String phone,HttpServletRequest request) throws ParseException{
		System.out.println("++"+phone+"++");
		@SuppressWarnings("unchecked")
		Map<String,String> theCode = (Map<String, String>)request.getSession().getAttribute(Constants.USER_RIGIST_CODE);
		if(theCode != null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Long createTime = df.parse(theCode.get("time")).getTime();
			Long nowTime = df.parse(df.format(new Date())).getTime();
			if(nowTime - createTime < 60*1000){
				j.setCode(0);
				j.setMessage("请1分钟后再试!");
				return j;
			}
		}
		
		String result = SmsUtils.send(phone, request,2);
		if(StringUtils.isBlank(result)){
			j.setCode(0);
			j.setMessage("发送失败!");
		}else if(result.equals("success")){
			j.setMessage("发送成功!");
		}
		return j;
	}
	
	/**
	 * 绑定手机号 短信
	 * @param j
	 * @param phone
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="bindPhone")
	public Json bindPhone(Json j,@RequestParam String phone,HttpServletRequest request) throws ParseException{
		System.out.println("++"+phone+"++");
		@SuppressWarnings("unchecked")
		Map<String,String> theCode = (Map<String, String>)request.getSession().getAttribute(Constants.BIND_PHONE_CODE);
		if(theCode != null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Long createTime = df.parse(theCode.get("time")).getTime();
			Long nowTime = df.parse(df.format(new Date())).getTime();
			if(nowTime - createTime < 60*1000){
				j.setCode(0);
				j.setMessage("请1分钟后再试!");
				return j;
			}
		}
		
		String result = SmsUtils.send(phone, request,3);
		if(StringUtils.isBlank(result)){
			j.setCode(0);
			j.setMessage("发送失败!");
		}else if(result.equals("success")){
			j.setMessage("发送成功!");
		}
		return j;
	}
	
	/**
	 * 解除绑定手机号 短信
	 * @param j
	 * @param phone
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="unBindPhone")
	public Json unBindPhone(Json j,@RequestParam String phone,HttpServletRequest request) throws ParseException{
		System.out.println("++"+phone+"++");
		@SuppressWarnings("unchecked")
		Map<String,String> theCode = (Map<String, String>)request.getSession().getAttribute(Constants.UN_BIND_PHONE_CODE);
		if(theCode != null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Long createTime = df.parse(theCode.get("time")).getTime();
			Long nowTime = df.parse(df.format(new Date())).getTime();
			if(nowTime - createTime < 60*1000){
				j.setCode(0);
				j.setMessage("请1分钟后再试!");
				return j;
			}
		}
		
		String result = SmsUtils.send(phone, request,4);
		if(StringUtils.isBlank(result)){
			j.setCode(0);
			j.setMessage("发送失败!");
		}else if(result.equals("success")){
			j.setMessage("发送成功!");
		}
		return j;
	}
}
