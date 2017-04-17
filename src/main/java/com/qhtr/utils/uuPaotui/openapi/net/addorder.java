package com.qhtr.utils.uuPaotui.openapi.net;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;
/**
 * 添加订单
 * @author wjx
 *
 * @date  2017年4月17日
 */
public class addorder {

	public static void main(String[] args) {
		Page_Load();
	}

	private static void Page_Load() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("price_token", "408dfa1c6ea14a0fa782fc33d7c784a1");
		mydic.Add("order_price", "17.00");
		mydic.Add("balance_paymoney", "17.00");
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
