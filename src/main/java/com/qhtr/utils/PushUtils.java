package com.qhtr.utils;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;

/**
 * @author Harry
 * @Description 极光推送工具类
 * @date  2017年6月5日
 */
public class PushUtils {
	/**
	 * 给所有平台的所有用户发送通知
	 * @param message
	 */
	public static void sendAllSetNotification(String message){
	
		JPushClient jPushClient = new JPushClient("masterSecret", "appKey");
		// 添加附加消息
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("extMessage", "我是附加消息!");
		// 发送通知
	}

}

