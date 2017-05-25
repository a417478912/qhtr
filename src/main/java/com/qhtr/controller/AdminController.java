package com.qhtr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.common.PageBean;
import com.qhtr.dto.WithdrawApplyListDto;
import com.qhtr.model.Admin;
import com.qhtr.service.AdminService;
import com.qhtr.service.WithdrawService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminService;
	@Resource
	public WithdrawService withdrawService;
	
	@RequestMapping(value="/loginUI")
	public String loginUI(){
		return "admin/login";
	}
	
	@RequestMapping(value="/login")
	public String login(@RequestParam String name,@RequestParam String password,HttpServletRequest request){
		Admin admin = adminService.getValidation(name,password);
		if(admin == null){
			return "admin/login";
		}else{
			request.getSession().setAttribute(Constants.ADMIN_KEY, admin);
			return "redirect:indexUI.do";
		}
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	@RequestMapping(value="/logOut")
	public String logOut(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.ADMIN_KEY);
		return "admin/login";
	}
	
	
	/**
	 *  提现申请列表
	 */
	@RequestMapping(value="/withdrawApplyList")
	public String withdrawApplyList(HttpServletRequest request,@RequestParam(defaultValue="1") int pageNum,@RequestParam(defaultValue="20") int numPerPage,String storeName,String storePhone,Integer status){
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isNotBlank(storeName)){
			map.put("storeName", storeName);
		}
		if(StringUtils.isNotBlank(storePhone)){
			map.put("storePhone", storePhone);
		}
		if(status != null&& status != 0){
			map.put("status", status + "");
		}
		PageHelper.startPage(pageNum, numPerPage);
		List<WithdrawApplyListDto> dtoList = withdrawService.selectDtoListByAdmin(map);
		request.setAttribute("data", new PageBean<WithdrawApplyListDto>(dtoList));
		request.setAttribute("storeName", storeName);
		request.setAttribute("storePhone", storePhone);
		request.setAttribute("status", status);
		
		return "admin/withdrawApplyList";
	}
	
	/**
	 *  申请详情
	 */
	@RequestMapping(value="/applyDetails")
	public String applyDetails(HttpServletRequest request,@RequestParam int id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id+"");
		WithdrawApplyListDto dto = withdrawService.selectDtoByWithdrawId(map);
		request.setAttribute("apply", dto);
		return "admin/applyDetails";
	}
	
	/**
	 * 申请结果处理
	 * @param type type=1 转账成功，type=2 审核失败
	 * @param reason 原因
	 */
	@ResponseBody
	@RequestMapping(value="/applyResult")
	public Json applyResult(Json j,@RequestParam int id,@RequestParam int type,String reason){
		int result = withdrawService.updateApplyResult(id,type,reason);
		if(result == -1){
			j.setCode(0);
			j.setMessage("状态不匹配!");
		}else if(result == 1){
			j.setMessage("处理完成!");
		}
		return j;
	}
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/indexUI")
	public String indexUI(){
		return "index";
	}
	
	@RequestMapping(value="/index1")
	public String index1(){
		return "index1";
	}
	@RequestMapping(value="/index2")
	public String index2(){
		return "index2";
	}
}
