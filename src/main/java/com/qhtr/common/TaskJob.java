package com.qhtr.common;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.StoreOrderService;

@Component()
public class TaskJob {
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public StoreOrderService storeOrderService;
	/**
     *  统计商家部分发货的变成已发货
	 *//*
	@Scheduled(cron = "0 0/10 * * * ?")
	public void changeToSendOutTask(){
		storeOrderService.changeToSendOutTask();
		System.out.println("商家部分发货的变成已发货:--------------");
	}*/
	
	/**
	 * 取消未付款订单(30分钟有效)
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void start1(){
		storeOrderService.updateCancleUnPayOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("定时任务执行-->取消未付款订单:++++++++++++++" + sdf.format(new Date()));
	}
}
