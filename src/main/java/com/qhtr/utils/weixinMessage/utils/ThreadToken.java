package com.qhtr.utils.weixinMessage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qhtr.common.Constants;
import com.qhtr.utils.weixinMessage.model.AccessToken;

/**
 * @author Harry
 * @Description 保证access_token长久有效
 * @date  2017年7月10日
 */
public class ThreadToken implements Runnable{

	private static Logger log = LoggerFactory.getLogger(ThreadToken.class);
	 // 第三方用户唯一凭证    
    public static String appid = Constants.WEIXINPUBLIC_APPID;    
    // 第三方用户唯一凭证密钥    
    public static String appsecret = Constants.WEIXINPUBLIC_APPSECRET; 
    
    public static AccessToken accessToken = null;
    
	@Override
	public void run() {
		while (true) {
			try {
				accessToken = WeiXinUtils.getAccessToken(appid, appsecret);
				if (null != accessToken) {
					log.info("获取access_token成功，有效时长{}秒  token:{}", accessToken.getExpiresIn(), accessToken.getToken());
					// 休眠7000秒
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					log.error("{}", e1);
				}
				log.error("{}", e);
			}
		}
	}

}
