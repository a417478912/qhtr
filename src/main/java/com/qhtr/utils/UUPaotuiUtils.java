package com.qhtr.utils;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;

public class UUPaotuiUtils {
	public static void addOrder() {
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
		System.out.println(result);
	}
}
