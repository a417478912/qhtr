package qhtr.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String format2 = sdf.format(date);
		System.out.println(format2);
		long time = date.getTime();
		
		Date date1 = new Date();
		date1.setTime(time-(15*24*60*60*1000));
		String format = sdf.format(date1);
		System.out.println(format);
	}
}
