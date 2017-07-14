package com.sell.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Store;
import com.qhtr.model.UserMessage;
import com.qhtr.service.StoreService;
import com.qhtr.service.UserMessageService;
import com.qhtr.utils.DateUtil;
import com.qhtr.utils.JPushUtils;

@Controller
@RequestMapping(value="/sell_jpushMessage")
public class Sell_JpushMessageController {

	@Resource
	private StoreService storeService;
	@Resource
	private UserMessageService userMsgService;
	/**
	 * 向关注店铺的用户推送信息
	 * @param j
	 * @param message
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/pushMessageToUser")
	public Json pushMessageToUser(Json j,String message,int storeId,int type,int linkId){
		
		List<Integer> userIdList = storeService.getAttentionUserByStoreId(storeId);
		if (!userIdList.isEmpty()) {
			Set<String> alias = new HashSet<>();
			for (Integer userId : userIdList) {
				
				// 将消息存入数据库
				UserMessage userMsg = new UserMessage();
				
				userMsg.setCreateTime(DateUtil.formatDate(new Date()));
				userMsg.setType(type);
				userMsg.setMessageContent("欢迎关注本店~经常来逛逛哦~");
				String storeAvatar = getStoreAvatar(storeId);
				if (storeAvatar != null) {
					
					userMsg.setStoreAvatar(storeAvatar);
				}
				userMsg.setLinkId(storeId);
				userMsg.setUserId(userId);
				userMsgService.insert(userMsg);
				
				alias.add(userId + "");
			}
			try {
				JPushUtils.sendPush(JPushUtils.pushToIOSByAlias(message, alias), LoggerFactory.getLogger(getClass()));
				j.setMessage("推送成功 !");
			} catch (Exception e) {
				e.printStackTrace();
				j.setMessage("推送失败 !");
			}
		}else{
			
			j.setMessage("暂无关注的用户 !");
		}
		return j;
	}
	
	private String getStoreAvatar(int storeId) {
		
		Store store = storeService.selectStoreById(storeId);
		if (store != null) {
			
			return store.getAvatar();
		}
		return null;
	}
}
