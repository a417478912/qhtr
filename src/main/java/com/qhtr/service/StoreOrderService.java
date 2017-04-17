package com.qhtr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.app.dto.StoreOrderDto;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
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
	List<Map<String, Object>> selectMapByConditions(StoreOrder so);
	
	/**
	 * 用户查询订单
	 * @param userId
	 * @param status
	 * @return
	 */
	List<StoreOrderDto> getOrdersByUser(int userId, int status);
	 /**
	  * 立刻购买
	  * @param skuId
	  * @param num
	  * @param distribution_type
	  * @return
	  */
	StoreOrderDto1 addtoBuy(int skuId, int num, int distributionType,int userId,int addressId,HttpServletRequest request);
	/**
	 * 立刻购买 ，确认订单
	 * @param submitOrderParam
	 * @param request
	 * @return
	 */
	String addOrder(String userRemark, HttpServletRequest request);
	
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

}
