package com.qhtr.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.AlipayService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.alipay.util.AlipayNotify;

@Service
public class AlipayServiceImpl implements AlipayService {
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public PayOrderService payOrderService;

	@Override
	public void payResult(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
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

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

		// 订单金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
		// 实收金额
		String receipt_amount = new String(request.getParameter("receipt_amount").getBytes("ISO-8859-1"), "UTF-8");
		// 用户在交易中支付的金额
		String buyer_pay_amount = new String(request.getParameter("buyer_pay_amount").getBytes("ISO-8859-1"), "UTF-8");

		// 异步通知ID
		String notify_id = request.getParameter("notify_id");

		// sign
		String sign = request.getParameter("sign");
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (notify_id != "" && notify_id != null) {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
			if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				if (AlipayNotify.getSignVeryfy(params, sign))// 使用支付宝公钥验签
				{
					// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					if (trade_status.equals("TRADE_FINISHED")) {
						// 判断该笔订单是否在商户网站中已经做过处理
						// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						// 如果有做过处理，不执行商户的业务程序
						// 注意：
						// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
						// 请务必判断请求时的out_trade_no、total_fee、seller_id与通知时获取的out_trade_no、total_fee、seller_id为一致的

					} else if (trade_status.equals("TRADE_SUCCESS")) {
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
							int poPrice = po.getTotalPrice();
							float poPriceF = poPrice / 100;
							if (poPrice != 0 && poPriceF == Float.parseFloat(total_amount)) {
								po.setStatus(20);
								Date payTime = new Date();
								po.setPaymentTime(payTime);
								payOrderService.update(po);
								
								StoreOrder soTem = new StoreOrder();
								soTem.setPayOrderCode(po.getOrderCode());
								List<StoreOrder> soList = storeOrderService.selectByConditions(soTem);
								for (StoreOrder so : soList) {
									so.setStatus(20);
									so.setPaymentTime(payTime);
									storeOrderService.updateByCondition(so);
									
									GoodsOrder goTem = new GoodsOrder();
									goTem.setStoreOrderCode(so.getOrderCode());
									List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
									for (GoodsOrder go : goList) {
										go.setStatus(20);
										goodsOrderService.updateGoodsOrder(go);
									}
								}
								out.write("success".getBytes("UTF-8"));// 请不要修改或删除
							}
						}
					}
					// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					// 调试打印log
					// AlipayCore.logResult("notify_url success!","notify_url");
				} else// 验证签名失败
				{
					out.write("sign fail".getBytes("UTF-8"));
				}
			} else// 验证是否来自支付宝的通知失败
			{
				out.write("response fail".getBytes("UTF-8"));
			}
		} else {
			out.write("no notify message".getBytes("UTF-8"));
		}
	}
}
