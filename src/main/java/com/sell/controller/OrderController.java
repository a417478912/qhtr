package com.sell.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qhtr.common.Json;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.ExpressService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.StoreOrderService;
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
		if(status == 10 || status == 20){
			StoreOrder so = new StoreOrder();
			so.setStoreId(storeId);
			so.setStatus(status);
			List<Map<String,Object>> list = storeOrderService.selectMapByConditions(so,page);
			j.setData(list);
			return j;
		}
		if (status == 21 || status == 30 || status == 40 || status == 50 || status == 100 || status == 110) {
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
	 * 计算邮费
	 */
	@ResponseBody
	@RequestMapping(value = "/getExpressOrderPrice")
	public Json getExpressOrderPrice(Json j,@RequestParam int addressId,@RequestParam int storeId){
		String expressPrice = uUpaotuiService.getExpressOrderPrice(addressId,storeId);
		String returnCode = JSONObject.parseObject(expressPrice).getString("return_code");
		if(returnCode != null && returnCode.equals("ok")){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("need_paymoney", JSONObject.parseObject(expressPrice).getString("need_paymoney"));
			map.put("price_token", JSONObject.parseObject(expressPrice).getString("price_token"));
			map.put("total_money", JSONObject.parseObject(expressPrice).getString("total_money"));
			j.setData(map);
		}else{
			j.setCode(0);
			j.setMessage("查询订单价格失败!");
		}
		return j;
	}
	
	public Json addExpressOrder(Json j,@RequestParam String price_token,@RequestParam String order_price,
			@RequestParam String balance_paymoney,@RequestParam String receiver,@RequestParam String receiver_phone,@RequestParam String pubUserMobile,String note){
		return j;
	}
}
