package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
    
    //以下为自定义内容
    /**
     * 通过接 条件查询 商品
     */
    List<Goods> selectByConditions(Goods goods);
    /**
     *  周边好货
     * @return
     */

	List<Goods> selectGoodsListByGoodAround();
	/**
	 * 通过内容搜索
	 * @param searchContent
	 * @return
	 */
	List<Goods> selectGoodsBySearch(String searchContent);
	/**
	 * 通过商铺id和商品状态查找商品
	 * @param storeId
	 * @param status
	 * @return
	 */
	List<Goods> selectListByStoreAndType(Map<String,String> map);
	/**
	 * 活动商品列表
	 * @param storeId
	 * @param activityId
	 * @return
	 */
	List<Goods> getActivityGoods(Map<String,Integer> map);
	
	/**
	 * 买家版，通过店铺id和分类id查询商品列表
	 */

	List<Goods> getGoodsByClass_App(int classId);

	/**
	 * 通过分类和店铺id查找商品
	 * @param gc
	 * @return
	 */
	List<Goods> selectListByClassIdAndStoreId(GoodsClasses gc);

	/**
	 * 通过店铺id和商品状态查找商品
	 * @param goods
	 * @return
	 */
	List<Goods> selectListByStoreIdAndStatus(Goods goods);

	/**
	 * 通过二级分类查询商品
	 * @param secondClassId
	 * @return
	 */
	List<Goods> selectGoodsBySecondClassId(int secondClassId);

	/**
	 * 根据店铺id查询十条商品数据
	 * @param storeId
	 * @return
	 */
	List<Goods> selectListByStoreId(int storeId);

	/**
	 * 通过行业分类查询商品列表
	 * @param categoryId
	 * @return
	 */
	List<Goods> selectGoodsListByCategoryId(int categoryId);
	
	/**
	 * 通过店铺id和二级分类id查询商品列表
	 * @param map
	 * @return
	 */
	List<Goods> selectGoodsByStoreIdAndSecondClassId(Map<String, Integer> map);

	/**
	 * 查询不在二级分类列表中的商品
	 * @param map
	 * @return
	 */
	List<Goods> getGoodsListNotInSecondClass(int storeId);

	
	/**
	 * 查询新品
	 * @param map
	 * @return
	 */
	List<Goods> selectNewProductByCategoryId(Map<String, Object> map);

	/**
	 * 查询二级分类中商品数量
	 * @param param
	 * @return
	 */
	Integer selectCountBySecondClassIdAndStoreId(Map<String, Integer> param);

	/**
	 * 通过行业分类查询商品
	 * @param categoryId
	 * @return
	 */
	List<Goods> selectGoodsByCategoryId(int categoryId);

}