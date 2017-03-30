package com.app.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.model.User;
import com.qhtr.service.SystemLogService;
import com.qhtr.service.UserService;

@Controller
@RequestMapping("/app_user")
public class App_UserController {
	@Resource
	public SystemLogService systemLogService;

	@Resource
	private UserService userService;

	/**
	 * 用户注册
	 * 
	 * @param j
	 * @param phone
	 * @param password
	 * @param code
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/register")
	public Json register(Json j, @RequestParam String phone, @RequestParam String password,
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
			int result = userService.addUser(phone, password);
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

	/**
	 * 更新用户信息
	 * 
	 * @param j
	 * @param id
	 * @param nickName
	 * @param sex
	 * @param birthday
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public Json updateUser(Json j, @RequestParam int id, String nickName, String sex, String birthday,String avatar) throws IOException{
		int result = userService.updateUser(id, nickName, sex, birthday, avatar);
		if (result == 1) {
			systemLogService.add("", id, 1, "更新用户信息：昵称"+nickName+" 性别:"+sex+" 生日:"+birthday+"头像"+avatar);
			j.setMessage("更新成功!");
		} else {
			j.setCode(0);
			j.setMessage("更新失败!");
		}
		return j;
	}

	/**
	 * 修改密码
	 * 
	 * @param j
	 * @param id
	 * @param nickName
	 * @param sex
	 * @param birthday
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public Json changePwd(Json j, @RequestParam String phone, @RequestParam String password,
			@RequestParam String phone_code, HttpServletRequest request) throws ParseException {
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
			int result = userService.updatePwd(phone, password);
			if (result == 1) {
				systemLogService.add("", 0, 1,"手机号为:"+phone +"用户修改了密码!" );
				j.setMessage("修改成功!");
			} else {
				j.setCode(0);
				j.setMessage("修改失败!");
			}
		}
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param j
	 * @param phone
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public Json login(Json j, @RequestParam String phone, @RequestParam String password, HttpServletRequest request) {
		User user = userService.login(phone, password);
		if (user != null) {
			request.getSession().setAttribute(Constants.USER_KEY, user);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("id", user.getId());
			j.setData(map);
			
			systemLogService.add(user.getName(), user.getId(),1, "登陆系统!");
			j.setMessage("登录成功");
		} else {
			j.setCode(0);
			j.setMessage("登录失败");
		}
		return j;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserInfo")
	public Json getUserInfo(Json j, @RequestParam int id) {
		User user = userService.getUserById(id);
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("name", user.getName());
			map.put("nickName", user.getNickName());
			map.put("phone", user.getPhone());
			map.put("sex", user.getSex());
			map.put("avatar", user.getAvatar());
			map.put("birthday", user.getBirthday());
			map.put("qqCode", user.getQqCode());
			map.put("weixinCode", user.getWeixinCode());
			map.put("sinaCode", user.getSinaCode());
			j.setData(map);
		} else {
			j.setCode(0);
			j.setMessage("获取信息失败!");
		}
		return j;
	}

	/**
	 * 绑定手机号
	 * 
	 * @param j
	 * @param user
	 * @param phone_code
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/bindPhone")
	public Json bindPhone(Json j, User user,String phone_code, HttpServletRequest request)
			throws ParseException {
		Integer id = user.getId();
		if (id == null || id.equals(0)) {
			@SuppressWarnings("unchecked")
			Map<String, String> theCode = (Map<String, String>) request.getSession()
					.getAttribute(Constants.BIND_PHONE_CODE);
			if (theCode == null) {
				j.setCode(0);
				j.setMessage("没有发送验证码!");
				return j;
			} else {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long createTime = df.parse(theCode.get("time")).getTime();
				Long nowTime = df.parse(df.format(new Date())).getTime();
				if (nowTime - createTime > 5 * 60 * 1000) {
					request.getSession().removeAttribute(Constants.BIND_PHONE_CODE);
					j.setCode(0);
					j.setMessage("验证码超时!");
					return j;
				}
			}
			String code = (String) theCode.get("code");
			String thePhone = (String) theCode.get("phone");
			if (code == null || thePhone == null || !code.equals(phone_code) || !thePhone.equals(user.getPhone())) {
				j.setCode(0);
				j.setMessage("手机号或者验证码输入错误!");
			} else {
				int result = userService.addBindPhone(user);
				if (result == 1) {
					User theUser = userService.getUsersByConditions(user).get(0);
					Map<String, String> map = new HashMap<String, String>();
					map.put("userId", theUser.getId()+"");
					systemLogService.add(user.getName(), 0, 1, "用户绑定手机号：" + user.getPhone());
					j.setMessage("绑定成功!");
				} else {
					j.setCode(0);
					j.setMessage("绑定失败!");
				}
			}
		}else{
			int result = userService.updateUserByConditions(user);
			if (result == 1) {
				systemLogService.add(user.getName(), 0, 1, "用户绑定其他信息：" + user.getPhone());
				j.setMessage("绑定成功!");
			} else {
				j.setCode(0);
				j.setMessage("绑定失败!");
			}
		}
		return j;
	}
}
