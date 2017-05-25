package com.qhtr.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.xmlpull.v1.XmlPullParserException;

import com.alibaba.fastjson.JSONException;
import com.alipay.api.AlipayApiException;

public interface PayService {
	public void updateAliPayResult(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException, NumberFormatException, AlipayApiException;

	public void updateWeixinPayResult(HttpServletRequest request, HttpServletResponse response) throws IOException;
	/**
	 * 微信企业付款(给卖家转账)
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
	public int updateWeixinEnterprisePayment(int money,int storeId,String openid,HttpServletRequest request, HttpServletResponse response) throws JSONException, JDOMException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException, XmlPullParserException;
	/**
	 * 支付宝给卖家转账(提现)
	 * @param money
	 * @param storeId
	 * @param alipayName
	 * @return
	 */
	public int updateAlipyToSeller(int money, int storeId, String alipayName);
	/**
	 * 支付宝提现申请
	 * @param money
	 * @param storeId
	 * @param alipayName
	 * @return
	 */
	public int updateWithdrawApplyByAli(int money, int storeId, String alipayName);
}
