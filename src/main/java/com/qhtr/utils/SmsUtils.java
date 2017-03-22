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

	public static String send(String phone,HttpServletRequest request){
	    Map<String, String> headers = new HashMap<String, String>();
	    
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization","APPCODE 6ae24879146747909f74d514547125ab");
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
	    querys.put("TemplateCode", "SMS_57225002");
	    
	    try {
	    	
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    //	System.out.println(EntityUtils.toString(response.getEntity()));
	    	Map<String,String> map = new HashMap<String,String>();
	    	map.put("code",yanzhengCode);
	    	map.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    	request.getSession().setAttribute(Constants.USER_RIGIST_CODE, map);
	    	return "success";
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
}
