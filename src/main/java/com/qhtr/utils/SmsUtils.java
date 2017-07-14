package com.qhtr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;

import com.qhtr.common.Constants;

public class SmsUtils {
	
	static String host = "http://sms.market.alicloudapi.com";
	static String path = "/singleSendSms";
	static String method = "GET";
	
	/**
	 * @param phone
	 * @param request
	 * @param type 短信类型 1：用户注册   2：修改密码 .3.手机号绑定 4.确认/取消绑定手机号
	 * @return
	 */
	public static String send(String phone,HttpServletRequest request,int type){
		String templateCode = "";
		if(type == 1){
			templateCode = "SMS_57225002";
		}else if(type == 2){
			templateCode = "SMS_57715164";
		}else if(type == 3 || type == 4){
			templateCode = "SMS_58385092";
		}
	    Map<String, String> headers = new HashMap<String, String>();
	    
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization","APPCODE 40734cf9225442cea08a0eec90146149");
	    Map<String, String> querys = new HashMap<String, String>();
	    String yanzhengCode = SmsUtils.getNum();
	    
	    String paramString = "{\"code\":\""+yanzhengCode+"\"}";
	    System.out.println(paramString);
	    querys.put("ParamString", paramString);
	    /**
	     * 目标手机号,多条记录可以英文逗号分隔
	     */
	    querys.put("RecNum", phone);
	    /**
	     * 签名名称
	     */
	    querys.put("SignName", "小逛一下");
	    /**
	     * 模板CODE
	     */
	    querys.put("TemplateCode", templateCode);
	    
	    try {
	    	
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    //	System.out.println(EntityUtils.toString(response.getEntity()));
	    	Map<String,String> map = new HashMap<String,String>();
	    	map.put("code",yanzhengCode);
	    	map.put("phone", phone);
	    	map.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    	if(type == 1){
	    		request.getSession().setAttribute(Constants.USER_RIGIST_CODE, map);
	    	}else if(type == 2){
	    		request.getSession().setAttribute(Constants.USER_CHANGE_PWD_CODE, map);
	    	}else if(type == 3){
	    		request.getSession().setAttribute(Constants.BIND_PHONE_CODE, map);
	    	}else if(type == 4){
	    		request.getSession().setAttribute(Constants.UN_BIND_PHONE_CODE, map);
	    	}
	    	//return "success";
	    	return yanzhengCode;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	private static String getNum() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			sb.append(new Random().nextInt(9)); 
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static void sendMessage(String phone,HttpServletRequest request,String storeName){
		String templateCode = "SMS_74580010";
		Map<String, String> headers = new HashMap<String, String>();
	    
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization","APPCODE 40734cf9225442cea08a0eec90146149");
	    Map<String, String> querys = new HashMap<String, String>();
	    
	    String paramString = "{\"storeName\":\" "+storeName+" \"}";
	    System.out.println(paramString);
	    querys.put("ParamString", paramString);
	    /**
	     * 目标手机号,多条记录可以英文逗号分隔
	     */
	    querys.put("RecNum", phone);
	    /**
	     * 签名名称
	     */
	    querys.put("SignName", "小逛一下");
	    /**
	     * 模板CODE
	     */
	    querys.put("TemplateCode", templateCode);
	    
	    try {
	    	
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	}
	
	/**
	 * 向商家发送库存为0的短信
	 * @param phone
	 * @param request
	 * @param goodsName
	 */
	public static void sendMessageAboutStock(String phone,HttpServletRequest request,String goodsName){
		String templateCode = "SMS_74880023";
		Map<String, String> headers = new HashMap<String, String>();
		
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization","APPCODE 40734cf9225442cea08a0eec90146149");
		Map<String, String> querys = new HashMap<String, String>();
		
		String paramString = "{\"goodsName\":\" "+goodsName+" \"}";
		System.out.println(paramString);
		querys.put("ParamString", paramString);
		/**
		 * 目标手机号,多条记录可以英文逗号分隔
		 */
		querys.put("RecNum", phone);
		/**
		 * 签名名称
		 */
		querys.put("SignName", "小逛一下");
		/**
		 * 模板CODE
		 */
		querys.put("TemplateCode", templateCode);
		
		try {
			
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			System.out.println(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
