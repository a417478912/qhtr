package com.qhtr.common;

public class Constants {
	
	public final static String ADMIN_KEY = "adminName";
	
	public static final String masterSecret = "8226191ca644cf6584a7edf8";
	public static final String appKey = "37c1e046219d57b87b711fc9";
	/**
	 * 用户信息
	 */
	public final static String USER_KEY = "userName";
	
	/**
	 * 用户注册码
	 */
	public final static String USER_RIGIST_CODE = "userRegistCode";
	/**
	 * 修改密码 短信码
	 */
	public final static String USER_CHANGE_PWD_CODE = "userChangePwdCode";
	/**
	 * 绑定手机号 短信吗
	 */
	public final static String BIND_PHONE_CODE = "bindPhoneCode";
	
	/**
	 * 解绑手机号 短信吗
	 */
	public final static String UN_BIND_PHONE_CODE = "unBindPhoneCode";
	
	/**
	 * 购物车ids
	 */
	public final static String CART_IDS = "cartIds";
	/**
	 * 购物车生成的订单(商铺订单)
	 */
	public final static String STORE_ORDER_BUYCART = "storeOrderBuycart";
	/**
	 * 购物车生成的订单(商品订单)
	 */
	public final static String GOODS_ORDER_BUYCART = "goodsOrderBuycart";
	
	/**
	 * 微信支付的信息
	 */
	public final static String WEIXINPAY_KEY = "iq8sw2BnVEaOTIzN4WfM7GLip6tu3psM";
	public final static String WEIXINPAY_APPID = "wx2e2b770d217cb2e7";
	public final static String WEIXINPAY_APPSECRET = "d653b81f686c3b92ae5e36249de504ab";  
	public final static String WEIXINPAY_PARTNER = "1430950202";//商户号 
	public final static String WEIXINPAY_PARTNERKEY = "goodsOrderBuycart"; //不是商户登录密码，是商户在微信平台设置的32位长度的api秘钥，
	public final static String WEIXINPAY_NOTIFY_URL = "http://114.55.248.53/qhtr/app_pay/weixinPayResult.do";
	
	/**
	 * 微信公众平台
	 * 
	 */
	public final static String WEIXINPUBLIC_APIKEY = "jixia6cqer2vqqu2f3jzl9h909e960ip";
	public final static String WEIXINPUBLIC_APPID = "wx00527dd179ff7578";
	public final static String WEIXINPUBLIC_APPSECRET = "8fcf56bc7380d74033468c9b7cc00bc5";
	public final static String WEIXINPUBLIC_TOKEN = "7htr_weixinpublic_token";
	public final static String WEIXINPUBLIC_EncodingAESKey = "MXoXjrfoOR4EHznMrdFpk7VqXEyqtY6k5UuJ8UBUO6T";
	
	/**
	 * comet
	 */
	 public static long EXPIRE_AFTER_ONE_HOUR = 30; //cache过期时间
     public static String CHANNEL_MSGCOUNT = "msgCount";
	 public static String CHANNEL_MSG_DATA = "msgData";
	 /**
	  * Jpush
	  */
	 public static String JPUSH_MASTER_SECRET = "8226191ca644cf6584a7edf8";
	 public static String JPUSH_APP_KEY = "37c1e046219d57b87b711fc9";
}
