package com.qhtr.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AlipayService {
	public void payResult(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException;
}
