package com.app.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.UserMessage;
import com.qhtr.service.UserMessageService;
import com.sun.tools.javac.util.List;

@Controller
@RequestMapping(value="/app_userMsg")
public class App_UserMsgController {

	@Resource
	private UserMessageService userMsgService;
	
	/**
	 * 查询消息列表
	 * @param j
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMsgListByUserId")
	public Json getMsgListByUserId(Json j,int userId){
		
		List<UserMessage> list = userMsgService.getMsgListByUserId(userId);
		if (!list.isEmpty()) {
			
			j.setData(list);
			j.setMessage("获取消息列表成功 !");
		}else{
			j.setMessage("暂无消息 !");
		}
		return j;
	}
	
	/**
	 * 删除单条消息
	 * @param j
	 * @param messageId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteMsgById")
	public Json deleteMsgById(Json j,int messageId){
		
		int result = userMsgService.deleteByPrimaryKey(messageId);
		if (result == 1) {
			
			j.setMessage("删除成功 !");
		}else{
			j.setCode(0);
			j.setMessage("删除失败 !");
		}
		return j;
	}
	
	/**
	 * 删除多条消息
	 * @param j
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteMsgBatch")
	public Json deleteMsgBatch(Json j,int[] ids){
		
		int result = userMsgService.deleteBatch(ids);
		if (result == 1) {
			
			j.setMessage("删除成功 !");
		}else{
			
			j.setCode(0);
			j.setMessage("删除失败 !");
		}
		return j;
	}
	
	/**
	 * 清空消息
	 * @param j
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteMsgAll")
	public Json deleteMsgAll(Json j,int userId){
		
		int result = userMsgService.deleteMsgAll(userId);
		
		if (result == 1) {
			
			j.setMessage("清空成功 !");
		}else{
			
			j.setCode(0);
			j.setMessage("清空失败 !");
		}
		return j;
	}
}
