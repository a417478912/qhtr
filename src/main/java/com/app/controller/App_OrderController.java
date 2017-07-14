package com.app.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.app.dto.StoreOrderDto_App;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.Address;
import com.qhtr.model.Express;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.Store;
import com.qhtr.model.StoreOrder;
import com.qhtr.model.StoreScore;
import com.qhtr.service.AddressService;
import com.qhtr.service.ExpressService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.service.StoreScoreService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UUpaotuiService;
import com.qhtr.utils.JPushUtils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

/**
 * 订单相关操作
 * @author wjx
 *
 * @date  2017年6月2日
 */
@Controller
@RequestMapping("/app_order")
public class App_OrderController {
	
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public StoreService storeService;
	@Resource
	public PayOrderService payOrderService;
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public AddressService addressService;
	@Resource
	public UUpaotuiService uUpaotuiService;
	@Resource
	public ExpressService expressService;
	
	
	/**
	 * 立刻购买 -->提交订单
	 * @param j
	 * @param request
	 * @param skuId
	 * @param num
	 * @param distributionType
	 * @param userId
	 * @param addressId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toBuy")// ,method=RequestMethod.POST
	public Json toBuy(Json j,String userRemark,@RequestParam int skuId,@RequestParam int num,@RequestParam int distributionType,@RequestParam int userId,@RequestParam Integer addressId){
		System.out.println("++++++++++++++++++++++++++++++++++提交订单+++++++++++++++++++++++++++++++++++++++++++++");
		StoreOrder result = storeOrderService.addtoBuy(skuId,num,distributionType,userId,addressId,userRemark);
		if(result != null){
			Map<String,String> map = new HashMap<String,String>();
			map.put("orderCode", result.getOrderCode());
			j.setData(map);
			j.setMessage("成功!");
			
			 // JPushUtils.sendPush(JPushUtils.sendMessageToAndroid(message),LoggerFactory.getLogger(getClass()));
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
	
	
	/**
	 * 确认支付
	 * 
	 * @param j
	 * @param orderId
	 *            商店订单id
	 * @param type
	 *            支付方式（1.支付宝 2.微信） 默认是1
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws JSONException 
	 */
	@ResponseBody
	@RequestMapping(value = "/surePay")
	public Json surePay(Json j,@RequestParam String orderBody,@RequestParam String orderCode, @RequestParam(defaultValue = "1") int type,@RequestParam int userId,HttpServletRequest request,HttpServletResponse response) throws JSONException, JDOMException, IOException {
		if(type == 1){
			//支付宝支付
			String PayCode = payOrderService.addOrder(orderCode, userId);
			if (StringUtils.isNotBlank(PayCode)) {
				Map<String,Object> map = new HashMap<String,Object>();
				PayOrder selectByOrderCode = payOrderService.selectByOrderCode(PayCode);
				map.put("orderId", selectByOrderCode.getId());
				map.put("code", PayCode);
				j.setData(map);
				j.setMessage("成功!");
				
			} else {
				j.setCode(0);
				j.setMessage("失败!");
			}
		}else if(type == 2){
			//微信支付
			Map<String,String> map = payOrderService.addOrder(orderBody,orderCode, userId,request,response);
			// PayOrder payOrder = payOrderService.selectByOrderCode(orderCode);
			if(map.isEmpty()){
				j.setCode(0);
				j.setMessage("错误");
			}else{
				// map.put("orderId", payOrder.getId() + "");
				j.setData(map);
			}
		}else{
			j.setCode(0);
			j.setMessage("支付方式错误!");
			return j;
		}
	
		return j;
	}

	/**
	 * 购物车结算
	 * http://localhost/app_order/selectFromBuyCartIds.do?userId=1&ids=1,2,3,4,5&distributionType=1
	 * @param j
	 * @param userId
	 * @param ids
	 *            购物车id
	 * @param distribution_type
	 * @param addressId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectFromBuyCartIds",method=RequestMethod.POST)
	public Json selectFromBuyCartIds(Json j, @RequestParam int userId, @RequestParam int[] ids,@RequestParam int distributionType,Integer addressId,HttpServletRequest request) {
		if(addressId == null || addressId== 0){
			List<Address> adds = addressService.selectAddressByUserId(userId);
			if(adds.isEmpty()){
				j.setCode(0);
				j.setMessage("没有默认地址!");
				return j;
			}else{
				
				addressId = adds.get(0).getId();
			}
		}
		List<StoreOrderDto1> dtoList = storeOrderService.selectFromBuyCartIds(userId, ids,distributionType,addressId,request);
		request.getSession().setAttribute(Constants.CART_IDS,ids);
		j.setData(dtoList);
		return j;
	}

	/**
	 * 购物车-->提交订单
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrders",method=RequestMethod.POST)
	public Json addOrders(Json j, @RequestBody Param1[] params,@RequestParam int userId,HttpServletRequest request) {
		
		String PayCode = storeOrderService.addOrders(params,userId,request);
		if (StringUtils.isNotBlank(PayCode)) {
			PayOrder poTem = new PayOrder();
			poTem.setOrderCode(PayCode);
			List<PayOrder> poList = payOrderService.selectByConditions(poTem);
			j.setData(poList.get(0).getId());
			j.setMessage("成功!");
		} else {
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
	
	/**
	 * 查询用户的订单
	 * @param j
	 * @param userId
	 * @param status
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrdersByUser")
	public Json getOrdersByUser(Json j,@RequestParam int userId,@RequestParam(defaultValue="0") int status,@RequestParam(defaultValue="1") int page){
		
		Map<String,Object> map = new HashMap<String,Object>();
		Page<?> startPage = PageHelper.startPage(page,10);
		List<StoreOrderDto_App> dto = storeOrderService.getOrdersByUser(userId,status);
		
		map.put("list", dto);
		map.put("totalNum", startPage.getTotal());
		map.put("totalPages", startPage.getPages());
		j.setData(map);
		
		return j;
	}
	
	/**
	 * 取消未付款订单
	 * @param j
	 * @param storeOrderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancalOrder")
	public Json cancalOrder(Json j,@RequestParam int storeOrderId){
		int result = storeOrderService.updateCancalUnpayOrder(storeOrderId);
		if(result == -1){
			j.setCode(0);
			j.setMessage("订单状态错误!");
		}else if(result == 1){
			j.setMessage("订单取消成功!");
		}
		return j;
	}
	
	/**
	 * 获取订单详情
	 * @param j
	 * @param storeOrderId
	 * @param orderCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderDetails")
	public Json getOrderDetails(Json j,@RequestParam Integer storeOrderId,@RequestParam String orderCode){
		
		StoreOrderDetailsDto dto = new StoreOrderDetailsDto();
		if (storeOrderId == null || storeOrderId == 0) {
			
			if (!"0".equals(orderCode)) {
				
				dto = storeOrderService.selectOrderDetailsByOrderCode(orderCode);
			}else{
				
				j.setCode(0);
				j.setMessage("参数有问题 !");
				return j;
			}
		}else{
			
			dto = storeOrderService.selectStoreOrderDetailsById(storeOrderId);
		}
		
		StoreOrder stordeOrder = dto.getStordeOrder();
		Integer storeId = stordeOrder.getStoreId();
		Store store = storeService.selectStoreById(storeId);
		String location = store.getLocation();
		dto.setStoreLocation(location);
		
		// 发货时间
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setStoreOrderCode(orderCode);
		List<GoodsOrder> goodsOrderList = goodsOrderService.selectByCondictions(goodsOrder);
		
		if (!goodsOrderList.isEmpty()) {
			
			Date shipmentsTime = goodsOrderList.get(0).getShipmentsTime();
			if (shipmentsTime != null) {
				
				dto.setShipmentTime(shipmentsTime);
			}
		}
		
		Express express = expressService.getByStoreOrderId(dto.getStordeOrder().getId());
		
		if (express != null) {
			String result = uUpaotuiService.GetOrderDetail(express.getCode());
			dto.setExpressDetail(result);
		}
		j.setData(dto);
		return j;
	}
	
	/**
	 * 确认收货
	 * @param j
	 * @param storeOrderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sureReceivingGoods",method=RequestMethod.POST)
	public Json sureReceivingGoods(Json j,@RequestParam int storeOrderId){
		int result = storeOrderService.updateSureReceiveingGoods(storeOrderId);
		if(result == -1){
			j.setCode(0);
			j.setMessage("订单状态错误!");
		}else if(result == 1){
			j.setMessage("收货成功!");
		}
		return j;
	}
	
	
	/**
	 * 计算邮费
	 */
	@ResponseBody
	@RequestMapping(value = "/getExpressOrderPrice",method=RequestMethod.POST)
	public Json getExpressOrderPrice(Json j,@RequestParam int addressId,@RequestParam int storeId){
		
		String expressPrice = uUpaotuiService.getExpressOrderPrice(addressId,storeId);
		String returnCode = JSONObject.parseObject(expressPrice).getString("return_code");
		if(returnCode != null && returnCode.equals("ok")){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("need_paymoney", new BigDecimal(JSONObject.parseObject(expressPrice).getString("need_paymoney")).multiply(new BigDecimal(100)));
			j.setData(map);
		}else{
			j.setCode(0);
			j.setMessage("查询订单价格失败!");
		}
		return j;
	}
	
	/**
	 * 删除已完成订单
	 */
	@ResponseBody
	@RequestMapping(value="/deleteOrder")
	public Json deleteOrder(Json j,Integer storeOrderId){
		
		StoreOrder storeOrder = storeOrderService.selectById(storeOrderId);
		// 当订单状态为 : 40 : 已收货   50 : 已评价    110 : 已退款/已退货
		Integer status = storeOrder.getStatus();
		if (status == 40 || status == 50 || status ==110) {
			
			int result = storeOrderService.deleteStoreOrder(storeOrderId);
			
		}else{
			j.setCode(0);
			j.setMessage("当前订单不能删除!");
		}
		return j;
	}
	
	/**
	 * 确认收货
	 * @param j
	 * @param storeOrderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/confirmReceipt")
	public Json confirmReceipt(Json j,Integer storeOrderId){
		int result = storeOrderService.updateSureReceiveingGoods(storeOrderId);
		if (result != 1) {
			j.setCode(0);
			j.setMessage("确认收货失败!");
		}else{
			j.setMessage("确认收货成功!");
		}
		return j;
	}
	
	@Resource
	private StoreScoreService storeScoreService;
	/**
	 * 确认评分
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sureScore")
	public Json sureScore(Json j,@RequestParam Integer storeId,@RequestParam Double score,@RequestParam Integer storeOrderId){
		
		StoreOrder storeOrder = storeOrderService.selectById(storeOrderId);
		if (storeOrder.getStatus() != 40) {
			j.setCode(0);
			j.setMessage("订单状态异常!");
			return j;
		}
		storeOrder.setStatus(50);
		storeOrderService.updateByCondition(storeOrder);
		
		String orderCode = storeOrder.getOrderCode();
		
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setStoreOrderId(storeOrderId);
		
		List<GoodsOrder> goodsOrderList = goodsOrderService.selectByCondictions(goodsOrder);
		
		for (GoodsOrder order : goodsOrderList) {
			if (order.getStatus() != 40) {
				
				j.setCode(0);
				j.setMessage("订单状态异常!");
				return j;
			}
			order.setStatus(50);
			goodsOrderService.updateGoodsOrder(order);
		}
		
		StoreScore ss = new StoreScore(null,storeId,score);
		
		int result = storeScoreService.addScore(ss);
		
		if (result == 1) {
			j.setMessage("评分成功!");
		}else{
			j.setMessage("评分失败!");
			j.setCode(0);
		}
		return j;
	}
	
}
