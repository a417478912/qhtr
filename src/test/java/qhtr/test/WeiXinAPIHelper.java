package qhtr.test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.qhtr.common.Constants;
import com.qhtr.utils.HttpUtils;
import com.qhtr.utils.weixinMessage.model.AccessToken;
import com.qhtr.utils.weixinMessage.model.TemplateData;
import com.qhtr.utils.weixinMessage.model.WxTemplate;
import com.qhtr.utils.weixinMessage.utils.WeiXinUtils;

public class WeiXinAPIHelper {
	
		/**
		 * 获取微信授权的code
		 */
	    public static String  GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	    
	   //获取授权请求

	    public static String getCodeRequest(){

	        String result = null;

	        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8(Constants.WEIXINPUBLIC_APPID));

	        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8("http://www.7htr.com/qhtr/sell_stroe/getOpenIdByCode.do"));

	        GetCodeRequest = GetCodeRequest.replace("SCOPE", "snsapi_base");

	        GetCodeRequest = GetCodeRequest.replace("STATE", "54");
	        
	        result = GetCodeRequest;

	        return result;

	    }
	    
	    public static void main(String[] args) {
	    	
	    	WxTemplate temp = new WxTemplate();
	    	
	    	temp.setTemplate_id("6dua0sxoLSexeEN8niW-B2ysIzklA4yL28M8_SISbkI");
	    	temp.setTouser("ogUf-wRS5zsuYhMNstGW6Sk7Ii6w");
	    	temp.setUrl("www.baidu.com");
	    	
	    	Map<String, TemplateData> data = new HashMap<>();
	    	
	    	TemplateData first = new TemplateData();
	    	
	    	first.setValue("哈哈哈哈");
	    	data.put("first", first);
	    	
	    	TemplateData orderMoneySum = new TemplateData();
	    	
	    	orderMoneySum.setValue("100元");
	    	data.put("orderMoneySum", orderMoneySum);
	    	
	    	TemplateData orderProductName = new TemplateData();
	    	
	    	orderProductName.setValue("哈哈哈哈");
	    	data.put("orderProductName", orderProductName);
	    	
	    	temp.setData(data);
	    	
	    	WeiXinUtils.send_template_message(Constants.WEIXINPUBLIC_APPID, Constants.WEIXINPUBLIC_APPSECRET, temp);
	    	
	    	/*AccessToken accessToken = WeiXinUtils.getAccessToken(Constants.WEIXINPUBLIC_APPID, Constants.WEIXINPUBLIC_APPSECRET);
	    	String token = accessToken.getToken();
	    	System.out.println(token);*/
		}

	    //进行转码

	    public static String urlEnodeUTF8(String str){

	        String result = str;

	        try {

	            result = URLEncoder.encode(str,"UTF-8");

	        } catch (Exception e) {

	            e.printStackTrace();

	        }

	        return result;


	}
}
