package com.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.StoreListDto_App;
import com.github.pagehelper.PageHelper;
import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.dto.CollectDto;
import com.qhtr.dto.UserDto;
import com.qhtr.model.Collect;
import com.qhtr.model.Goods;
import com.qhtr.model.Sku;
import com.qhtr.model.User;
import com.qhtr.service.AttentionService;
import com.qhtr.service.CollectService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.SkuService;
import com.qhtr.service.StoreService;
import com.qhtr.service.SystemLogService;
import com.qhtr.service.UserService;

@Controller
@RequestMapping("/app_user")
public class App_UserController {
	@Resource
	public SystemLogService systemLogService;

	@Resource
	public UserService userService;
	
	@Resource
	public CollectService collectService;
	
	@Resource
	public AttentionService attentionService;
	
	@Resource
	public GoodsService goodsService;
	
	@Resource
	public SkuService skuService;

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
			systemLogService.add("", id, 1, "更新用户信息：昵称"+nickName+" 性别:"+sex+" 生日:"+birthday+"头像:");
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
				request.getSession().removeAttribute(Constants.USER_CHANGE_PWD_CODE);
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
			j.setMessage("登录成功!");
		} else {
			j.setCode(0);
			j.setMessage("账号或密码错误!");
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
			j.setData(new UserDto(user));
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
	public Json bindPhone(Json j,@RequestParam int id,@RequestParam String phone,@RequestParam String phone_code, HttpServletRequest request) throws ParseException {
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
			if (code == null || thePhone == null || !code.equals(phone_code) || !thePhone.equals(phone)) {
				j.setCode(0);
				j.setMessage("手机号或者验证码输入错误!");
			} else {
				int result = userService.addBindPhone(id,phone);
				if (result == 1) {
					systemLogService.add("", 0, 1, "用户:"+id+"绑定手机号：" + phone+"成功!");
					j.setMessage("绑定成功!");
				} else if(result == 0){
					j.setCode(0);
					j.setMessage("绑定失败!");
				} else if(result == 2){
					j.setCode(0);
					j.setMessage("此手机号已绑定!");
				}
			}
			return j;
	}
	
	/**
	 * 解除绑定手机号
	 * 
	 * @param j
	 * @param user
	 * @param phone_code
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/unBindPhone")
	public Json unBindPhone(Json j,@RequestParam int id,@RequestParam String phone,@RequestParam String phone_code, HttpServletRequest request) throws ParseException {
			@SuppressWarnings("unchecked")
			Map<String, String> theCode = (Map<String, String>) request.getSession().getAttribute(Constants.UN_BIND_PHONE_CODE);
			if (theCode == null) {
				j.setCode(0);
				j.setMessage("没有发送验证码!");
				return j;
			} else {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long createTime = df.parse(theCode.get("time")).getTime();
				Long nowTime = df.parse(df.format(new Date())).getTime();
				if (nowTime - createTime > 5 * 60 * 1000) {
					request.getSession().removeAttribute(Constants.UN_BIND_PHONE_CODE);
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
				User user = userService.getUserById(id);
				if (phone.equals(user.getPhone())) {
					systemLogService.add("", 0, 1, "用户:"+id+"验证绑定手机号：" + phone+"成功!");
					j.setMessage("验证成功!");
				} else{
					j.setCode(0);
					j.setMessage("验证失败!");
				}
			}
			return j;
	}
	
	/**
	 * 绑定其他三方
	 * @param j
	 * @param user
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bindThirdLogin")
	public Json bindThirdLogin(Json j,User user){
		int result = userService.updateUserByConditions(user);
		if(result == 1){
			j.setMessage("绑定成功");
		}else{
			j.setCode(0);
			j.setMessage("绑定失败!");
		}
		return j;
	}
	
	
	/**
	 * 三方登陆
	 * @param j  1.qq登陆  2 微信登陆 3微博
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thirdLogin")
	public Json thirdLogin(Json j,User user){
		int result = 1;
		User userTem = new User();
		if(StringUtils.isNotBlank(user.getQqCode())){
			userTem.setQqCode(user.getQqCode());
		}else if(StringUtils.isNotBlank(user.getWeixinCode())){
			userTem.setWeixinCode(user.getWeixinCode());
		}else if(StringUtils.isNotBlank(user.getSinaCode())){
			userTem.setSinaCode(user.getSinaCode());
		}else{
			j.setCode(0);
			j.setMessage("请求有问题!");
			return j;
		}
		List<User> userList = userService.selectByConditions(userTem);
		if(userList.isEmpty()){
			result = userService.addUser(user);
		}
		if(result == 1){
			User theUser = userService.selectByConditions(userTem).get(0);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", theUser.getId());
			j.setData(map);
		}else{
			j.setCode(0);
			j.setMessage("登陆失败!");
		}
		return j;
	}
	
	/**
	 * 获取用户收藏列表
	 * @param j
	 * @param userId
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/getCollectList")
	public Json getCollectList(Json j,@RequestParam int userId){
		List<CollectDto> list  = collectService.selectByUserid(userId);
		j.setData(list);
		return j;
	}
	
	/**
	 * 添加收藏
	 * @param j
	 * @param userId
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCollect")
	public Json addCollect(Json j,@RequestParam int userId,@RequestParam int goodsId){
		int result = collectService.addCollect(userId,goodsId);
		if(result == 1){
			j.setMessage("收藏成功!");
		}else if(result == -1){
			j.setCode(0);
			j.setMessage("已经收藏过此店铺!");
		}else{
			j.setCode(0);
			j.setMessage("收藏失败!");
		}
		return j;
	}
	
	/**
	 * 删除收藏
	 * @param j
	 * @param collectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCollect")
	public Json deleteCollect(Json j,@RequestParam int collectId){
		int result = collectService.deleteCollect(collectId);
		if(result == 1){
			j.setMessage("删除成功!");
		}else{
			j.setCode(0);
			j.setMessage("删除收藏失败!");
		}
		return j;
	}
	
	/**
	 * 收藏，查看更多
	 * @param j
	 * @param userId
	 * @param categoryId  categoryId :0 ， 最近收藏     categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMoreCollect")
	public Json getMoreCollect(Json j,@RequestParam int userId,@RequestParam int categoryId,@RequestParam(defaultValue="1") int page){
		PageHelper.startPage(page, 10);
		List<Map<String,Object>> list = collectService.selectByRecentCollect(userId,categoryId);
		for (Map<String, Object> map : list) {
			int topPrice = 0;
			int lowPrice = 10000000;
			int goodsId = Integer.parseInt(map.get("goodsId").toString());
			List<Sku> skuList = skuService.selectListByGoodsId(goodsId);
			for (Sku sku : skuList) {
				if(sku.getPrice() > topPrice){
					topPrice = sku.getPrice();
				}
				if(sku.getPrice() < lowPrice){
					lowPrice = sku.getPrice();
				}
			}
			map.put("topPrice", topPrice);
			map.put("lowPrice", lowPrice);
		}
		j.setData(list);
		return j;
	}
	
	
	/**
	 * 获取关注列表
	 * @param j
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAttentionList")
	public Json getAttentionList(Json j,@RequestParam int userId){
		List<Map<String,Object>> list  = attentionService.getAttentionList(userId);
		if(list != null){
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : list) {
				int storeId = Integer.parseInt(map.get("storeId").toString());
				PageHelper.startPage(1,3);
				List<Goods> goodsList = goodsService.selectListByStoreAndType(storeId, 1);
				Map<String,Object> theMap = new HashMap<String,Object>();
				theMap.put("id", map.get("id"));
				theMap.put("storeId", map.get("storeId"));
				theMap.put("storeName", map.get("storeName"));
				theMap.put("storeAvatar", map.get("storeAvatar"));
				theMap.put("goodsList", goodsList);
				mapList.add(theMap);
			}
			j.setData(mapList);
		}else{
			j.setMessage("没有关注店铺!");
		}
		return j;
	}
	
	/**
	 * 添加关注
	 * @param j
	 * @param userId
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addAttention")
	public Json addAttention(Json j,@RequestParam int userId,@RequestParam int storeId){
		int result = attentionService.addAttention(userId,storeId);
		if(result == 1){
			j.setMessage("关注成功!");
		}else if(result == -1){
			j.setCode(0);
			j.setMessage("已经关注过此店铺!");
		}else{
			j.setCode(0);
			j.setMessage("关注失败!");
		}
		return j;
	}
	
	/**
	 * 取消关注
	 * @param j
	 * @param collectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAttention")
	public Json deleteAttention(Json j,@RequestParam int attentionId){
		int result = attentionService.deleteAttention(attentionId);
		if(result == 1){
			j.setMessage("删除成功!");
		}else{
			j.setCode(0);
			j.setMessage("删除关注失败!");
		}
		return j;
	}
}
