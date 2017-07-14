package com.qhtr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qhtr.utils.weixinMessage.utils.ThreadToken;
import com.qhtr.utils.weixinMessage.utils.WeiXinUtils;

/**
 * 初始化 Servlet,保持 access_token 长久有效
 * @author Harry
 * @date  2017年7月11日
 */
@SuppressWarnings("serial")
public class InitServlet extends HttpServlet{

	private static Logger log = LoggerFactory.getLogger(WeiXinUtils.class);
	
	@Override
	public void init() throws ServletException {
		
		log.info("weixin api appid:{}", ThreadToken.appid);    
        log.info("weixin api appsecret:{}", ThreadToken.appsecret);
        
        // 未配置appid、appsecret时给出提示 
        if ("".equals(ThreadToken.appid) || "".equals(ThreadToken.appsecret)) {    
            log.error("appid and appsecret configuration error, please check carefully.");    
        } else {    
            // 启动定时获取access_token的线程    
            new Thread(new ThreadToken()).start();  
        } 
	}
}
