package com.app.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.StoreDto_App;
import com.qhtr.common.Json;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsPic;
import com.qhtr.model.Store;
import com.qhtr.model.StoreScore;
import com.qhtr.service.GoodsService;
import com.qhtr.service.StoreScoreService;
import com.qhtr.service.StoreService;
/**
 * 
 * @author Harry
 * @Description 店铺相关的 Controller
 * @date  2017年6月2日
 */
@Controller
@RequestMapping("/app_store")
public class App_StoreController {
	
	@Resource
	public StoreService storeService;
	@Resource
	private StoreScoreService storeScoreService;
	
	/**
	 * 通过地理位置获取店铺
	 * @param j
	 * @param location
	 * @param distance
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStoresByDistance")
	public Json getStoresByDistance(Json j,@RequestParam int categoryId,@RequestParam String longitude,@RequestParam String latitude,@RequestParam(defaultValue="1000")String accuracy){
		
		List<StoreDto_App> stores = null;
		
		if (categoryId == 0) {
			
			stores = storeService.getStoresByDistance(longitude,latitude,accuracy);
		}else{
			stores = storeService.getStoresByDistanceAndCategoryId(longitude,latitude,accuracy,categoryId);
		}
		if (!stores.isEmpty()) {
			
		for (StoreDto_App storeDto_App : stores) {
			
			int id = storeDto_App.getId();
			List<GoodsPic> thumbList = new ArrayList<>();
			List<Goods> goodsList = goodsService.selectListByStoreId(id);
			// 如果商品列表为空,不予显示
			if (!goodsList.isEmpty()) {
			
				// 将商品图片添加进store
				for (Goods goods : goodsList) {
					
					if (goods.getThumb() != null || !"".equals(goods.getThumb())) {
						GoodsPic goodsPic = new GoodsPic();
						goodsPic.setId(goods.getId());
						goodsPic.setPic(goods.getResultPicture());
						thumbList.add(goodsPic);
					}
				}
				storeDto_App.setGoodsThumbs(thumbList);
			
			
			StoreScore ss = new StoreScore();
			ss.setStoreId(id);
			List<StoreScore> scoreList = storeScoreService.selectScoreByStoreId(ss);
			
			if (!scoreList.isEmpty()) {
				double scoreCount = 0;
				int scoreNum = scoreList.size();
				for (StoreScore storeScore : scoreList) {
					
					scoreCount += storeScore.getScore();
				}
				double score = scoreCount / scoreNum ;
				// 取小数点后一位
				DecimalFormat decimalFormat = new DecimalFormat(".#");
				storeDto_App.setScore(Double.parseDouble(decimalFormat.format(score)));
			}else{
				storeDto_App.setScore(0);
			}
		}
	}
}
		j.setData(stores);
		return j;
	}
	
	/**
	 * 通过id获取店铺信息
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStoreById")
	public Json getStoreById(Json j,@RequestParam int id){
		
		double score = 0.0;
		
		Store storeTem = new Store();
		
		storeTem.setId(id);
		StoreDto_App dto = storeService.getStoreById(id);
		StoreScore ss = new StoreScore();
		ss.setStoreId(id);
		List<StoreScore> scoreList = storeScoreService.selectScoreByStoreId(ss);
		if (!scoreList.isEmpty()) {
			double scoreCount = 0;
			int scoreNum = scoreList.size();
			for (StoreScore storeScore : scoreList) {
				
				scoreCount += storeScore.getScore();
			}
			score = scoreCount / scoreNum ;
			// 取小数点后一位
			DecimalFormat decimalFormat = new DecimalFormat(".#");
			dto.setScore(Double.parseDouble(decimalFormat.format(score)));
		}else{
			dto.setScore(score);
		}
		j.setData(dto);
		return j;
	}
	
	
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 根据行业分类获取店铺
	 * @param j
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStoreByCategory")
	public Json getStoreByCategory(Json j,int categoryId){
		
		List<Store> storeList = storeService.selectStoreByCategoryId(categoryId);
		
		if (storeList == null || storeList.size() == 0) {
			
			j.setCode(0);
			j.setMessage("该行业下暂无店铺 !");
			return j;
		}else{
			List<Store> list = new ArrayList<>();
			// 计算店铺分数
			for (Store store : storeList) {
				List<GoodsPic> thumbList = new ArrayList<>();
				Integer storeId = store.getId();
				List<Goods> goodsList = goodsService.selectListByStoreId(storeId);
				// 如果商品列表为空,不予显示
				if (!goodsList.isEmpty()) {
					
					// 将商品图片添加进store
					for (Goods goods : goodsList) {
						if (goods.getThumb() != null || !"".equals(goods.getThumb())) {
							GoodsPic goodsPic = new GoodsPic();
							goodsPic.setId(goods.getId());
							goodsPic.setPic(goods.getResultPicture());
							thumbList.add(goodsPic);
						}
					}
					store.setGoodsThumbs(thumbList);
					// 计算分数
					double score = 0.0;
					StoreScore ss = new StoreScore();
					ss.setStoreId(storeId);
					List<StoreScore> scoreList = storeScoreService.selectScoreByStoreId(ss);
					if (!scoreList.isEmpty()) {
						double scoreCount = 0;
						int scoreNum = scoreList.size();
						for (StoreScore storeScore : scoreList) {
							
							scoreCount += storeScore.getScore();
						}
						score = scoreCount / scoreNum ;
						// 取小数点后一位
						DecimalFormat decimalFormat = new DecimalFormat(".#");
						store.setScore(Double.parseDouble(decimalFormat.format(score)));
					}else{
						store.setScore(score);
					}
					list.add(store);
				}
			}
			if (list.isEmpty()) {
				j.setCode(0);
				j.setMessage("该行业下暂无符合条件的店铺 !");
				return j;
			}
			j.setData(list);
			j.setMessage("店铺信息获取成功 !");
			return j;
		}
	}
}
