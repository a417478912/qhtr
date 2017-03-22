package com.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.model.User;
import com.qhtr.service.UserService;
import com.qhtr.utils.FileUploadUtils;
import com.sun.tools.internal.xjc.model.SymbolSpace;

@Controller
@RequestMapping("/app_user")
public class App_UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 用户注册
	 * @param j
	 * @param phone
	 * @param password
	 * @param code
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/register")
	public Json register(Json j,@RequestParam String phone,@RequestParam String password,@RequestParam String phone_code,HttpServletRequest request) throws ParseException{
		@SuppressWarnings("unchecked")
		Map<String,String> theCode = (Map<String, String>)request.getSession().getAttribute(Constants.USER_RIGIST_CODE);
		if(theCode == null){
			j.setSuccess(false);
			j.setMessage("没有发送验证码!");
			return j;
		}else{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Long createTime = df.parse(theCode.get("time")).getTime();
			Long nowTime = df.parse(df.format(new Date())).getTime();
			if(nowTime - createTime > 5*60*1000){
				request.getSession().removeAttribute(Constants.USER_RIGIST_CODE);
				j.setSuccess(false);
				j.setMessage("验证码超时!");
				return j;
			}
		}
		String code = (String) theCode.get("code");
		if (code != null && code.equals(phone_code)) {
			int result = userService.addUser(phone, password);
			if(result == 2){
				j.setSuccess(false);
				j.setMessage("手机号已被注册!");
			}else if (result == 1) {
				j.setMessage("注册成功!");
			} else {
				j.setSuccess(false);
				j.setMessage("注册失败!");
			}
		}else{
			j.setSuccess(false);
			j.setMessage("验证码输入错误!");
		}
		return j;
	}
	
	/**
	 * 更新用户信息
	 * @param j
	 * @param id
	 * @param nickName
	 * @param sex
	 * @param birthday
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public Json updateUser(Json j,@RequestParam int id,String nickName,String sex,String birthday){
		int result = userService.updateUser(id,nickName,sex,birthday,"");
		if(result == 1){
			j.setMessage("更新成功!");
		}else{
			j.setSuccess(false);
			j.setMessage("更新失败!");
		}
		return j;
	}
	
	/**
	 * 用户登录
	 * @param j
	 * @param phone
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public Json login(Json j,@RequestParam String phone,@RequestParam String password,HttpServletRequest request){
		User user = userService.login(phone,password);
		if(user != null){
			request.getSession().setAttribute(Constants.USER_KEY, user);
			j.setData(user.getId());
			j.setMessage("登录成功");
		}else{
			j.setSuccess(false);
			j.setMessage("登录失败");
		}
		return j;
	}
	
	/**
	 * 用户头像上传
	 * @param j
	 * @param id
	 * @param avatar
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/updateAvatar",method=RequestMethod.POST)
	public Json updateAvatar(Json j,@RequestParam int id,@RequestParam MultipartFile avatar) throws Exception{
		String path = FileUploadUtils.uploadFile(avatar);
		int result = userService.updateUser(id, "", "", "",path);
		if(result == 1){
			j.setMessage("更新成功!");
		}else{
			j.setSuccess(false);
			j.setMessage("更新失败!");
		}
		return j;
	}
	
	/**
	 * 查询用户信息
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getUserInfo")
	public Json getUserInfo(Json j,@RequestParam int id){
		User user = userService.getUserById(id);
		j.setData(user);
		return j;
	}
}
