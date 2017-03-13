package com.qhtr.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qhtr.model.Admin;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	 private static final String[] IGNORE_URI = {"/admin/loginUI.do","/admin/login.do","app_"};
	 
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        boolean flag = false;
	        String url = request.getRequestURL().toString();
	        System.out.println(">>>: " + url);
	        for (String s : IGNORE_URI) {
	            if (url.contains(s)) {
	                flag = true;
	                break;
	            }
	        }
	        if (!flag) {
 	            Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_KEY);
	            if (admin != null) return true;
	            
	            //不符合条件的，跳转到登录界面  
		        request.getRequestDispatcher("/WEB-INF/jsp/admin/login.jsp").forward(request, response);
		        System.out.println("++++页面跳转");
	        }
	        return flag;
	    }
	    
	    /** 
	     * Handler执行完成之后调用这个方法 
	     */  
	    public void afterCompletion(HttpServletRequest request,  
	            HttpServletResponse response, Object handler, Exception exc)  
	            throws Exception {  
	          
	    }  
	  
	    /** 
	     * Handler执行之后，ModelAndView返回之前调用这个方法 
	     */  
	    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
	            Object handler, ModelAndView modelAndView) throws Exception {  
	    }
}
