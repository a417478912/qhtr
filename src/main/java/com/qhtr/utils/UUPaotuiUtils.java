package com.qhtr.utils;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;

public class UUPaotuiUtils {
	/**
	 * 下单
	 * @return
	 */
	public static String addOrder() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("price_token", "086082b8106e4827ab3190dd9e45eb07");
		mydic.Add("order_price", "8.00");
		mydic.Add("balance_paymoney", "8.00");
		mydic.Add("receiver", "张三");
		mydic.Add("receiver_phone", "13766666666");
		mydic.Add("note", "请尽快取件");
		mydic.Add("callback_url", "http://www.uupt.com");
		mydic.Add("push_type", "0");
		mydic.Add("special_type", "0");
		mydic.Add("callme_withtake", "1");
		mydic.Add("pubUserMobile", "15324932625");
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.AddOrderUrl, mydic);
		return result;
	}
	
	/**
	 * 取消订单
	 * @return
	 */
	public static String cancelOrder() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("order_code", "U37908001704191351189522499");
		mydic.Add("reason", "用户需求变更，订单取消");
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.CancelOrderUrl, mydic);
		return result;
	}
	
	/**
	 * 获取订单详情
	 * @return
	 */
	public static String GetOrderDetail() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("order_code", "1481");
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
		mydic.Add("from_address", "金水路与玉凤路交汇处浦发国际金融中心");//"金水路与玉凤路交汇处浦发国际金融中心"        目的地址
		mydic.Add("to_address","金水路与玉凤路交汇处浦发国际金融中心");// "金水路与玉凤路交汇处浦发国际金融中心"    	 	目的地址具体门牌号
		mydic.Add("city_name", "郑州市");//							订单所在城市名 称(如郑州市就填”郑州市“，必须带上“市”)
		mydic.Add("from_lng", "113.71742");//					起始地坐标经度，如果无，传0(坐标系为百度地图坐标系)	
		mydic.Add("from_lat", "34.767995");//					起始地坐标纬度，如果无，传0(坐标系为百度地图坐标系)
		mydic.Add("to_lng", "113.71742");//						目的地坐标经度，如果无，传0(坐标系为百度地图坐标系)
		mydic.Add("to_lat",  "34.767995");//                      目的地坐标纬度，如果无，传0(坐标系为百度地图坐标系)
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.GetOrderPriceUrl, mydic);
		return result;
	}
}
