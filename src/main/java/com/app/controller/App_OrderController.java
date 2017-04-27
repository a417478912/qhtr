package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.app.dto.StoreOrderDto;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.model.Address;
import com.qhtr.model.PayOrder;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.AddressService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.service.UUpaotuiService;

@Controller
@RequestMapping("/app_order")
public class App_OrderController {
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public PayOrderService payOrderService;
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public AddressService addressService;
	@Resource
	public UUpaotuiService uUpaotuiService;
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
	@RequestMapping(value = "/toBuy")
	public Json toBuy(Json j,String userRemark,@RequestParam int skuId,@RequestParam int num,@RequestParam int distributionType,@RequestParam int userId,@RequestParam Integer addressId){
		System.out.println("++++++++++++++++++++++++++++++++++提交订单+++++++++++++++++++++++++++++++++++++++++++++");
		StoreOrder result = storeOrderService.addtoBuy(skuId,num,distributionType,userId,addressId,userRemark);
		if(result != null){
			Map<String,String> map = new HashMap<String,String>();
			map.put("orderCode", result.getOrderCode());
			j.setData(map);
			j.setMessage("成功!");
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
	 */
	@ResponseBody
	@RequestMapping(value = "/surePay")
	public Json surePay(Json j, @RequestParam String orderCode, @RequestParam(defaultValue = "1") int type,@RequestParam int userId) {
		String PayCode = payOrderService.addOrder(orderCode, type, userId);
		if (StringUtils.isNotBlank(PayCode)) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("code", PayCode);
			j.setData(map);
			j.setMessage("成功!");
		} else {
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}

	/**
	 * 购物车结算
	 * http://localhost:8080/app_order/selectFromBuyCartIds.do?userId=1&ids=1,2,3,4,5&distributionType=1
	 * @param j
	 * @param userId
	 * @param ids
	 *            购物车id
	 * @param distribution_type
	 * @param addressId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectFromBuyCartIds")
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
	@RequestMapping(value = "/addOrders")
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
	
	@ResponseBody
	@RequestMapping(value = "/getOrdersByUser")
	public Json getOrdersByUser(Json j,@RequestParam int userId,@RequestParam int status){
		List<StoreOrderDto> dto = storeOrderService.getOrdersByUser(userId,status);
		j.setData(dto);
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
			j.setData(map);
		}else{
			j.setCode(0);
			j.setMessage("查询订单价格失败!");
		}
		return j;
	}
}
