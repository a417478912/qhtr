package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;
import com.sell.param.GoodsParam;

public interface GoodsService {
	/**
	 * 通过商品id  获取商品详情
	 */
	public GoodsDto selectGoodsByGoodsId(int goodsId);
	/**
	 * 通过商品id  获取商品详情
	 * @param goodsId
	 * @return
	 */
	public Goods selectGoodsByPrimaryKey(int goodsId);
	/**
	 * 条件查找商品
	 */
	public List<Goods> selectGoodsByCondition(Goods goods,int page,int number);
	/**
	 * 周边好货
	 * page 第几页
	 * number 数量
	 */
	public List<Goods> selectGoodsListByGoodAround(int page,int number);
	/**
	 * 通过搜索内容搜索商品
	 * @param searchContent
	 * @return
	 */
	public List<Goods> selectGoodsBySearch(String searchContent, int page, int num);
	
	public int add(GoodsParam goodsParam);
	public int delete(int id,int status);
	public int update(GoodsParam goodsParam);
	
	/**
	 * 通过卖家id和商品状态查找商品
	 * @param storeId
	 * @param page 
	 * @param type
	 * @return
	 */
	public List<Goods> selectListByStoreAndType(int storeId, int status,int page);
	/**
	 * 置顶/取消置顶
	 * @param goodsId
	 * @return
	 */
	public int toTop(int goodsId);
	
	/**
	 * 下架商品重新上架
	 * @param goodsId
	 * @return
	 */
	public int reshelf(Integer goodsId);
	
	/**
	 * 将商品下架
	 * @param goodsId
	 * @param i
	 */
	public void soldOut(Integer goodsId);
	/**
	 * 查询该状态下的商品数量
	 * @param storeId
	 * @param status
	 * @return
	 */
	public int selectCountByStoreIdAndStatus(int storeId, int status);
	
	/**
	 * 查询分类中的商品
	 * @param classId
	 * @param storeId
	 * @return
	 */
	List<Goods> selectListByClassIdAndStoreId(int classId, int storeId);
	
	/**
	 * 修改商品
	 * @param goods
	 */
	public void updateGoodsByPrimaryKey(Goods goods);
	
	/**
	 * 通过二级分类查询商品
	 * @param secondClassId
	 * @return
	 */
	public List<Goods> selectGoodsBySecondClassId(int secondClassId);
	
	/**
	 * 通过店铺id查询十条商品
	 * @param storeId
	 * @return
	 */
	public List<Goods> selectListByStoreId(int storeId);
	
	/**
	 * 通过行业分类查询商品列表
	 * @return
	 */
	public List<Goods> selectGoodsListByCategoryId(int categoryId);
	
	/**
	 * 通过店铺id和二级分类id查询商品列表
	 * @param storeId
	 * @param secondClassId
	 * @return
	 */
	public List<Goods> selectGoodsByStoreIdAndSecondClassId(int storeId, int secondClassId);
	
	/**
	 * 通过店铺和行业分类查询不在二级分类中的商品列表
	 * @param storeId
	 * @param categoryId
	 * @return
	 */
	public List<Goods> getGoodsListNotInSecondClass(int storeId);
	
	/**
	 * 通过二级分类查询新品
	 * @param categoryId
	 * @return
	 */
	public List<Goods> selectNewProductByCategoryId(int categoryId);
	
	/**
	 * 通过categoryId查询商品
	 * @param categoryId
	 * @return
	 */
	public List<Goods> selectGoodsByCategoryId(int categoryId);
	
}
