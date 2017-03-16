package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Goods;

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
     * 周边好货
     * @return
     */

	List<Goods> selectGoodsByCondition1();
	/**
	 * 通过内容搜索
	 * @param searchContent
	 * @return
	 */
	List<Goods> selectGoodsBySearch(String searchContent);
    
}