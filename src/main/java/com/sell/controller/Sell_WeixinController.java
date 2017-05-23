package com.sell.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.service.SellerAccountService;
import com.qhtr.utils.HttpUtils;

@Controller
@RequestMapping("/sell_weixin")
public class Sell_WeixinController {
	@Resource
	public SellerAccountService sellerAccountService;
	
	/**
	 * 验证服务器token
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/checkTokenSignature")
	public String checkTokenSignature(HttpServletResponse response,@RequestParam String signature,@RequestParam String timestamp,@RequestParam String nonce,@RequestParam String echostr) throws IOException{
		List<String> list=new ArrayList<String>();
		list.add(nonce);
		list.add(timestamp);
		list.add(Constants.WEIXINPUBLIC_TOKEN);

		Collections.sort(list);
		
		String sha1Str = DigestUtils.sha1Hex(list.get(0)+list.get(1)+list.get(2));
		
		if(sha1Str.equals(signature)){
			response.getWriter().write(echostr);
		}
		return null;
	}
	
	/**
	 * 获取授权的url地址
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAuthorizeUrl")
	public Json getAuthorizeUrl(Json j){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx00527dd179ff7578&redirect_uri=http%3A%2F%2Fwww.7htr.com%2Fqhtr%2Fsell_weixin%2Fresult.do&response_type=code&scope=snsapi_base#wechat_redirect";
		String sendGet = HttpUtils.sendGet(url, "");
		System.out.println(sendGet);
		Map<String,String> map = new HashMap<String,String>();
		map.put("url", url);
		j.setData(map);
		return j;
	}
	
	/**
	 * 授权后返回的页面
	 * @param request
	 * @param code
	 * @param state
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/result")
	public Json result(HttpServletRequest request,String code,String state){
		Json j = new Json();
		if(StringUtils.isBlank(code)){
			j.setCode(0);
			j.setMessage("参数错误!");
			return j;
		}
		
		System.out.println("code++++++++++++++++"+code);
		//获取code后，请求以下链接获取access_token：
		String atUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+Constants.WEIXINPUBLIC_APPID+"&secret="+Constants.WEIXINPUBLIC_APPSECRET+"&code="+code+"&grant_type=authorization_code";
		String result = HttpUtils.sendGet(atUrl, "");
			
		JSONObject jsonObject = JSONObject.parseObject(result);
		String openId = jsonObject.getString("openid");
		
		if(StringUtils.isNotBlank(openId)){
			Map<String,String> map = new HashMap<String,String>();
			map.put("openId", openId);
			j.setData(map);
		}else{
			j.setCode(0);
			j.setMessage("返回结果错误!");
		}
		return j;
	}
	
	/**
	 * 更新卖家的openId
	 * @param j
	 * @param storeId
	 * @param openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/setStoreOpenId")
	public Json setStoreOpenId(Json j,@RequestParam int storeId,@RequestParam String openId){
		int result = sellerAccountService.updateOpenId(storeId,openId);
		if(result == -1){
			j.setCode(0);
			j.setMessage("已经存在的openId");
		}else if(result == 0){
			j.setCode(0);
			j.setMessage("openId更新失败!");
		}else if(result == 1){
			j.setMessage("openId更新成功!");
		}
		return j;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*String a= "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx00527dd179ff7578&secret=8fcf56bc7380d74033468c9b7cc00bc5&code=051mYGFu0WK74d1Q38Iu0eSAFu0mYGFk&grant_type=authorization_code";
		String result = HttpUtils.sendGet(a, "");
		System.out.println(result);*/
	//	System.out.println(java.net.URLEncoder.encode("http://www.7htr.com/qhtr/sell_weixin/result.do","UTF-8"));
	}
}
