package com.qhtr.utils.uuPaotui.openapi.net;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.Response;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;
/**
 * 订单详情
 * @author wjx
 *
 * @date  2017年4月17日
 */
public class getorderdetail {
	public static void main(String[] args) {
		Page_Load();
	}

	protected static void Page_Load() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("order_code", "1481");
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.GetOrderDetailUrl, mydic);
		Response.Write(result);
	}
}
