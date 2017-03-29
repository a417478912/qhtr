package com.qhtr.utils;

import javax.servlet.http.HttpServletRequest;

import com.qhtr.log.LogUser;

public class ContextUtil {
	 public static LogUser getCurrentUser(HttpServletRequest request){
		/* SecurityContext securityContext = SecurityContextHolder.getContext();  
	        if (securityContext != null) {  
	            Authentication auth = securityContext.getAuthentication();  
	            if (auth != null) {  
	                Object principal = auth.getPrincipal();  
	                if (principal instanceof AppUser) {  
	                    return (AppUser) principal;  
	                }  
	            } else {  
	                logger.warn("WARN: securityContext cannot be lookuped using SecurityContextHolder.");  
	            }  
	        }  */
	        return null;  
	 }
}
