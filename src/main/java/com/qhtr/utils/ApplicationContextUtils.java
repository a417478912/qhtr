package com.qhtr.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextUtils {
	public static ApplicationContext ac;
	public static ApplicationContext getContext(){
		if(ac == null){
			ac = new FileSystemXmlApplicationContext("classpath:spring.xml","classpath:spring-mybatis.xml");
		}
		return ac;
	}
}
