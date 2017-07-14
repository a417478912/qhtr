package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    
    //自定义方法
    List<Store> selectByConditions(Store store);
    /**
     * 热店搜索
     * @return
     */
	List<Store> selectHotStore();
	/**
	 * 通过内容搜索
	 * @param searchContent
	 * @return
	 */
	List<Store> selectStoreBySearch(String searchContent);
	/**
	 * 查询商铺的 购买用户信息
	 * @param storeId
	 * @return
	 */
	List<Map<String, Object>> getUserListByStoreId(int storeId);
	/**
	 * 新店首发
	 * @return
	 */
	List<Store> getNewStoreList();
	/**
	 * 查询店铺中商品的数量
	 * @param id
	 * @return
	 */
	int selectGoodsNumByStoreId(Integer storeId);

	/**
	 * 根据行业分类查询店铺
	 * @param categoryId
	 * @return
	 */
	List<Store> selectStoreByCategoryId(int categoryId);

	/**
	 * 获取关注店铺的用户id
	 * @param storeId
	 * @return
	 */
	List<Integer> getAttentionUserByStoreId(int storeId);
}