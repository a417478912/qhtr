package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Activity;
import com.qhtr.model.Goods;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    //以下是自定义方法
    int getGoodsNumByModelId(Map<String,String> map);

	/**
	 * 通过商铺id 和 活动模版id 查找活动商品
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	List<Goods> selectByStoreIdAndModelId(Map<String,String> map);
	/**
	 * 删除  通过 商铺id 和 活动模版id 商品活动
	 * @param storeId
	 * @param modelId
	 * @return
	 */
	int deleteByStroeIdAndModelId(Map<String, Integer> map);
	/**
	 * 查询不在此活动中的商品
	 * @param map
	 * @return
	 */
	List<Goods> selectByStoreIdAndModelIdExcept(Map<String, String> map);
	/**
	 * 删除活动商品
	 * @param map
	 * @return
	 */
	int deleteGoods(Map<String, Integer> map);
	/**
	 * 通过商品id查找活动
	 * @param goodsId
	 * @return
	 */
	List<Map<String,Object>> selectByGoodsId(int goodsId);
}