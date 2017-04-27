package com.sell.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.app.dto.StoreDto;
import com.qhtr.common.Json;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.ExpressService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UUpaotuiService;

@Controller
@RequestMapping("/sell_order")
public class OrderController {
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
	 * 订单列表
	 */
	@ResponseBody
	@RequestMapping("/getOrderList")
	public Json getOrderList(Json j,@RequestParam int storeId,@RequestParam int status,@RequestParam(defaultValue = "1") int page){
		//全部订单
		if(status == 0){
			StoreOrder so = new StoreOrder();
			so.setStoreId(storeId);
			List<Map<String,Object>> list = storeOrderService.selectMapByConditions(so,page);
			j.setData(list);
			return j;
		}
		//代付款订单
		if(status == 10){
			StoreOrder so = new StoreOrder();
			so.setStoreId(storeId);
			so.setStatus(status);
			List<Map<String,Object>> list = storeOrderService.selectMapByConditions(so,page);
			j.setData(list);
			return j;
		}
		if (status == 20 || status == 21 || status == 30 || status == 40 || status == 50 || status == 100 || status == 110) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("storeId", storeId);
			map.put("status", status);
			map.put("page", page);
			List<Map<String, Object>> list = goodsOrderService.selectMapByConditions(map);
			j.setData(list);
			return j;
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
		if(orderId == null && orderCode == null){
			j.setCode(0);
			j.setMessage("参数错误");
			return j;
		}
		if(orderId != null){
			StoreOrderDetailsDto dto = storeOrderService.selectStoreOrderDetailsById(orderId);
			j.setData(dto);
			return j;
		}
		StoreOrder so = storeOrderService.selectByOrderCode(orderCode);
		StoreOrderDetailsDto dto = storeOrderService.selectStoreOrderDetailsById(so.getId());
		j.setData(dto);
		return j;
	}
	
	/**
	 * 增加快递信息
	 */
	@ResponseBody
	@RequestMapping("/addExpress")
	public Json addExpress(Json j,@RequestParam String orderId,@RequestParam String expressName,@RequestParam String expressCode){
		int result = expressService.add(orderId,expressName,expressCode);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
	
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
	public Json addExpressOrder(Json j,@RequestParam int orderId,@RequestParam int storeId,int addressId,String note){
		//先计算订单价格
		String need_paymoney = "";
		String price_token = "";
		String total_money = "";
		String expressPrice = uUpaotuiService.getExpressOrderPrice(addressId,storeId);
		String returnCode = JSONObject.parseObject(expressPrice).getString("return_code");
		if(returnCode != null && returnCode.equals("ok")){
			need_paymoney = JSONObject.parseObject(expressPrice).getString("need_paymoney");
			price_token = JSONObject.parseObject(expressPrice).getString("price_token");
			total_money = JSONObject.parseObject(expressPrice).getString("total_money");
		}else{
			j.setCode(0);
			j.setMessage("参数不匹配!");
			return j;
		}
		
		
		StoreOrder so = storeOrderService.selectById(orderId);
		if(so.getAddressId() != null && so.getStoreId() != null && so.getAddressId() == addressId && so.getStoreId() == storeId){
			StoreDto store = storeService.getStoreById(so.getStoreId());
			String result = uUpaotuiService.addOrder(price_token, total_money, need_paymoney, so.getReceivingName(), so.getReceivingPhone(), note, store.getPhone());
			String returnCode1 = JSONObject.parseObject(result).getString("return_code");
			if(returnCode1 != null && returnCode1.equals("ok")){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("expressOrder", JSONObject.parseObject(result).getString("ordercode"));
				j.setData(map);
			}
			return j;
		}else{
			j.setCode(0);
			j.setMessage("参数错误!");
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
	@RequestMapping(value = "/cancelExpressOrder")
	public Json cancelExpressOrder(Json j,@RequestParam String order_code,@RequestParam String reason){
		String result = uUpaotuiService.cancelOrder(order_code, reason);
		String returnCode = JSONObject.parseObject(result).getString("return_code");
		if(returnCode != null && returnCode.equals("ok")){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("expressOrder", JSONObject.parseObject(result).getString("ordercode"));
			j.setData(map);
			j.setMessage("快递订单取消成功!");
		}
		return j;
	}
	
	/**
	 * 查询各种订单数量
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderCounts")
	public Json getOrderCounts(Json j,@RequestParam int storeId){
		Map<String,Integer> map = new HashMap<String,Integer>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("storeId", storeId);
		map1.put("status", 10);
		int result1 = storeOrderService.selectCountByConditions(map1);
		map.put("dfk", result1);
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("storeId", storeId);
		map2.put("status", 20);
		int result2 = goodsOrderService.selectCountByConditions(map2);
		map.put("dfh", result2);
		
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("storeId", storeId);
		map3.put("status", 30);
		int result3 = goodsOrderService.selectCountByConditions(map3);
		map.put("dqh", result3);
		
		map.put("tkz", 0);
		
		Map<String,Object> map5 = new HashMap<String,Object>();
		map5.put("storeId", storeId);
		map5.put("status", 40);
		int result5 = goodsOrderService.selectCountByConditions(map5);
		map.put("ywc", result5);
		
		
		Map<String,Object> map6 = new HashMap<String,Object>();
		map6.put("storeId", storeId);
		map6.put("status", 110);
		int result6 = goodsOrderService.selectCountByConditions(map6);
		map.put("tk", result6);
		
		j.setData(map);
		return j;
	}
}
