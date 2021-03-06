package com.qhtr.utils.weixinPay;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

import com.alibaba.fastjson.JSONException;
import com.qhtr.utils.weixinPay.client.TenpayHttpClient;
import com.qhtr.utils.weixinPay.util.ConstantUtil;
import com.qhtr.utils.weixinPay.util.MD5Util;
import com.qhtr.utils.weixinPay.util.Sha1Util;
import com.qhtr.utils.weixinPay.util.XMLUtil;

public class PrepayIdRequestHandler extends RequestHandler {

	public PrepayIdRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 创建签名SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createSHA1Sign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = Sha1Util.getSha1(params);
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "sha1 sb:" + params);
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "app sign:" + appsign);
		return appsign;
	}
	
	/**
	 * 创建签名MD5
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createMd5Sign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		sb.append("key="+this.getKey());
		//String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "sha1 sb:" + sb.toString());
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "app sign:" + appsign);
		return appsign;
	}

	// 提交预支付
	//xml 格式的参数
		public String sendPrepay() throws JSONException, JDOMException, IOException {
			String prepayid = "";
			StringBuffer sb = new StringBuffer("<xml>");
			Set es = super.getAllParameters().entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String v = (String) entry.getValue();
				if (null != v && !"".equals(v) && !"appkey".equals(k)) {
					sb.append("<" + k + ">" + v + "</" + k + ">");
				}
			}
			String params = sb.append("</xml>").toString();

			String requestUrl = super.getGateUrl();
			this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
					+ requestUrl);
			TenpayHttpClient httpClient = new TenpayHttpClient();
			httpClient.setReqContent(requestUrl);
			String resContent = "";
			this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
			if (httpClient.callHttpPost(requestUrl, params)) {
				resContent = httpClient.getResContent();
				Map xmlMap = XMLUtil.doXMLParse(resContent);
				if ("SUCCESS".equals(xmlMap.get("result_code"))){
					prepayid = xmlMap.get("prepay_id").toString();
				}
				this.setDebugInfo(this.getDebugInfo() + "\r\n" + "resContent:"
						+ resContent);
			}
			return prepayid;
		}
		
		// 提交请求  	//xml 格式的参数
			public String sendRequest() throws JSONException, JDOMException, IOException {
				String result = "";
				StringBuffer sb = new StringBuffer("<xml>");
				Set es = super.getAllParameters().entrySet();
				Iterator it = es.iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					String k = (String) entry.getKey();
					String v = (String) entry.getValue();
					if (null != v && !"".equals(v) && !"appkey".equals(k)) {
						sb.append("<" + k + ">" + v + "</" + k + ">");
					}
				}
				String params = sb.append("</xml>").toString();

				String requestUrl = super.getGateUrl();
				this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
						+ requestUrl);
				TenpayHttpClient httpClient = new TenpayHttpClient();
				//httpClient.setCertInfo(new File("C://apiclient_cert.p12"), "1430950202");
				//httpClient.setCaInfo(new File("C://apiclient_cert.p12"));
				httpClient.setReqContent(requestUrl);
				String resContent = "";
				this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
				if (httpClient.callHttpPost(requestUrl, params)) {
					resContent = httpClient.getResContent();
					Map xmlMap = XMLUtil.doXMLParse(resContent);
					if ("SUCCESS".equals(xmlMap.get("result_code"))){
						result = xmlMap.get("payment_no").toString();
					}
					this.setDebugInfo(this.getDebugInfo() + "\r\n" + "resContent:"
							+ resContent);
				}
				return result;
			}
	//json 格式的参数
	/*public String sendPrepay() throws JSONException {
		String prepayid = "";
		StringBuffer sb = new StringBuffer("{");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
				sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.substring(0, sb.lastIndexOf(","));
		params += "}";

		String requestUrl = super.getGateUrl();
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
				+ requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (2 == resContent.indexOf("prepayid")) {
				prepayid = JsonUtil.getJsonValue(resContent, "prepayid");
			}
			this.setDebugInfo(this.getDebugInfo() + "\r\n" + "resContent:"
					+ resContent);
		}
		return prepayid;
	}*/

	// 判断access_token是否失效
	public String sendAccessToken() {
		String accesstoken = "";
		StringBuffer sb = new StringBuffer("{");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
				sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.substring(0, sb.lastIndexOf(","));
		params += "}";

		String requestUrl = super.getGateUrl();
//		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
//				+ requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
//		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (2 == resContent.indexOf(ConstantUtil.ERRORCODE)) {
				accesstoken = resContent.substring(11, 16);//获取对应的errcode的值
			}
		}
		return accesstoken;
	}
}
