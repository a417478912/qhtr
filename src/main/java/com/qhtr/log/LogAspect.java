package com.qhtr.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import com.qhtr.model.SystemLog;
import com.qhtr.service.SystemLogService;
import com.qhtr.utils.ContextUtil;

public class LogAspect {
	@Resource  
    private SystemLogService systemLogService;  
      
    private Log logger = LogFactory.getLog(LogAspect.class);  
  
    public Object doSystemLog(ProceedingJoinPoint point) throws Throwable {  
  
        String methodName = point.getSignature().getName();  
  
        // 目标方法不为空  
        if (StringUtils.isNotEmpty(methodName)) {  
            // set与get方法除外  
            if (!(methodName.startsWith("set") || methodName.startsWith("get"))) {  
  
                Class targetClass = point.getTarget().getClass();  
                Method method = targetClass.getMethod(methodName);  
  
                if (method != null) {  
  
                    boolean hasAnnotation = method.isAnnotationPresent(MyLogAnnotation.class);  
  
                    if (hasAnnotation) {  
                    	MyLogAnnotation annotation = method.getAnnotation(MyLogAnnotation.class);  
                          
                        String methodDescp = annotation.description();  
                        if (logger.isDebugEnabled()) {  
                            logger.debug("Action method:" + method.getName() + " Description:" + methodDescp);  
                        }  
                        //取到当前的操作用户  
                        /*AppUser appUser=ContextUtil.getCurrentUser();  
                        if(appUser!=null){  
                            try{  
                                SystemLog sysLog=new SystemLog();  
                                  
                                sysLog.setCreatetime(new Date());  
                                sysLog.setUserId(appUser.getUserId());  
                                sysLog.setUsername(appUser.getFullname());  
                                sysLog.setExeOperation(methodDescp);  
                                  
                                systemLogService.save(sysLog);  
                            }catch(Exception ex){  
                                logger.error(ex.getMessage());  
                            }  
                        }  */
                          
                    }  
                }  
  
            }  
        }  
        return point.proceed();  
    }  
  
} 