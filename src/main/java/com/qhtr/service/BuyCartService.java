package com.qhtr.service;

import java.util.List;

import com.app.dto.BuyCartDto;
import com.qhtr.model.BuyCart;

public interface BuyCartService {
	BuyCart selectById(int id);
	/**
	 * 通过用户id查询购物车
	 * @param userId
	 * @return
	 */
	List<BuyCartDto> selectCartsByUserId(int userId);
	
	/**
	 * 删除购物车
	 * @param cartId
	 * @return
	 */
	int deleteById(int cartId);
	/**
	 * 批量删除购物车数据
	 * @param ids
	 * @return
	 */
	int deleteByIds(int[] ids);
	/**
	 * 增加
	 * @param cart
	 * @return
	 */
	int addById(BuyCart cart);
	/**
	 * 修改
	 * @param cart
	 * @return
	 */
	int updateById(BuyCart cart);
	/**
	 * 批量修改
	 * @param carts
	 * @return
	 */
	int updateBatch(List<BuyCart> carts);
	/**
	 * 通过购物车ids，查找购物车信息
	 * @param ids
	 * @return
	 */
	List<BuyCartDto> selectByIds(int[] ids);
}
