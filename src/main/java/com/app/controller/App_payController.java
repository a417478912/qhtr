package com.app.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qhtr.service.PayService;
import com.qhtr.utils.alipay.config.AlipayConfig;
import com.qhtr.utils.alipay.sign.Base64;
import com.qhtr.utils.alipay.util.AlipayCore;

@Controller
@RequestMapping("/app_pay")
public class App_payController {
	@Resource
	public PayService payService;
	
	@RequestMapping(value="/alipayResult")
	public void alipayResult(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException, NumberFormatException, AlipayApiException{
		System.out.println("++++++++++++++++++++++++++++++++++进入支付回调+++++++++++++++++++++++++++++++++++++++++++++");
		payService.updateAliPayResult(request, response);
	}
	
	@RequestMapping(value="/weixinPayResult")
	public void weixinPayResult(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("++++++++++++++++++++++++++++++++++进入支付回调+++++++++++++++++++++++++++++++++++++++++++++");
		payService.updateWeixinPayResult(request, response);
	}
	
	/*public static void main(String[] args) throws AlipayApiException {
		Map<String, String> params = new HashMap<String, String>();
		String s = "gmt_create=2017-05-08 16:13:55,charset=utf-8,seller_email=xie@7htr.com,subject=商品的标题";
		String s2 = "buyer_id=2088802071395852,body=商品描述,invoice_amount=0.01,notify_id=54ad304ff9cad2abf5c0c1c8c780e96mk6";
		String s1 = "notify_type=trade_status_sync,trade_status=TRADE_SUCCESS,receipt_amount=0.01,sign_type=RSA2,buyer_pay_amount=0.01,app_id=2017011305063049,seller_id=2088521548721245,notify_time=2017-05-08 16:13:56,gmt_payment=2017-05-08 16:13:55,version=1.0,out_trade_no=PO00008020170508161346,total_amount=0.01,trade_no=2017050821001004850221203717,auth_app_id=2017011305063049,buyer_logon_id=178***@qq.com,point_amount=0.00";
		String[] ss= s.split(",");
		for (String string : ss) {
			String[]  sss = string.split("=");
			params.put(sss[0], sss[1]);
		}
		String sign = "DEtZj5+mMAWC8rSXCUjEdLdR0WZOLUZtCfHj8lq8IpRCIBpeiXOoPuxoWJf6i3cvvxtBBiLVfD7T5he/iOmpkLLjPbZbvthCLb38L6IlZ+y3Ru8eMLWngVqKIQTLeTn0LlDasARSp1iaTNp9PvJVhG8z8cC1N2GuwCNY/l3P0cpI6ok0QWlbjC0EXknDO1YSL+rIFwPH60Gj7bDeph7BxkXreXfOGwkws6dK9r42e4RUdtWqWCu/xQp13g0hq1iksl6TCLuCL0CPuRY6SStqYgdI1WX+apLeZeXx5duP6adM0U1PD4lie/305NOjnEgL9ovDIwdww0Ok290QhoUBig==";
		params.put("sign", sign);
		String[] ss2= s2.split(",");
		for (String string2 : ss2) {
			String[]  sss2 = string2.split("=");
			params.put(sss2[0], sss2[1]);
		}
		params.put("fund_bill_list","[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]");
		String[] ss1= s1.split(",");
		for (String string1 : ss1) {
			String[]  sss1 = string1.split("=");
			params.put(sss1[0], sss1[1]);
		}
		System.out.println(params);
		String old = "{gmt_create=2017-05-08 16:13:55, charset=utf-8, seller_email=xie@7htr.com, subject=商品的标题, sign=DEtZj5+mMAWC8rSXCUjEdLdR0WZOLUZtCfHj8lq8IpRCIBpeiXOoPuxoWJf6i3cvvxtBBiLVfD7T5he/iOmpkLLjPbZbvthCLb38L6IlZ+y3Ru8eMLWngVqKIQTLeTn0LlDasARSp1iaTNp9PvJVhG8z8cC1N2GuwCNY/l3P0cpI6ok0QWlbjC0EXknDO1YSL+rIFwPH60Gj7bDeph7BxkXreXfOGwkws6dK9r42e4RUdtWqWCu/xQp13g0hq1iksl6TCLuCL0CPuRY6SStqYgdI1WX+apLeZeXx5duP6adM0U1PD4lie/305NOjnEgL9ovDIwdww0Ok290QhoUBig==, buyer_id=2088802071395852, body=商品描述, invoice_amount=0.01, notify_id=54ad304ff9cad2abf5c0c1c8c780e96mk6, fund_bill_list=[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}], notify_type=trade_status_sync, trade_status=TRADE_SUCCESS, receipt_amount=0.01, sign_type=RSA2, buyer_pay_amount=0.01, app_id=2017011305063049, seller_id=2088521548721245, notify_time=2017-05-08 16:13:56, gmt_payment=2017-05-08 16:13:55, version=1.0, out_trade_no=PO00008020170508161346, total_amount=0.01, trade_no=2017050821001004850221203717, auth_app_id=2017011305063049, buyer_logon_id=178***@qq.com, point_amount=0.00}";
		System.out.println(old.equals(params.toString()));
		String content = AlipaySignature.getSignCheckContentV1(params);
	//	byte[] decode = Base64.decode("DEtZj5+mMAWC8rSXCUjEdLdR0WZOLUZtCfHj8lq8IpRCIBpeiXOoPuxoWJf6i3cvvxtBBiLVfD7T5he/iOmpkLLjPbZbvthCLb38L6IlZ+y3Ru8eMLWngVqKIQTLeTn0LlDasARSp1iaTNp9PvJVhG8z8cC1N2GuwCNY/l3P0cpI6ok0QWlbjC0EXknDO1YSL+rIFwPH60Gj7bDeph7BxkXreXfOGwkws6dK9r42e4RUdtWqWCu/xQp13g0hq1iksl6TCLuCL0CPuRY6SStqYgdI1WX+apLeZeXx5duP6adM0U1PD4lie/305NOjnEgL9ovDIwdww0Ok290QhoUBig==");
		System.out.println("签名00++++++++++++++++++"+AlipaySignature.rsa256CheckContent(content, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset));
//		System.out.println("签名11++++++++++++++++++"+AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.input_charset));
		System.out.println("签名22++++++++++++++++++"+AlipaySignature.rsaCheck(preSignStr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset, "RSA2"));
		System.out.println("签名33++++++++++++++++++"+AlipaySignature.rsaCheckContent(preSignStr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset));
		System.out.println("签名44++++++++++++++++++"+AlipaySignature.rsaCheckV2(sParaNew,AlipayConfig.alipay_public_key, AlipayConfig.input_charset,"RSA2"));
	}*/
}
