package com.sell.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.model.SecondClass;
import com.qhtr.model.Sku;
import com.qhtr.service.GoodsService;
import com.qhtr.service.SecondClassService;
import com.qhtr.service.SkuService;
/**
 * @author Harry
 * @Description 商家版 : 操作二级分类的 Controller
 * @date  2017年6月20日
 */
@Controller
@RequestMapping(value="/sell_secondClass")
public class Sell_SecondClassController {
	
	@Resource
	private SecondClassService secondClassService;

	/**
	 * 获取行业下的二级分类列表
	 * @param j
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getSecondClassByCategoryId")
	public Json getSecondClassByCategoryId(Json j,@RequestParam int categoryId,@RequestParam int storeId){
		
		List<Map<String, Object>> list = secondClassService.getSecondClassListByCategoryId(categoryId,storeId);
		if (list.isEmpty()) {
			j.setCode(0);
			j.setMessage("该行业下暂无分类 !");
		}else{
			j.setData(list);
			j.setMessage("获取成功 !");
		}
		return j;
	}
	
	/**
	 * 将商品从二级分类中删除
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteFromSecondClass")
	public Json deleteFromSecondClass(Json j,int[] goodsIds){
		
		int result = secondClassService.deleteFromSecondClass(goodsIds);
		
		if (result == 1) {
			j.setMessage("操作成功 !");
		}else{
			j.setMessage("操作失败 !");
			j.setCode(0);
		}
		return j;
	}
	
	@Resource
	private GoodsService goodsService;
	@Resource
	private SkuService skuService;
	
	/**
	 * 通过二级分类和店铺id查询商品列表
	 * @param j
	 * @param secondClassId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectGoodsListBySecondClass")
	public Json selectGoodsListBySecondClass(Json j,int secondClassId,int storeId){
		
		List<Goods> goodsList = goodsService.selectGoodsByStoreIdAndSecondClassId(storeId,secondClassId);
		
		if (goodsList.isEmpty()) {
			
			j.setCode(0);
			j.setMessage("该分类下暂无商品 !");
		}else{
			
			for (Goods goods : goodsList) {
				Integer goodsId = goods.getId();
				int totalStock = 0;
				List<Sku> skuList = skuService.selectListByGoodsId(goodsId);
				for (Sku sku : skuList) {
					Integer skuStock = sku.getStock();
					if (skuStock!=null) {
						totalStock +=skuStock;
					}
				}
				goods.setStock(totalStock);
			}
			j.setData(goodsList);
			j.setMessage("获取商品列表成功 !");
		}
		return j;
	}
	
	/**
	 * 查询不在二级分类列表中的商品
	 * @param j
	 * @param storeId
	 * @param secondClassId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsListNotInSecondClass")
	public Json getGoodsListNotInSecondClass(Json j,int storeId){
		
		List<Goods> goodsList = goodsService.getGoodsListNotInSecondClass(storeId);
		if (goodsList.isEmpty()) {
			
			j.setMessage("暂无商品 !");
			j.setCode(0);
		}else{
			
			for (Goods goods : goodsList) {
				Integer goodsId = goods.getId();
				int totalStock = 0;
				List<Sku> skuList = skuService.selectListByGoodsId(goodsId);
				for (Sku sku : skuList) {
					Integer skuStock = sku.getStock();
					if (skuStock!=null) {
						totalStock +=skuStock;
					}
				}
				goods.setStock(totalStock);
			}
			j.setData(goodsList);
			j.setMessage("获取商品列表成功 !");
		}
		return j;
	}
	
	/**
	 * 将商品批量添加进二级分类
	 * @param j
	 * @param goodsIds
	 * @param secondClassId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/buildRelationshipBatch")
	public Json buildRelationshipBatch(Json j,int[] goodsIds,int secondClassId){
		
		int result = secondClassService.buildRelationshipBatch(goodsIds,secondClassId);
		
		if (result == 1) {
			
			j.setMessage("添加成功 !");
		}else{
			
			j.setCode(0);
			j.setMessage("添加失败 !");
		}
		
		return j;
	}
}
