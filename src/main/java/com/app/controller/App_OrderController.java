package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.BuyCartDto;
import com.qhtr.common.Json;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.StoreOrderService;

@Controller
@RequestMapping("/app_order")
public class App_OrderController {
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public PayOrderService payOrderService;
	@Resource
	public StoreOrderService storeOrderService;

	/**
	 * 直接下单
	 * 
	 * @param j
	 * @param goodsOrder
	 * @return 返回storeOrder 的 Code
	 */
	@ResponseBody
	@RequestMapping(value = "/addGoodsOrder")
	public Json addGoodsOrder(Json j, GoodsOrder goodsOrder) {
		String soCode = storeOrderService.insertByGoodsOrder(goodsOrder);
		if (StringUtils.isNotBlank(soCode)) {
			j.setData(soCode);
			j.setMessage("下单成功!");
		} else {
			j.setSuccess(false);
			j.setMessage("下单失败!");
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
	public Json surePay(Json j, @RequestParam String orderCode, @RequestParam(defaultValue = "1") int type,
			@RequestParam int userId) {
		String PayCode = payOrderService.addOrder(orderCode, type, userId);
		if (StringUtils.isNotBlank(PayCode)) {
			j.setData(PayCode);
			j.setMessage("成功!");
		} else {
			j.setSuccess(false);
			j.setMessage("失败!");
		}
		return j;
	}

	/**
	 * 购物车去下单
	 * 
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
	public Json selectFromBuyCartIds(Json j, @RequestParam int userId, @RequestParam int[] ids) {
		List<BuyCartDto> buyCarts = goodsOrderService.selectFromBuyCartIds(userId, ids);
		j.setData(buyCarts);
		return j;
	}

	/**
	 *  购物车结算
	 */
	@ResponseBody
	@RequestMapping(value = "/addGoodsOrders")
	public Json addGoodsOrders(Json j, @RequestParam int userId, @RequestParam int[] ids, String[] userRemark,
			int distributionType, @RequestParam int addressId) {
		String PayCode = goodsOrderService.addGoodsOrders(userId, ids, userRemark, distributionType, addressId);
		if (StringUtils.isNotBlank(PayCode)) {
			PayOrder poTem = new PayOrder();
			poTem.setOrderCode(PayCode);
			List<PayOrder> poList = payOrderService.selectByConditions(poTem);
			j.setData(poList.get(0).getId());
			j.setMessage("成功!");
		} else {
			j.setSuccess(false);
			j.setMessage("失败!");
		}
		return j;
	}
}
