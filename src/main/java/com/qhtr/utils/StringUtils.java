package com.qhtr.utils;

public class StringUtils {
	/**
	 * 手机号加码
	 */
	public static String phoneOverweight(String phone){
		if(phone == null){
			return null;
		}else{
			return phone.substring(0,3)+"****"+phone.substring(7,10);
		}
	}
}
