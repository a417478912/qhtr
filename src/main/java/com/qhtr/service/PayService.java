package com.qhtr.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;

public interface PayService {
	public void aliPayResult(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException, NumberFormatException, AlipayApiException;

	public void weixinPayResult(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
