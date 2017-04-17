package com.qhtr.utils.uuPaotui.openapi.net;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.Response;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;

public class cancelbind {

	public static void main(String[] args) {
		Page_Load();
	}

	protected static void Page_Load() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.BindUserSubmitUrl, mydic);
		Response.Write(result);
	}
}
