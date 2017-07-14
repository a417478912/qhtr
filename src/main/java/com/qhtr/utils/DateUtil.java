package com.qhtr.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Date date = new Date();
	private static Calendar cale = null;
	/**
	 * 获取当前时间,多少天前的时间
	 * @return
	 */
	public static String getFifteenDaysAgoTime(int day){
		
		long time = date.getTime();
		long newTime = time - (day*24*60*60*1000);
		date.setTime(newTime);
		
		return sdf.format(date);
	}
	
	/**
	 * 获取当天时间的开始
	 * @return
	 */
	public static String getBeginTimeOfToday(){
		// 当天时间开始
		cale = Calendar.getInstance();
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		
		return sdf.format(cale.getTime());
	}
	
	/**
	 * 获取当前月份的开始时间
	 * @return
	 */
	public static String getThisMonthBeginTime(){
		
		// 当前月的第一天
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		
		return sdf.format(cale.getTime());
	}
	
	/**
	 * 获取本周的开始时间
	 * @return
	 */
	public static String getThisWeekBeginTime(){
		
		// 当前周的第一天
		cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		
		return sdf.format(cale.getTime());
	}
	
	/**
	 * 格式化日期格式
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		
		return sdf.format(date);
	}
}
