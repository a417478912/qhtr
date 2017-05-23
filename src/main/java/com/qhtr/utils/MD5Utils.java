package com.qhtr.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang.StringUtils;

import com.qhtr.common.Constants;
import com.qhtr.utils.weixinPay.util.MD5Util;

public class MD5Utils {
	public static String getString(String string) {
		try {
			// 创建加密对象
			MessageDigest digest = MessageDigest.getInstance("md5");

			// 调用加密对象的方法，加密的动作已经完成
			byte[] bs = digest.digest(string.getBytes());
			String hexString = "";
			for (byte b : bs) {
				int temp = b & 255;
				if (temp < 16 && temp >= 0) {
					hexString = hexString + "0" + Integer.toHexString(temp);
				} else {
					hexString = hexString + Integer.toHexString(temp);
				}
			}
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * mapmd5 加密
	 * @param map
	 * @return
	 */
	public static String getMd5ByMap(SortedMap<String,String> map){
		StringBuffer sb = new StringBuffer();
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if(!"key".equals(k) && StringUtils.isNotBlank(v)){
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key="+ Constants.WEIXINPUBLIC_APIKEY);
		//String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return appsign;
	}
}
