package com.sell.controller;

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
@RequestMapping("/sell_sms")
public class SmsController {
	/**
	 * type  1.用户注册 2.修改密码 3.绑定手机号
	 * @param j
	 * @param phone
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/send")
	public Json send(Json j,@RequestParam String phone,HttpServletRequest request,@RequestParam int type) throws ParseException{
		Map<String,String> theCode = null;
		if(type == 1){
			theCode = (Map<String, String>)request.getSession().getAttribute(Constants.USER_RIGIST_CODE);
		}else if(type == 2){
			theCode = (Map<String, String>)request.getSession().getAttribute(Constants.USER_RIGIST_CODE);
		}else if(type == 3){
			theCode = (Map<String, String>)request.getSession().getAttribute(Constants.BIND_PHONE_CODE);
		}
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
		
		String result = SmsUtils.send(phone, request,type);
		if(StringUtils.isBlank(result)){
			j.setCode(0);
			j.setMessage("发送失败!");
		}else if(result.equals("success")){
			j.setMessage("发送成功!");
		}
		return j;
	}
}
