package com.qhtr.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerationUtils {
	/**
	 * @param type 固定字符串；生成前缀
	 * @param userId 用户id
	 */
	public static String getGenerationCode(String type,String userId){
		String str1 = "QHTR"+type;
		/**
		 * 位数
		 */
		int s = 8;
		String str2 = "";
		if (userId.length() < s) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s - userId.length(); i++) {
                sb.append(0);
            }
            str2 = sb.toString()+userId;
        }
		String str3 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return str1+str2+str3;
	}
}
