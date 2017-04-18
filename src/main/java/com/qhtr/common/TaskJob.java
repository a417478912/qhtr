package com.qhtr.common;

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
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void changeToSendOutTask(){
		storeOrderService.changeToSendOutTask();
		System.out.println("商家部分发货的变成已发货:--------------");
	}
	
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public void start1(){
		System.out.println("任务开启222222:++++" + new Date());
	}
}
