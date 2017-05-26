package com.qhtr.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qhtr.common.Constants;
import com.qhtr.dao.WithdrawMapper;
import com.qhtr.model.FundFlow;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.SellerAccount;
import com.qhtr.model.StoreOrder;
import com.qhtr.model.Withdraw;
import com.qhtr.service.FundFlowService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.PayService;
import com.qhtr.service.SellerAccountService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.GenerationUtils;
import com.qhtr.utils.MD5Utils;
import com.qhtr.utils.alipay.config.AlipayConfig;
import com.qhtr.utils.alipay.util.AlipayCore;
import com.qhtr.utils.alipay.util.AlipayNotify;
import com.qhtr.utils.weixinPay.PrepayIdRequestHandler;
import com.qhtr.utils.weixinPay.util.CollectionUtil;
import com.qhtr.utils.weixinPay.util.HttpUtils;
import com.qhtr.utils.weixinPay.util.MD5Util;
import com.qhtr.utils.weixinPay.util.PayUtil;
import com.qhtr.utils.weixinPay.util.TenpayUtil;
import com.qhtr.utils.weixinPay.util.WebUtil;
import com.qhtr.utils.weixinPay.util.XMLUtil;

import io.netty.handler.codec.http.HttpRequest;

@Service
public class PayServiceImpl implements PayService {
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public PayOrderService payOrderService;
	@Resource
	public FundFlowService fundFlowService;
	@Resource
	public WithdrawMapper withdrawMapper;
	@Resource
	public SellerAccountService sellerAccountService;
 
	@Override
	public void updateAliPayResult(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, NumberFormatException, AlipayApiException {
		System.out.println("+++++++++++++++++++++++++++++++++             " + "异步接口回调" + "                 ++++++++++++++++++++++");
		OutputStream out = response.getOutputStream();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		System.out.println("参数+++++++++++++++++++++++"+params);
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("订单号++++++++++++++++++" + out_trade_no);

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

		// 订单金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("订单金额++++++++++++++++++" + total_amount);
		
		// 实收金额
		String receipt_amount = new String(request.getParameter("receipt_amount").getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(" 实收金额++++++++++++++++++" + receipt_amount);
		
		// 用户在交易中支付的金额
		String buyer_pay_amount = new String(request.getParameter("buyer_pay_amount").getBytes("ISO-8859-1"), "UTF-8");

		// 异步通知ID
		String notify_id = request.getParameter("notify_id");

		// sign
		String sign = request.getParameter("sign");
		System.out.println("sign++++++++++++++++++++++++++"+sign);
		
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (notify_id != "" && notify_id != null) {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
			System.out.println("判断已经有notify_id  +++++++++++++++++" + notify_id);
			System.out.println("AlipayNotify.verifyResponse(notify_id)  +++++++++++++++++" + AlipayNotify.verifyResponse(notify_id));
			if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				System.out.println("确定是支付宝的通知  +++++++++++++++++");
				/*//过滤空值、sign与sign_type参数
				Map<String, String> sParaNew = AlipayCore.paraFilter(params);
				System.out.println("过滤过空参数之后的的参数+++++++++++++"+sParaNew);
			        //获取待签名字符串
			    String preSignStr = AlipayCore.createLinkString(sParaNew);
			    System.out.println("带签名的字符串++++++++++++++"+preSignStr);
			    System.out.println("签名00++++++++++++++++++"+AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.input_charset));
				System.out.println("签名11++++++++++++++++++"+AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.input_charset,"RSA2"));
				System.out.println("签名22++++++++++++++++++"+AlipaySignature.rsaCheck(preSignStr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset, "RSA2"));
				System.out.println("签名33++++++++++++++++++"+AlipaySignature.rsaCheckContent(preSignStr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset));
				System.out.println("签名44++++++++++++++++++"+AlipaySignature.rsaCheckV2(sParaNew,AlipayConfig.alipay_public_key, AlipayConfig.input_charset,"RSA2"));
				if (AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.input_charset))// 使用支付宝公钥验签
				{	
					System.out.println("签名正确  +++++++++++++++++");*/
					
					System.out.println("trade_status +++++++++++++订单状态 ：++++"+trade_status);
					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (trade_status.equals("TRADE_FINISHED")) {
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
						// 请务必判断请求时的out_trade_no、total_fee、seller_id与通知时获取的out_trade_no、total_fee、seller_id为一致的

					} else if (trade_status.equals("TRADE_SUCCESS")) {
						System.out.println("回调状态正确!");
						// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
						// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
						// 3、校验通知中的seller_id（或者seller_email) 
						// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
						// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
						PayOrder poTem = new PayOrder();
						poTem.setOrderCode(out_trade_no);
						List<PayOrder> poList = payOrderService.selectByConditions(poTem);
						if (!poList.isEmpty()) {
							PayOrder po = poList.get(0);
							System.out.println("订单id+++++++++++++++++" + po.getId());
							float poPrice = (float)po.getTotalPrice();
							float poPriceF = poPrice / 100;
							if (poPrice != 0 && poPriceF == Float.parseFloat(total_amount)) {
								System.out.println("金额相等++++++++++++++++++++");
								po.setStatus(20);
								Date payTime = new Date();
								po.setPaymentTime(payTime);
								payOrderService.update(po);
								
								StoreOrder soTem = new StoreOrder();
								soTem.setPayOrderCode(po.getOrderCode());
								List<StoreOrder> soList = storeOrderService.selectByConditions(soTem);
								if(!soList.isEmpty()){
									StoreOrder so = soList.get(0);
									if(so.getDistributionType() == 1){
										so.setStatus(20);
									}else if(so.getDistributionType() == 2){
										so.setStatus(21);
									}
									so.setPaymentTime(payTime);
									storeOrderService.updateByCondition(so);
									
									GoodsOrder goTem = new GoodsOrder();
									goTem.setStoreOrderCode(so.getOrderCode());
									List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
									for (GoodsOrder go : goList) {
										if(so.getDistributionType() == 1){
											go.setStatus(20);
										}else if(so.getDistributionType() == 2){
											go.setStatus(21);
										}
										
										goodsOrderService.updateGoodsOrder(go);
									}
									System.out.println("storeOrder  状态改变成功+++++++++++++");
									fundFlowService.insertByUser(so.getUserId(), 11, -so.getTotalPrice(), "购买商品");
									fundFlowService.insertByStore(so.getStoreId(), 21, so.getTotalPrice(), "卖家卖出商品");
								}
								
								out.write("success".getBytes("UTF-8"));// 请不要修改或删除
							}
						}
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					// 调试打印log
					// AlipayCore.logResult("notify_url success!","notify_url");
				/*} else// 验证签名失败
				{
					out.write("sign fail".getBytes("UTF-8"));
				}*/
			} else// 验证是否来自支付宝的通知失败
			{
				out.write("response fail".getBytes("UTF-8"));
			}
		} else {
			out.write("no notify message".getBytes("UTF-8"));
		}
	}
	
	
	/**
	 * 微信支付异步回调
	 * @throws IOException 
	 */
	@Override
	public void updateWeixinPayResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        System.out.println("result++++++++++++++++++"+result);
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            e.printStackTrace();
        }

        // 此处调用订单查询接口验证是否交易成功
        System.out.println("resultMap+++++++++++++++++"+map);
        Map<String,String> wxpayResult = reqOrderQueryResult(map);
        System.out.println("wxpayResult+++++++++++++++++++" + wxpayResult);
        boolean isSucc = wxpayResult.get("success").equals("true");
        System.out.println("isSucc+++++++++++++++++++" + isSucc);
        
        // 支付成功，商户处理后同步返回给微信参数
        PrintWriter writer = response.getWriter();
        if (!isSucc) {
            // 支付失败， 记录流水失败
            System.out.println("===============支付失败==============");
        } else {
        	PayOrder po = payOrderService.selectByOrderCode(wxpayResult.get("out_trade_no"));
        	po.setStatus(20);
			Date payTime = new Date();
			po.setPaymentTime(payTime);
			payOrderService.update(po);
			
			StoreOrder soTem = new StoreOrder();
			soTem.setPayOrderCode(po.getOrderCode());
			List<StoreOrder> soList = storeOrderService.selectByConditions(soTem);
			if(!soList.isEmpty()){
				StoreOrder so = soList.get(0);
				if(so.getDistributionType() == 1){
					so.setStatus(20);
				}else if(so.getDistributionType() == 2){
					so.setStatus(21);
				}
				so.setPaymentTime(payTime);
				storeOrderService.updateByCondition(so);
				
				GoodsOrder goTem = new GoodsOrder();
				goTem.setStoreOrderCode(so.getOrderCode());
				List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
				for (GoodsOrder go : goList) {
					if(so.getDistributionType() == 1){
						go.setStatus(20);
					}else if(so.getDistributionType() == 2){
						go.setStatus(21);
					}
					goodsOrderService.updateGoodsOrder(go);
				}
				System.out.println("storeOrder  状态改变成功+++++++++++++");
				fundFlowService.insertByUser(so.getUserId(), 11, -so.getTotalPrice(), "购买商品");
				fundFlowService.insertByStore(so.getStoreId(), 21, so.getTotalPrice(), "卖家卖出商品");
			}
            System.out.println("===============付款成功，业务处理完毕==============");
            
            // 通知微信已经收到消息，不要再给我发消息了，否则微信会8连击调用本接口
            String noticeStr = setXML("SUCCESS", "");
            writer.write(noticeStr);
            writer.flush();
        }
        
        String noticeStr = setXML("FAIL", "");
        writer.write(noticeStr);
        writer.flush();
    }

    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
	}
    
    /**
     * 查询微信通知的结果
     * @param map
     * @return
     */
    public Map<String,String> reqOrderQueryResult(Map<String, String> map) {
    	Map<String,String> orderQuery = new HashMap<String,String>();
        orderQuery.put("appid",map.get("appid"));
        orderQuery.put("mch_id",map.get("mch_id"));
        orderQuery.put("transaction_id",map.get("transaction_id"));
        orderQuery.put("out_trade_no",map.get("out_trade_no"));
        orderQuery.put("nonce_str",map.get("nonce_str"));
        orderQuery.put("attach",map.get("attach"));
        orderQuery.put("return_code",map.get("return_code"));
        orderQuery.put("result_code",map.get("result_code"));
        
   /*     //此处需要密钥PartnerKey，此处直接写死，自己的业务需要从持久化中获取此密钥，否则会报签名错误
        orderQuery.put("partnerKey",Constants.WEIXINPAY_PARTNERKEY);*/
        
        //此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
        if (orderQuery.get("return_code") != null && orderQuery.get("return_code").equalsIgnoreCase("SUCCESS")) {
            if (orderQuery.get("result_code") != null && orderQuery.get("result_code").equalsIgnoreCase("SUCCESS")) {
            	System.out.println("微信判断返回结果成功!!!+++++++++++++++++++");
                // 查询订单（交易流水的实际金额），判断微信收到的钱和订单中的钱是否等额
                PayOrder po = payOrderService.selectByOrderCode(orderQuery.get("out_trade_no"));
                String total_fee = map.get("total_fee");
                Integer db_fee = po.getTotalPrice();
                if (Integer.parseInt(total_fee) == db_fee) {
                	System.out.println("微信支付金额一致!!!+++++++++++++++++++");
                	orderQuery.put("success", "true");
                    return orderQuery;
                }
            }
        }
        orderQuery.put("success", "false");
        return orderQuery;
    }


	@SuppressWarnings("finally")
	@Override
	public int updateWeixinEnterprisePayment(int money,int storeId,String openid,HttpServletRequest request, HttpServletResponse response) throws JSONException, JDOMException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, XmlPullParserException {
		/**
		 * 生成微信的提现订单
		 */
		Withdraw withdraw = new Withdraw();
		withdraw.setCreateTime(new Date());
		
		String orderCode = GenerationUtils.getGenerationCode("WI", storeId+"");
		withdraw.setOrderCode(orderCode);
		withdraw.setPayType(2);
		withdraw.setStatus(1);
		withdraw.setStoreId(storeId);
		withdraw.setTotalPrice(money);
		
		//企业付款
		String baseUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
		
		//证书使用
		//指定读取证书格式为  PKCS12
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		
		//读取本机证书
        FileInputStream instream = new FileInputStream(new File("C:/apiclient_cert.p12"));///app/apiclient_cert.p12
        try {
        	//指定商户密码（商户id)
            keyStore.load(instream, "1430950202".toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "1430950202".toCharArray())
                .build();
        // Allow TLSv1 protocol only
        //指定TLS版本
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        
        //随机数
        String currTime = TenpayUtil.getCurrTime(); 
		 //8位日期  
        String strTime = currTime.substring(8, currTime.length());  
       //四位随机数  
        String strRandom = TenpayUtil.buildRandom(4) + "";
       //10位序列号,可以自行调整。  
		String nonce_str = strTime + strRandom; //随机字符串，不长于32位。推荐随机数生成算法
		
		
        
        //Map参数
		SortedMap<String,String> map = new TreeMap<String,String>();
        map.put("mch_appid",Constants.WEIXINPUBLIC_APPID);
        map.put("mchid", Constants.WEIXINPAY_PARTNER);
        map.put("nonce_str", nonce_str);
        map.put("partner_trade_no", orderCode);
        map.put("openid", openid);
        map.put("check_name", "NO_CHECK");
        map.put("amount", money+"");
        map.put("desc", "商家提现");
        map.put("spbill_create_ip", "218.28.136.174");
        System.out.println("++++++++++++++++++++++++"+MD5Utils.getMd5ByMap(map));
        
        
        Map<String, String> restmap = null;
			Map<String, String> parm = new HashMap<String, String>();
			parm.put("mch_appid", Constants.WEIXINPUBLIC_APPID); //公众账号appid
			parm.put("mchid", Constants.WEIXINPAY_PARTNER); //商户号
			parm.put("nonce_str", nonce_str); //随机字符串
			parm.put("partner_trade_no", orderCode); //商户订单号
			parm.put("openid", openid); //用户openid	
			parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
			//parm.put("re_user_name", "安迪"); //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
			parm.put("amount", "100"); //转账金额
			parm.put("desc", "测试转账到个人"); //企业付款描述信息
			parm.put("spbill_create_ip", "218.28.136.174"); //Ip地址
			System.out.println("++++++++++++++++++++++++"+PayUtil.getSign(parm, Constants.WEIXINPUBLIC_APIKEY));
			parm.put("sign", PayUtil.getSign(parm, Constants.WEIXINPUBLIC_APIKEY));
			
			map.put("sign", PayUtil.getSign(parm, Constants.WEIXINPUBLIC_APIKEY));
	        
	        String xmlString = XMLUtil.xmlFormat(parm, false);
			String restxml = HttpUtils.posts(baseUrl,XMLUtil.xmlFormat(parm, false));
			restmap = XMLUtil.xmlParse(restxml);

		if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
			System.out.println("转账成功：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			Map<String, String> transferMap = new HashMap<>();
			transferMap.put("partner_trade_no", restmap.get("partner_trade_no"));//商户转账订单号
			transferMap.put("payment_no", restmap.get("payment_no")); //微信订单号
			transferMap.put("payment_time", restmap.get("payment_time")); //微信支付成功时间
		}else {
			if (CollectionUtil.isNotEmpty(restmap)) {
				System.out.println("转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
		}
        
        
        
        
       ///请求
        HttpPost httpPost = new HttpPost(baseUrl);
        StringEntity reqEntity = new StringEntity(xmlString);
        httpPost.setEntity(reqEntity);
        CloseableHttpResponse responseResult = httpclient.execute(httpPost);
        try {
            HttpEntity entity = responseResult.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(responseResult.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
                String text;
                while ((text = bufferedReader.readLine()) != null) {
                    System.out.println(text);
                }
               
            }
            EntityUtils.consume(entity);
        } finally {
        	withdrawMapper.insert(withdraw);
        	responseResult.close();
        	httpclient.close();
        	return 1;
        }
		
		/*// PrepayIdRequestHandler方式
		PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//获取prepayid的请求类 
        //对以下字段进行签名
        prepayReqHandler.setParameter("mch_appid", Constants.WEIXINPUBLIC_APPID);    //微信分配的公众账号ID（企业号corpid即为此appId）
        prepayReqHandler.setParameter("mchid", Constants.WEIXINPAY_PARTNER);      //微信支付分配的商户号
        
        prepayReqHandler.setParameter("nonce_str", nonce_str);  //随机字符串，不长于32位
        
		
        prepayReqHandler.setParameter("partner_trade_no", orderCode);    //商户订单号，需保持唯一性	(只能是字母或者数字，不能包含有符号)
        prepayReqHandler.setParameter("openid", openid);      //商户appid下，某用户的openid
        prepayReqHandler.setParameter("check_name","NO_CHECK");  //NO_CHECK：不校验真实姓名    FORCE_CHECK：强校验真实姓名
      //map.put("re_user_name", value); //收款用户真实姓名。 		如果check_name设置为FORCE_CHECK，则必填用户真实姓名
        prepayReqHandler.setParameter("amount", money+"");    //企业付款金额，单位为分
        prepayReqHandler.setParameter("desc", "商家提现");//企业付款操作说明信息。必填。
        prepayReqHandler.setParameter("spbill_create_ip", "218.28.136.174");    //调用接口的机器Ip地址  //218.28.136.174  、、、request.getRemoteAddr()
        prepayReqHandler.setGateUrl(baseUrl);
        
        String sign = prepayReqHandler.createMd5Sign();
        prepayReqHandler.setParameter("sign", sign);
        //获取result  
        String result = prepayReqHandler.sendRequest();*/
	}


	@Override
	public int updateAlipyToSeller(int money, int storeId, String alipayName) {
		return 0;
	}


	@Override
	public int updateWithdrawApplyByAli(int money, int storeId, String alipayName) {
		//账户减去金额
		SellerAccount sa = sellerAccountService.getByStoreId(storeId);
		if(money > sa.getAccountMoney()){
			return -1;
		}
		sa.setAccountMoney(sa.getAccountMoney() - money);
		sellerAccountService.update(sa);
		
		//增加资金流水
		FundFlow ff = new FundFlow();
		fundFlowService.insertByStore(storeId, 31, -money, "卖家提现");
				
		//添加提现申请
		Withdraw wd = new Withdraw();
		wd.setCreateTime(new Date());
		wd.setPayType(1);
		wd.setStatus(1);
		wd.setStoreId(storeId);
		wd.setTotalPrice(money);
		withdrawMapper.insert(wd);
				
		return 1;
	}
}
