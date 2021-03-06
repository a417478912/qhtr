package com.qhtr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.app.dto.StoreDto_App;
import com.qhtr.model.Store;

public interface StoreService {
	/**
	 * 通过商铺id或者手机号查找商铺(卖家版)
	 * @param storeId
	 * @return
	 */
	Store getStoreByIdOrPhone(Store store);
	
	/**
	 * 通过商铺id查找店铺（买家）
	 * @param id
	 * @return
	 */
	public StoreDto_App getStoreById(int id);
	/**
	 * 根据经纬度，返回商铺信息
	 * @param location 经纬度
	 * @param distance 距离/默认1000米
	 * @return
	 */
	List<StoreDto_App> getStoresByDistance(String longitude,String latitude,String distance);
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
	/**
	 * 查询所有
	 * @return
	 */
	List<Store> getAll();
	/**
	 * 查询店铺中商品的数量
	 * @param id
	 * @return
	 */
	int selectGoodsNumByStoreId(Integer storeId);

	/**
	 * 根据店铺ID查询店铺
	 * @param storeId
	 * @return
	 */
	Store selectStoreById(Integer storeId);

	/**
	 * 通过行业分类id查询店铺
	 * @param categoryId
	 * @return
	 */
	List<Store> selectStoreByCategoryId(int categoryId);

	/**
	 * 根据经纬度 和 categoryId查询店铺
	 * @param longitude
	 * @param latitude
	 * @param accuracy
	 * @param categoryId
	 * @return
	 */
	List<StoreDto_App> getStoresByDistanceAndCategoryId(String longitude, String latitude, String accuracy,
			int categoryId);

	/**
	 * 获取关注店铺的用户id
	 * @param storeId
	 * @return
	 */
	List<Integer> getAttentionUserByStoreId(int storeId);
}
