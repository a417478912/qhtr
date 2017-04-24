package com.qhtr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.qhtr.dto.StoreDto;
import com.qhtr.model.Store;

public interface StoreService {
	/**
	 * 通过商铺id或者手机号查找商铺(卖家版)
	 * @param storeId
	 * @return
	 */
	StoreDto getStoreByIdOrPhone(Store store);
	
	/**
	 * 通过商铺id查找店铺（买家）
	 * @param id
	 * @return
	 */
	public StoreDto getStoreById(int id);
	/**
	 * 根据经纬度，返回商铺信息
	 * @param location 经纬度
	 * @param distance 距离/默认1000米
	 * @return
	 */
	List<StoreDto> getStoresByDistance(String longitude,String latitude,String distance);
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
	/**
	 * 修改密码
	 * @param phone
	 * @param password
	 * @return
	 */
	int updatePassword(String phone, String password);
	/**
	 * 查询商铺的 购买用户信息
	 * @param storeId
	 * @return
	 */
	List<Map<String, Object>> getUserListByStoreId(int storeId);
}
