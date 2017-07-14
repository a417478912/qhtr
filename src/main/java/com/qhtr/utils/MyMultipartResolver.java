package com.qhtr.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyMultipartResolver extends CommonsMultipartResolver{
	 
	@Override
	public boolean isMultipart(HttpServletRequest request) {
		
		  if (request.getRequestURI().contains("/file/") ) {
	            return false;
	        } else {
	            return super.isMultipart(request);
	        }
	}
}