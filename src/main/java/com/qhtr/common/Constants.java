package com.qhtr.common;

public class Constants {
	public final static String ADMIN_KEY = "adminName";
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
	public final static String WEIXINPAY_APPID = "wx2e2b770d217cb2e7";
	public final static String WEIXINPAY_APPSECRET = "d653b81f686c3b92ae5e36249de504ab";  
	public final static String WEIXINPAY_PARTNER = "1430950202";//商户号 
	public final static String WEIXINPAY_PARTNERKEY = "goodsOrderBuycart"; //不是商户登录密码，是商户在微信平台设置的32位长度的api秘钥，
	public final static String WEIXINPAY_NOTIFY_URL = "goodsOrderBuycart";
}
