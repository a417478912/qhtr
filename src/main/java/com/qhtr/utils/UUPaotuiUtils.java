package com.qhtr.utils;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;

public class UUPaotuiUtils {
	/**
	 * 下单
	 * @param price_token  金额令牌，计算订单价格接口返回的price_token
	 * @param order_price  订单金额，计算订单价格接口返回的total_money
	 * @param balance_paymoney 	实际余额支付金额计算订单价格接口返回的need_paymoney
	 * @param receiver	收件人
	 * @param receiver_phone 收件人电话
	 * @param note	订单备注 最长140个汉字
	 * @param pubUserMobile	发件人电话，（如果为空则是用户注册的手机号）
	 * @return
	 */
	public static String addOrder(String price_token,String order_price,String balance_paymoney,String receiver,String receiver_phone,String note,String pubUserMobile) {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("price_token", price_token);
		mydic.Add("order_price", order_price);
		mydic.Add("balance_paymoney", balance_paymoney);
		mydic.Add("receiver", receiver);
		mydic.Add("receiver_phone", receiver_phone);
		mydic.Add("note", note);
		mydic.Add("callback_url", "http://www.uupt.com");   //非必填字段     订单提价成功后的回调地址
		mydic.Add("push_type", "0");		//推送方式（0 开放订单，1指定跑男，2商户绑定的跑男）默认传0即可
		mydic.Add("special_type", "0");   //特殊处理类型，是否需要保温箱 1需要 0不需要
		mydic.Add("callme_withtake", "1");  //非必须字段  取件是否给我打电话 1需要 0不需要
		mydic.Add("pubUserMobile", pubUserMobile);  //非必须字段   发件人电话，（如果为空则是用户注册的手机号）
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.AddOrderUrl, mydic);
		return result;
	}
	
	/**
	 * 取消订单
	 * @param order_code  UU跑腿订单编号
	 * @param reason 取消原因
	 * @return
	 */
	public static String cancelOrder(String order_code,String reason) {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("order_code", order_code);
		mydic.Add("reason", reason);
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.CancelOrderUrl, mydic);
		return result;
	}
	
	/**
	 * 获取订单详情   UU跑腿订单编号
	 * @return  
	 */
	public static String GetOrderDetail(String order_code) {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("order_code", order_code);
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.GetOrderDetailUrl, mydic);
		return result;
	}
	
	/**
	 * 计算订单价格
	 * @return
	 */
	public static String getOrderPrice(String fromAddress,String toAddress,String fromLng,String fromLat,String toLng,String toLat) {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("from_address", fromAddress);//"金水路与玉凤路交汇处浦发国际金融中心"        目的地址
		mydic.Add("to_address",toAddress);// "金水路与玉凤路交汇处浦发国际金融中心"    	 	目的地址具体门牌号
		mydic.Add("city_name", "郑州市");//							订单所在城市名 称(如郑州市就填”郑州市“，必须带上“市”)
		mydic.Add("from_lng", fromLng);//					起始地坐标经度，如果无，传0(坐标系为百度地图坐标系)	
		mydic.Add("from_lat", fromLat);//					起始地坐标纬度，如果无，传0(坐标系为百度地图坐标系)
		mydic.Add("to_lng", toLng);//						目的地坐标经度，如果无，传0(坐标系为百度地图坐标系)
		mydic.Add("to_lat",  toLat);//                      目的地坐标纬度，如果无，传0(坐标系为百度地图坐标系)
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.GetOrderPriceUrl, mydic);
		System.out.println(result);
		return result;
	}
}
