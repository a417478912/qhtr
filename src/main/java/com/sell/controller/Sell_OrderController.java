package com.sell.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.app.dto.StoreDto_App;
import com.qhtr.common.Json;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.Express;
import com.qhtr.model.QueryCount;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.ExpressService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.PickUpCodeService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UUpaotuiService;

/**
 * @author Harry
 * @Description 商家版 : 订单
 * @date  2017年7月11日
 */
@Controller
@RequestMapping("/sell_order")
public class Sell_OrderController {
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public ExpressService expressService;
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public UUpaotuiService uUpaotuiService;
	@Resource
	public StoreService storeService;
	
	/**
	 * 订单列表-----------待修改
	 */
	@ResponseBody
	@RequestMapping("/getOrderList")
	public Json getOrderList(Json j,@RequestParam int storeId,@RequestParam int status,@RequestParam(defaultValue = "1") int page){
		 // 10 : 待付款      20 : 已付款   21 : 已付款带自取    30 : 已发货      40 : 已收货   50 : 已评价
	    // 100 : 申请售后   110 : 已退款/已退货
		//全部订单
		StoreOrder so = new StoreOrder();
		Map<String, Object> condition = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		
		condition.put("storeId", storeId);
		so.setStoreId(storeId);
		
		if (status == 0) {
			so.setStatus(null);
			condition.put("status", null);
		}else{
			so.setStatus(status);
			condition.put("status", status);
		}
		
		// 查询订单列表
		List<Map<String,Object>> list = storeOrderService.selectMapByConditions(so,page);
		map.put("orderList", list);
		// 查询该状态的订单数量
		int count = storeOrderService.selectCountByConditions(condition);
		map.put("count", count);
		j.setData(map);
		return j;
	}
	
	/**
	 * 发货
	 * @param j
	 * @param storeOrderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendGoods")
	public Json sendGoods(Json j,@RequestParam int storeOrderId){
		int result = storeOrderService.sendOutGoods(storeOrderId);
		if(result == 1){
			j.setMessage("发货成功!");
		}else{
			j.setCode(0);
			j.setMessage("发货失败!");
		}
		return j;
	}
	
	/**
	 * 卖家备注
	 * @param j
	 * @param orderId
	 * @param remark
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setSellerRemark")
	public Json setSellerRemark(Json j,@RequestParam int orderId,@RequestParam String remark){
		int result = storeOrderService.setSellerRemark(orderId,remark);
		if(result != 1){
			j.setCode(0);
			j.setMessage("保存失败!");
		}
		return j;
	}
	
	/**
	 * 查询订单详情  通过id或者编号
	 */
	@ResponseBody
	@RequestMapping("/getStoreOrderDetails")
	public Json getStoreOrderDetails(Json j,Integer orderId,String orderCode){
		
		StoreOrderDetailsDto dto = new StoreOrderDetailsDto();
		if(orderId == null && orderCode == null){
			j.setCode(0);
			j.setMessage("参数错误");
			return j;
		}
		if(orderId != null){
			dto = storeOrderService.selectStoreOrderDetailsById(orderId);
			
		}else{
			StoreOrder so = storeOrderService.selectByOrderCode(orderCode);
			dto = storeOrderService.selectStoreOrderDetailsById(so.getId());
		}
		// 配送
		Express express = expressService.getByStoreOrderId(dto.getStordeOrder().getId());
		
		if (express != null) {
			
			String result = uUpaotuiService.GetOrderDetail(express.getCode());
			dto.setExpressDetail(result);
		}
		j.setData(dto);
		return j;
	}
	
	/**
	 * 增加快递信息
	 *//*
	@ResponseBody
	@RequestMapping("/addExpress")
	public Json addExpress(Json j,@RequestParam int orderId,@RequestParam String expressName,@RequestParam String expressCode){
		int result = expressService.add(orderId,expressName,expressCode);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}*/
	
	/**
	 * 增加快递订单
	 * @param j
	 * @param orderId
	 * @param storeId
	 * @param addressId
	 * @param price_token
	 * @param order_price
	 * @param balance_paymoney
	 * @param note
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addExpressOrder")
	public Json addExpressOrder(Json j,@RequestParam int orderId,@RequestParam(defaultValue="") String note){
		Express theExpress = expressService.getByStoreOrderId(orderId);
		if(theExpress != null){
			j.setCode(0);
			j.setMessage("重复增加快递订单!");
			return j;
		}
		//先计算订单价格
		String need_paymoney = "";
		String price_token = "";
		String total_money = "";
		StoreOrder soTem = storeOrderService.selectById(orderId);
		
		String expressPrice = uUpaotuiService.getExpressOrderPrice(soTem.getAddressId(),soTem.getStoreId());
		String returnCode = JSONObject.parseObject(expressPrice).getString("return_code");
		if(returnCode != null && returnCode.equals("ok")){
			need_paymoney = JSONObject.parseObject(expressPrice).getString("need_paymoney");
			price_token = JSONObject.parseObject(expressPrice).getString("price_token");
			total_money = JSONObject.parseObject(expressPrice).getString("total_money");
		}else{
			j.setCode(0);
			j.setMessage(JSONObject.parseObject(expressPrice).getString("return_msg"));
			return j;
		}
		
		
		StoreOrder so = storeOrderService.selectById(orderId);
			StoreDto_App store = storeService.getStoreById(so.getStoreId());
			String result = uUpaotuiService.addOrder(price_token, total_money, need_paymoney, so.getReceivingName(), so.getReceivingPhone(), note, store.getPhone());
			String returnCode1 = JSONObject.parseObject(result).getString("return_code");
			String ordercode = JSONObject.parseObject(result).getString("ordercode");
			if(returnCode1 != null && returnCode1.equals("ok")){
				expressService.add(orderId, "UU跑腿", ordercode);
				j.setMessage("下单成功!");
			}else{
				j.setCode(0);
				j.setMessage("下单失败!");
			}
			return j;
	}
	
	/**
	 * 取消快递订单
	 * @param j
	 * @param order_code
	 * @param reason
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelExpressOrder")	public Json cancelExpressOrder(Json j,@RequestParam int storeOrderId,@RequestParam String reason){
		
		Express express = expressService.getByStoreOrderId(storeOrderId);
		String result = uUpaotuiService.cancelOrder(express.getCode(), reason);
		String returnCode = JSONObject.parseObject(result).getString("return_code");
		if(returnCode != null && returnCode.equals("ok")){
			expressService.delete(express.getId());
			storeOrderService.cancelSendOutGoods(storeOrderId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("expressOrder", JSONObject.parseObject(result).getString("ordercode"));
			j.setData(map);
			j.setMessage("快递订单取消成功!");
		}
		return j;
	}
	
	/**
	 * 统计
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderCounts")
	public Json getOrderCounts(Json j,@RequestParam int storeId){
		
		// 10 : 待付款      20 : 已付款   21 : 已付款待自取    30 : 已发货      40 : 已收货   50 : 已评价
	    // 100 : 申请售后   110 : 已退款/已退货
		// 10 : 待付款
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		
		map1.put("storeId", storeId);
		map1.put("status", 10);
		int result1 = storeOrderService.selectCountByConditions(map1);
		map.put("dfk", result1);
		
		// 20  : 已付款,待发货
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("storeId", storeId);
		map2.put("status", 20);
		int result2 = storeOrderService.selectCountByConditions(map2);
		map.put("dfh", result2);
		
		// 21 : 已付款待自取
		Map<String,Object> map10 = new HashMap<String,Object>();
		map10.put("storeId", storeId);
		map10.put("status", 21);
		int result10 = storeOrderService.selectCountByConditions(map10);
		map.put("dzt", result10);
		
		// 30 : 已发货,待收货
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("storeId", storeId);
		map3.put("status", 30);
		int result3 = storeOrderService.selectCountByConditions(map3);
		map.put("dqh", result3);
		
		map.put("tkz", 0);
		
		Map<String,Object> map5 = new HashMap<String,Object>();
		map5.put("storeId", storeId);
		map5.put("status", 40);
		int result5 = storeOrderService.selectCountByConditions(map5);
		map.put("ywc", result5);
		
		
		Map<String,Object> map6 = new HashMap<String,Object>();
		map6.put("storeId", storeId);
		map6.put("status", 110);
		int result6 = storeOrderService.selectCountByConditions(map6);
		map.put("tk", result6);
		
		Calendar cale = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 当前时间
		String nowTime = sdf.format(new Date());
		
		// 当天时间开始
		cale = Calendar.getInstance();
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		String today = sdf.format(cale.getTime());
		
		// 当前月的第一天
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		String thisMonth = sdf.format(cale.getTime());
		
		// 当前周的第一天
		cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		String thisWeek = sdf.format(cale.getTime());
		
		// 昨天
		cale = Calendar.getInstance();
		cale.setTime(new Date());
		cale.add(Calendar.DAY_OF_MONTH, -1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		
		// 昨天开始时间
		String yesterdayStartTime = sdf.format(cale.getTime());
		cale = Calendar.getInstance();
		cale.setTime(new Date());
		cale.add(Calendar.DAY_OF_MONTH, -1);
		cale.set(Calendar.HOUR_OF_DAY, 24);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		
		// 昨天结束时间
		String yesterdayEndTime = sdf.format(cale.getTime());
		
		// 查询当天订单数量及收入
		QueryCount qc = null;
		qc = new QueryCount(today, nowTime, storeId);
		List<StoreOrder> thisDayList = storeOrderService.selectCountByTime(qc);
		int orderCount = thisDayList.size();
		long accountOrder = 0;
		
		for (StoreOrder storeOrder : thisDayList) {
			
			if (storeOrder.getTotalPrice()!=null) {
				
				Integer totalPrice = storeOrder.getTotalPrice();
				long totalAccount = totalPrice;
				accountOrder += totalAccount;
			}
		}
		
		qc = new QueryCount(yesterdayStartTime, yesterdayEndTime, storeId);
		
		List<StoreOrder> yesterdayList = storeOrderService.selectCountByTime(qc);
		long yesterdayAccountOrder = 0;
		for (StoreOrder storeOrder : yesterdayList) {
			
			if (storeOrder.getTotalPrice()!=null) {
				
				Integer totalPrice = storeOrder.getTotalPrice();
				long totalAccount = totalPrice;
				yesterdayAccountOrder += totalAccount;
			}
		}
		
		// 查询本周订单数量及收入
		qc = new QueryCount(thisWeek, nowTime, storeId);
		int thisWeekNum = 0;
		long thisWeekAccount = 0;
		List<StoreOrder> thisWeekList = storeOrderService.selectCountByTime(qc);
		if (!thisWeekList.isEmpty()) {
			
			thisWeekNum = thisWeekList.size();
			for (StoreOrder storeOrder : thisWeekList) {
				
				if (storeOrder.getTotalPrice()!=null) {
					thisWeekAccount += storeOrder.getTotalPrice();
				}
			}
		}
		// 查询本月订单数量及收入
		qc = new QueryCount(thisMonth, nowTime, storeId);
		int thisMonthNum = 0;
		long thisMonthAccount = 0;
		List<StoreOrder> thisMonthList = storeOrderService.selectCountByTime(qc);
		if (!thisMonthList.isEmpty()) {
			
			thisMonthNum = thisMonthList.size();
			for (StoreOrder storeOrder : thisMonthList) {
				if (storeOrder.getTotalPrice()!=null) {
					
					thisMonthAccount += storeOrder.getTotalPrice();
				}
			}
		} 
		// 查询所有订单数量及收入
		qc = new QueryCount("2017-06-01 00:00:00", nowTime, storeId);
		long allAccount = 0;
		List<StoreOrder> allList = storeOrderService.selectCountByTime(qc);
		if (!allList.isEmpty()) {
			
			for (StoreOrder storeOrder : allList) {
				if (storeOrder.getTotalPrice()!=null) {
					
					allAccount += storeOrder.getTotalPrice();
				}
			}
		}
		
		map.put("today_order_num", orderCount); //今日订单数量
		map.put("today_income", accountOrder);   //今日收入
		
		map.put("this_week_order_num", thisWeekNum); //本周订单数量
		map.put("this_week_income", thisWeekAccount); //本周总收入
		
		map.put("this_month_order_num", thisMonthNum);// 本月订单数量
		map.put("this_month_income", thisMonthAccount); //本月收入
		
		map.put("total_income", allAccount);   //总收入
		map.put("yesterday_income", yesterdayAccountOrder);// 昨日收入
		// map.put("goods_num",0);    //商品数
		//map.put("unRepay_num", 0);  //待回复留言数
		
		j.setData(map);
		
		return j;
	}
	
	@Resource
	private PickUpCodeService pickUpCodeService;
	
	/**
	 * 确认收货
	 * @param j
	 * @param storeOrderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sureReceive")// sell_order/sureReceive.do?storeOrderId=...
	public Json sureReceive(Json j,int storeOrderId){
		
		int result = storeOrderService.updateSureReceiveingGoods(storeOrderId);
		
		if (result == 1) {
			
			// 删除取货码
			int result1 = pickUpCodeService.deletePickUpCode(storeOrderId);
			if (result1 == 1) {
				
				j.setMessage("确认收货成功 !");
				return j;
			}else{
				
				j.setCode(0);
				j.setMessage("确认收货失败 !");
				return j;
			}
		}else{
			
			j.setCode(0);
			j.setMessage("确认收货失败 !");
			return j;
		}
	}
}
