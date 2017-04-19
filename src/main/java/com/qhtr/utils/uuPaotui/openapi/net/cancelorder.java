package com.qhtr.utils.uuPaotui.openapi.net;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.Response;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;
/**
 * 取消订单
 * @author wjx
 *
 * @date  2017年4月17日
 */
public class cancelorder {

	public static void main(String[] args) {
		Page_Load();
	}

	protected static void Page_Load() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("order_code", "U37908001704191351189522499");
		mydic.Add("reason", "用户需求变更，订单取消");
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.CancelOrderUrl, mydic);
		Response.Write(result);
	}
}

