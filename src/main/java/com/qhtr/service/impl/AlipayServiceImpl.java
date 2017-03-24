package com.qhtr.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.qhtr.model.GoodsOrder;
import com.qhtr.service.AlipayService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.utils.alipay.util.AlipayNotify;

@Service
public class AlipayServiceImpl implements AlipayService {
	@Resource
	public GoodsOrderService goodsOrderService;

	@Override
	public void payResult(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		OutputStream out = response.getOutputStream();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号	
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		//订单金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
		//实收金额
		String receipt_amount = new String(request.getParameter("receipt_amount").getBytes("ISO-8859-1"),"UTF-8");
		//用户在交易中支付的金额
		String buyer_pay_amount = new String(request.getParameter("buyer_pay_amount").getBytes("ISO-8859-1"),"UTF-8");
		
		//异步通知ID
		String notify_id=request.getParameter("notify_id");
		
		//sign
		String sign=request.getParameter("sign");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		if(notify_id!=""&&notify_id!=null){////判断接受的post通知中有无notify_id，如果有则是异步通知。
			if(AlipayNotify.verifyResponse(notify_id).equals("true"))//判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				if(AlipayNotify.getSignVeryfy(params, sign))//使用支付宝公钥验签
				{
					//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if(trade_status.equals("TRADE_FINISHED")){
						//判断该笔订单是否在商户网站中已经做过处理
							//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
							//如果有做过处理，不执行商户的业务程序
						//注意：
						//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
						//请务必判断请求时的out_trade_no、total_fee、seller_id与通知时获取的out_trade_no、total_fee、seller_id为一致的
						
					} else if (trade_status.equals("TRADE_SUCCESS")){
						GoodsOrder goTem = new GoodsOrder();
						goTem.setOrderCode(out_trade_no);
						List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
						/*if(!goList.isEmpty()){
							GoodsOrder go = goList.get(0);
							int goPrice = go.getTotalPrice();
							float goPriceF = goPrice/100;
							if(goPrice != 0 && goPriceF == Float.parseFloat(total_amount)){
								 go.setStatus(2);
								 go.setPaymentTime(new Date());
								 out.write("success".getBytes("UTF-8"));//请不要修改或删除
							}
						}*/
						//判断该笔订单是否在商户网站中已经做过处理
							//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
							//如果有做过处理，不执行商户的业务程序
						//注意：
						//付款完成后，支付宝系统发送该交易状态通知
						//请务必判断请求时的out_trade_no、total_fee、seller_id与通知时获取的out_trade_no、total_fee、seller_id为一致的
					}
					//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					//调试打印log
					//AlipayCore.logResult("notify_url success!","notify_url");
				}
				else//验证签名失败
				{
					out.write("sign fail".getBytes("UTF-8"));
				}
			}
			else//验证是否来自支付宝的通知失败
			{
				out.write("response fail".getBytes("UTF-8"));
			}
		}
		else{
			out.write("no notify message".getBytes("UTF-8"));
		}
	}

}
