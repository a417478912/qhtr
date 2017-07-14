package qhtr.test;

public class TestString {

	public static void main(String[] args) {
		
		String s = "11211";
		String substring = s.substring(s.indexOf("2"), s.length());
		System.out.println(substring);
	}
}
