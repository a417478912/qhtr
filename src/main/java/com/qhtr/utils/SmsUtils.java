package com.qhtr.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class SmsUtils {
	static String host = "http://sms.market.alicloudapi.com";
	static String path = "/singleSendSms";
	static String method = "GET";

	public static String send(String phone){
	    Map<String, String> headers = new HashMap<String, String>();
	    
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization","APPCODE 6ae24879146747909f74d514547125ab");
	    Map<String, String> querys = new HashMap<String, String>();
	    String paramString = "{\"code\":\""+"666666"+"\"}";
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
	    querys.put("TemplateCode", "【小逛一下】您的短信验证码为:${code},请妥善保存！");
	    
	    try {
	    	
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return "";
	}
	public static void main(String[] args) {
		SmsUtils.send("15324932625");
	}
}
