package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.qhtr.common.Constants;
import com.qhtr.dao.PayOrderMapper;
import com.qhtr.model.PayOrder;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.GenerationUtils;
import com.qhtr.utils.weixinPay.PackageRequestHandler;
import com.qhtr.utils.weixinPay.PrepayIdRequestHandler;
import com.qhtr.utils.weixinPay.RequestHandler;
import com.qhtr.utils.weixinPay.util.TenpayUtil;

@Service
public class PayOrderServiceImpl implements PayOrderService {
	@Resource
	public PayOrderMapper payOrderMapper;
	@Resource
	public StoreOrderService storeOrderService;

	@Override
	public String addOrder(String orderCode,int userId) {
		StoreOrder so = storeOrderService.selectByOrderCode(orderCode);
		PayOrder po = new PayOrder();
		po.setCreateTime(new Date());
		po.setOrderCode(GenerationUtils.getGenerationCode("PO", userId + ""));
		po.setStatus(10);
		po.setUserId(userId);
		po.setPayType(1);
		po.setTotalPrice(so.getResultPrice());
		int result = payOrderMapper.insert(po);
		if (result == 1) {
			so.setPayOrderCode(po.getOrderCode());
			storeOrderService.updateByCondition(so);
			return po.getOrderCode();
		} else {
			return null;
		}
		
	}
	
	@Override
	public String addOrder(String orderCode,int userId,HttpServletRequest request,HttpServletResponse response) {
		StoreOrder so = storeOrderService.selectByOrderCode(orderCode);
		PayOrder po = new PayOrder();
		po.setCreateTime(new Date());
		po.setOrderCode(GenerationUtils.getGenerationCode("PO", userId + ""));
		po.setStatus(10);
		po.setUserId(userId);
		po.setPayType(2);
		po.setTotalPrice(so.getResultPrice());
		String result = this.toWeixinPay(po,request,response);
		if (StringUtils.isBlank(result)) {
			return null;
		}else {
			payOrderMapper.insert(po);
			so.setPayOrderCode(po.getOrderCode());
			storeOrderService.updateByCondition(so);
			return po.getOrderCode();
		}
		
	}
	
	/**
	 * 微信支付 ，生成预订单
	 * @return
	 */
	public String toWeixinPay(PayOrder po,HttpServletRequest request,HttpServletResponse response) {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//URL地址：
		
		String appid = Constants.WEIXINPAY_APPID;  //微信开放平台审核通过的应用APPID
		String mch_id = Constants.WEIXINPAY_PARTNER; //微信支付分配的商户号
		
		String currTime = TenpayUtil.getCurrTime();  
        //8位日期  
        String strTime = currTime.substring(8, currTime.length());  
        //四位随机数  
        String strRandom = TenpayUtil.buildRandom(4) + "";
        //10位序列号,可以自行调整。  
		String nonce_str = strTime + strRandom; ; //随机字符串，不长于32位。推荐随机数生成算法
		
		String body = "小逛一下-商品购买";///商品描述交易字段格式根据不同的应用场景按照以下格式：aPP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
		String out_trade_no = po.getOrderCode();//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
		int total_fee = po.getTotalPrice();//订单总金额，单位为分，详见支付金额
		String spbill_create_ip = request.getRemoteAddr();//用户端实际ip
		String notify_url = Constants.WEIXINPAY_NOTIFY_URL;//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
		String trade_type = "APP";//支付类型
		
         
         PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//获取prepayid的请求类 
         //对以下字段进行签名
         prepayReqHandler.setParameter("appid", appid);    
         prepayReqHandler.setParameter("mch_id", mch_id);      
         prepayReqHandler.setParameter("nonce_str", nonce_str);    
         prepayReqHandler.setParameter("body", body);    
         prepayReqHandler.setParameter("out_trade_no", out_trade_no);      
         prepayReqHandler.setParameter("total_fee", total_fee + "");  
         prepayReqHandler.setParameter("spbill_create_ip", spbill_create_ip);   
         prepayReqHandler.setParameter("notify_url", notify_url);    
         prepayReqHandler.setParameter("trade_type", trade_type);
         prepayReqHandler.setGateUrl(url);
         
         String sign = prepayReqHandler.createSHA1Sign();
         prepayReqHandler.setParameter("sign", sign);
         //获取prepayId  
         String prepayid = prepayReqHandler.sendPrepay(); 
		System.out.println(prepayid);
		return prepayid;
	}
	
	@Override
	public List<PayOrder> selectByConditions(PayOrder poTem) {
		return payOrderMapper.selectByConditions(poTem);
	}

	@Override
	public int insert(PayOrder po) {
		return payOrderMapper.insert(po);
	}

	@Override
	public PayOrder selectByOrderCode(String code) {
		PayOrder po = new PayOrder();
		po.setOrderCode(code);
		List<PayOrder> list = payOrderMapper.selectByConditions(po);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public int updateByConditions(PayOrder payOrder) {
		return payOrderMapper.updateByPrimaryKeySelective(payOrder);
	}

	@Override
	public int update(PayOrder payOrder) {
		return payOrderMapper.updateByPrimaryKey(payOrder);
	}


}
