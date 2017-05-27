package com.qhtr.utils;

import cn.jpush.api.push.model.PushPayload;

public class JPushUtils {
	 public static PushPayload buildPushObject_all_all_alert(String alert) {
	        return PushPayload.alertAll(alert);
	    }
}
