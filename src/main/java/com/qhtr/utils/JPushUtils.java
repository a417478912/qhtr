package com.qhtr.utils;

import java.util.Set;

import org.slf4j.Logger;

import com.qhtr.common.Constants;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
/**
 * @author Harry
 * @Description 极光推送工具类
 * @date  2017年6月8日
 */
public class JPushUtils {
	
	private static final JPushClient jPushClient = new JPushClient(Constants.masterSecret, Constants.appKey);
	
	
	/**
	 * 进行推送
	 * @param payLoad
	 * @param LOG
	 */
	public static void sendPush(PushPayload payLoad,Logger LOG){
		
		try {
			PushResult result = jPushClient.sendPush(payLoad);
			// 绑定手机号
			// jPushClient.bindMobile(registrationId, mobile);
			
			System.out.println("result ++++++++++++++++" + result);
			
			LOG.info("result ==========>>>>" + result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			LOG.error("Connection error. Should retry later. ", e);
			
		} catch (APIRequestException e) {
			e.printStackTrace();
			LOG.error("Error response from JPush server. Should review and fix it. ", e);  
	        LOG.info("HTTP Status: " + e.getStatus());  
	        LOG.info("Error Code: " + e.getErrorCode());  
	        LOG.info("Error Message: " + e.getErrorMessage());  
	        LOG.info("Msg ID: " + e.getMsgId());  
		}
	}
	
	/**
	 * 向指定用户发送消息
	 * @param message
	 * @param aliases
	 * @return
	 */
	public static PushPayload sendMessageToIOSByAlias(String message,Set<String> aliases){
		return 
				 PushPayload.newBuilder()
				 .setPlatform(Platform.ios())
				 .setAudience(Audience.alias(aliases))
				 .setMessage(Message.content(message))
				 .build();
			}
	
	/**
	 * 向所有平台所有用户推送通知
	 * @param alert
	 * @return
	 */
	 public static PushPayload PushAll(String alert) {
		 
	        return PushPayload.alertAll(alert);
	    }
	 
	 /**
	  * 向指定用户推送消息
	  * @param alert 推送内容
	  * @param alias registrationIds集合
	  * @return
	  */
	 public static PushPayload pushToIOSByAlias(String alert,Set<String> alias){
		 
		 return PushPayload.newBuilder().
				 setAudience(Audience.alias(alias)).
				 setPlatform(Platform.ios()).
				 setNotification(Notification.alert(alert)).
				 build();
	 }
	 
	 /**
	  * 所有平台所有用户推送通知,并发送短息
	  * @param alert
	  * @param message
	  * @return
	  */
	 public static PushPayload pushAllAndSendSms(String alert,String message){
		 
		 return PushPayload.alertAll(alert, SMS.content(message, 0));
	 }
	 
	 /**
	  * 向所有平台所有用户推送消息
	  * @param message
	  * @return
	  */
	 public static PushPayload pushMessageAll(String message){
		 
		 return PushPayload.messageAll(message);
	 }
	 
	 /**
	  * 向所有平台所有用户推送消息,并短信通知
	  * @param message
	  * @param content
	  * @return
	  */
	 public static PushPayload pushMessageAndSmsAll(String message,String content){
		 
		 return PushPayload.messageAll(message, SMS.content(content, 0));
	 }
	 
	 /**
	  * 向ios平台的所有用户推送通知
	  * @param alert
	  * @return
	  */
	 public static PushPayload sendNotificationToIOS(String alert){
		 
		 return 
				 PushPayload.newBuilder()
				 	.setPlatform(Platform.ios())
				 	.setAudience(Audience.all())
				 	.setNotification(Notification.alert(alert))
				 	.build();
		 
	 }
	 /**
	  * 向IOS平台的所有用户推送通知,并短信通知
	  * @param alert
	  * @param message
	  * @return
	  */
	 public static PushPayload sendNotificationAndSmsToIOS(String alert,String message){
		 
		 return PushPayload.newBuilder()
				 .setPlatform(Platform.ios())
				 .setAudience(Audience.all())
				 .setNotification(Notification.alert(alert))
				 .setSMS(SMS.content(message, 0))
				 .build()
				 ;
	 }
	 
	 /**
	  * 向Android平台所有用户推送通知
	  * @param alert
	  * @return
	  */
	 public static PushPayload sendNotificationToAndroid(String alert){
		 
		return 
				PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(alert))
				.build();
		
		 
	 }
	 /**
	  * 向Android平台的所有用户推送通知,并短信通知
	  * @param alert
	  * @param message
	  * @return
	  */
	 public static PushPayload sendNotificationAndSmsToAndroid(String alert,String message){
			 
			 return PushPayload.newBuilder()
					 .setPlatform(Platform.android())
					 .setAudience(Audience.all())
					 .setNotification(Notification.alert(alert))
					 .setSMS(SMS.content(message, 0))
					 .build()
					 ;
		 }
	 
	 /**
	  * 向ios平台的所有用户推送消息
	  * @param message
	  * @return
	  */
	 public static PushPayload sendMessageToIOS(String message){
		 return 
				 PushPayload.newBuilder()
				 .setPlatform(Platform.ios())
				 .setAudience(Audience.all())
				 .setMessage(Message.content(message))
				 .build();
	 }
	 
	 /**
	  * 向iOS平台所有用户推送消息,并短信通知
	  * @param message
	  * @param sms
	  * @return
	  */
	 public static PushPayload sendMessageAndSmsToIOS(String message,String sms){
		 return 
				 PushPayload.newBuilder()
				 .setPlatform(Platform.ios())
				 .setAudience(Audience.all())
				 .setMessage(Message.content(message))
				 .setSMS(SMS.content(sms, 0))
				 .build();
	 }
	 /**
	  * 向Android平台的所有用户推送消息
	  * @param message
	  * @return
	  */
	 public static PushPayload sendMessageToAndroid(String message){
		 return 
				 PushPayload.newBuilder()
				 .setPlatform(Platform.android())
				 .setAudience(Audience.all())
				 .setMessage(Message.content(message))
				 .build();
	 }
	 
	 /**
	  * 向iOS平台所有用户推送消息,并短信通知
	  * @param message
	  * @param sms
	  * @return
	  */
	 public static PushPayload sendMessageAndSmsToAndroid(String message,String sms){
		 return 
				 PushPayload.newBuilder()
				 .setPlatform(Platform.android())
				 .setAudience(Audience.all())
				 .setMessage(Message.content(message))
				 .setSMS(SMS.content(sms, 0))
				 .build();
	 }
	 
}
