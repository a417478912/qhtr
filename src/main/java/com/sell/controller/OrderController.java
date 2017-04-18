package com.sell.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.ExpressService;
import com.qhtr.service.StoreOrderService;

@Controller
@RequestMapping("/sell_order")
public class OrderController {
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public ExpressService expressService;
	
	/**
	 * 订单列表
	 */
	@ResponseBody
	@RequestMapping("/getOrderList")
	public Json getOrderList(Json j,@RequestParam int storeId,@RequestParam int status,@RequestParam(defaultValue = "1") int page){
		StoreOrder so = new StoreOrder();
		so.setStoreId(storeId);
		so.setStatus(status);
		List<Map<String,Object>> list = storeOrderService.selectMapByConditions(so,page);
		j.setData(list);
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
	 * 查询订单详情
	 */
	@ResponseBody
	@RequestMapping("/getStoreOrderDetails")
	public Json getStoreOrderDetails(Json j,@RequestParam int orderId){
		StoreOrderDetailsDto dto = storeOrderService.selectStoreOrderDetailsById(orderId);
		j.setData(dto);
		return j;
	}
	
	/**
	 * 增加快递信息
	 */
	@ResponseBody
	@RequestMapping("/addExpress")
	public Json addExpress(Json j,@RequestParam int orderId,@RequestParam String name,@RequestParam String code){
		int result = expressService.add(orderId,name,code);
		if(result == 1){
			j.setMessage("成功!");
		}else{
			j.setCode(0);
			j.setMessage("失败!");
		}
		return j;
	}
}
