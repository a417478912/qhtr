package com.qhtr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.app.dto.StoreOrderDto_App;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.StoreOrder;

public interface StoreOrderService {
	int insert(StoreOrder record);

	StoreOrder selectByOrderCode(String soCode);

	StoreOrder selectById(int storeOrderId);
	
	int updateByCondition(StoreOrder record);
	
	/**
	 * 条件查询
	 */
	List<StoreOrder> selectByConditions(StoreOrder storeOrder);
	List<Map<String, Object>> selectMapByConditions(StoreOrder so,int page);
	
	/**
	 * 用户查询订单列表
	 * @param userId
	 * @param status
	 * @return
	 */
	List<StoreOrderDto_App> getOrdersByUser(int userId, int status);
	 /**
	  * 立刻购买 ，确认订单
	  * @param skuId
	  * @param num
	  * @param distribution_type
	  * @return
	  */
	StoreOrder addtoBuy(int skuId, int num, int distributionType,int userId,int addressId,String userRemark);
	/**
	 * 从购物车结算
	 * @param userId
	 * @param ids
	 * @param distribution_type
	 * @return
	 */
	List<StoreOrderDto1> selectFromBuyCartIds(int userId, int[] ids,int distributionType,int addressId,HttpServletRequest request);
	
	/**
	 * 购物车确认订单
	 * @param userId
	 * @param ids
	 * @param userRemark
	 * @param distributionType
	 * @return
	 */
	String addOrders(Param1[] params,int userId,HttpServletRequest request);
	
	/**
	 * 查询数量
	 */
	int selectCountByConditions(Map<String,Object> map);
	/**
	 * 增加卖家备注
	 * @param orderId
	 * @param remark
	 * @return
	 */
	int setSellerRemark(int orderId, String remark);
	/**
	 * 
	 * @param orderId
	 * @return
	 */
	StoreOrderDetailsDto selectStoreOrderDetailsById(int orderId);
	
	/**
	 * 统计商家部分发货的变成已发货
	 */
	void changeToSendOutTask();
	/**
	 * 发货
	 * @param storeOrderId
	 * @return
	 */
	int sendOutGoods(int storeOrderId);
	
	/**
	 * 取消发货
	 * @param storeOrderId
	 * @return
	 */
	int cancelSendOutGoods(int storeOrderId);
	
	
	/**
	 * 取消未付款订单(30分钟 系统自动)
	 */
	void updateCancleUnPayOrder();
	/**
	 * 用户手动取消未付款订单
	 * @param storeOrderId
	 * @return
	 */
	int updateCancalUnpayOrder(int storeOrderId);
	/**
	 * 确认收货
	 * @param storeOrderId
	 * @return
	 */
	int updateSureReceiveingGoods(int storeOrderId);
}
