package qhtr.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathTest {

	public static void main(String[] args) {
		// 去小数点后一位
		/*double b =5.1212;
		DecimalFormat decimalFormat = new DecimalFormat(".#");
		double c =Double.parseDouble(decimalFormat.format(b)) ;
		System.out.println(c);*/
		
		
		/*List<String> list = new ArrayList<>();
		list.add("111");
		list.add("222");
		list.add("333");
		System.out.println(list.size());
		int size = (int)(Math.random() * list.size());
		System.out.println(size);*/
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(r.nextInt(10) + "");
		}
		String pickUpCode = sb.toString();
		System.out.println(pickUpCode);
	}
}
