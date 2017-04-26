package com.qhtr.service;

public interface UUpaotuiService {
	/**
	 * 计算邮费
	 * @param addressId
	 * @param storeId
	 * @return
	 */
	String getExpressOrderPrice(int addressId, int storeId);
	/**
	 * 下单
	 * @param price_token  金额令牌，计算订单价格接口返回的price_token
	 * @param order_price  订单金额，计算订单价格接口返回的total_money
	 * @param balance_paymoney 	实际余额支付金额计算订单价格接口返回的need_paymoney
	 * @param receiver	收件人
	 * @param receiver_phone 收件人电话
	 * @param note	订单备注 最长140个汉字
	 * @param pubUserMobile	发件人电话，（如果为空则是用户注册的手机号）
	 * @return
	 */
	String addOrder(String price_token,String order_price,String balance_paymoney,String receiver,String receiver_phone,String note,String pubUserMobile);
	/**
	 * 取消订单
	 * @param order_code  UU跑腿订单编号
	 * @param reason 取消原因
	 * @return
	 */
	String cancelOrder(String order_code,String reason);
	/**
	 * 获取订单详情   UU跑腿订单编号
	 * @return  
	 */
	String GetOrderDetail(String order_code);
}
