package com.qhtr.common;
public class _Global {
	
	public static int APP_PAGE_SIZE = 5;
	public static String APP_KEY = "1177170615178739#smallshop";
	public static String APP_CLIENT_ID = "YXA6E4gf4FGTEeefR8NQFj1TYA";
	public static String APP_CLIENT_SECRET = "YXA6kENz0Ul5arlAOwKwoLD6DaEYuSo";
	//TODO 以上参数需相应修改
	public static final int HTTP_METHOD_GET = 1;
	public static final int HTTP_METHOD_POST = 2;
	public static final int HTTP_METHOD_PUT = 3;
	public static final int HTTP_METHOD_DELETE = 4;
	public static final String URL_HOST = "http://a1.easemob.com/"+APP_KEY.replace("#","/")+"/";
	public static final String URR_TOKEN = URL_HOST+"token";
	public static final String URL_CHAT = URL_HOST+"chatmessages";
	public static final String URL_GROUP = URL_HOST+"chatgroups";
	public static final String URL_FILE = URL_HOST+"chatfiles";
	public static final String URL_ROOM = URL_HOST+"chatrooms";
	public static final String URL_MESSAGES = URL_HOST+"messages";
	public static final String URL_USER = URL_HOST+"users";
}