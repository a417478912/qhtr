package com.qhtr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.qhtr.model.Store;

public interface StoreService {
	/**
	 * 通过商铺id查找商铺
	 * @param storeId
	 * @return
	 */
	Store getStoreByIdOrPhone(Store store);
	/**
	 * 通过卖家id  查找商铺
	 * @param sellerId
	 * @return
	 */
	Store getStoreBySellerId(Integer sellerId);
	/**
	 * 根据经纬度，返回商铺信息
	 * @param location 经纬度
	 * @param distance 距离/默认1000米
	 * @return
	 */
	List<Map<String,Object>> getStoresByDistance(String longitude,String latitude,String distance);
	/**
	 * 通过分类查找店铺
	 * @param type
	 * @param page 第几页
	 * @param num  数量
	 * @return
	 */
	List<Store> getStoresByType(int type,int page,int num);
	/**
	 * 热店查找
	 * @return
	 */
	List<Store> getHotStores(int page,int num);
	/**
	 * 通过内容搜索
	 * @param searchContent
	 * @param page
	 * @param num
	 * @return
	 */
	List<Store> selectStoreBySearch(String searchContent, int page, int num);
	
	public int addRegister(String phone, String password);
			
	int updateByConditions(Store store);
	
	public int login(String phone, String password,HttpServletResponse response);
}
