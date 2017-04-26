package com.qhtr.utils.uuPaotui.openapi.net;

import com.qhtr.utils.uuPaotui.openapi.ApiConfig;
import com.qhtr.utils.uuPaotui.openapi.Dictionary;
import com.qhtr.utils.uuPaotui.openapi.Response;
import com.qhtr.utils.uuPaotui.openapi.UUCommonFun;
import com.qhtr.utils.uuPaotui.openapi.UUHttpRequestHelper;
/**
 * 获取订单价格
 * @author wjx
 *
 * @date  2017年4月17日
 */
public class getorderprice {

	public static void main(String[] args) {
		Page_Load();
	}

	protected static void Page_Load() {
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		mydic.Add("from_address", "金水路与玉凤路交汇处浦发国际金融中心");
		//mydic.Add("from_usernote", "21层2111房间");
		mydic.Add("to_address", "金水路与玉凤路交汇处浦发国际金融中心");
		//	mydic.Add("to_usernote", "北门");
		mydic.Add("city_name", "郑州市");
	//	mydic.Add("county_name", "金水区");
		mydic.Add("from_lng", "113.71742");
		mydic.Add("from_lat", "34.767995");
		mydic.Add("to_lng", "113.71742");
		mydic.Add("to_lat", "34.767995");
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.HOST+ApiConfig.GetOrderPriceUrl, mydic);
		Response.Write(result);
	}

}
