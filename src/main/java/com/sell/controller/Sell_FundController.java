package com.sell.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xmlpull.v1.XmlPullParserException;

import com.alibaba.fastjson.JSONException;
import com.qhtr.common.Json;
import com.qhtr.model.SellerAccount;
import com.qhtr.service.FundFlowService;
import com.qhtr.service.PayService;
import com.qhtr.service.SellerAccountService;

@Controller
@RequestMapping("/sell_fund")
public class Sell_FundController {
	@Resource
	public FundFlowService fundFlowService;
	@Resource
	public PayService payService;
	@Resource
	public SellerAccountService sellerAccountService;
	
	
	/**
	 * 资金流水信息
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFundInfo")
	public Json getFundInfo(Json j,@RequestParam int storeId){
		List<Map<String,Object>> list = fundFlowService.selectMoneysByStoreId(storeId);
		j.setData(list);
		return j;
	}
	
	/**
	 *  提现(
	 * @param j
	 * @param storeId
	 * @param money
	 * @param type   1.支付宝  2微信 3银行卡
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws JSONException 
	 * @throws KeyStoreException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnrecoverableKeyException 
	 * @throws KeyManagementException 
	 * @throws XmlPullParserException 
	 */
	@ResponseBody
	@RequestMapping("/withdrawApply")
	public Json withdrawApply(Json j,@RequestParam int storeId,@RequestParam int money,@RequestParam int type,HttpServletRequest request,HttpServletResponse response) throws JSONException, JDOMException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, XmlPullParserException{
	//	自动提现到支付宝和微信。。。有问题。    暂时改为提现申请，手动转账到银行卡
	  if(type == 1){
		  	// 提现到支付宝
		   
			SellerAccount sa = sellerAccountService.getByStoreId(storeId);
			if(StringUtils.isBlank(sa.getAlipayName())){
				j.setCode(0);
				j.setMessage("没有可以使用的支付宝账户!");
				return j;
			}
			int result = payService.updateAlipyToSeller(money,storeId,sa.getAlipayName());
			if(result == 0){
				j.setCode(0);
				j.setMessage("支付宝提现失败!");
				return j;
			}
			
		}else if(type == 2){
			//提现到微信  ，暂时不可用
			/*SellerAccount sa = sellerAccountService.getByStoreId(storeId);
			if(StringUtils.isBlank(sa.getOpenId())){
				j.setCode(0);
				j.setMessage("没有可以使用的微信账户!");
				return j;
			}
			int result = payService.updateWeixinEnterprisePayment(money, storeId, sa.getOpenId(),request,response);
			if(result == 0){
				j.setCode(0);
				j.setMessage("微信提现失败!");
				return j;
			}*/
			j.setCode(0);
			j.setMessage("提现到微信失败!");
			return j;
		}else if(type == 3){
			//提现到银行卡
			
			
			
			
			
			
			
		}else{
			j.setCode(0);
			j.setMessage("提现方式错误!");
		}
		j.setMessage("提现成功!");
		return j;
	}
}
