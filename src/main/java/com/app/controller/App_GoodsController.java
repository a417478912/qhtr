package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.GoodsListDto_App;
import com.app.dto.StoreDto_App;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;
import com.qhtr.common.Json;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.Goods;
import com.qhtr.model.SecondClass;
import com.qhtr.service.ActivityService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.PictureService;
import com.qhtr.service.SecondClassService;
import com.qhtr.service.SkuService;
import com.qhtr.service.StoreService;
import com.qhtr.utils.PicUtils;
import com.qhtr.utils.SmsUtils;
/**
 * @author Harry
 * @Description 用户版 : 操作商品的 Controller
 * @date  2017年6月22日
 */
@Controller
@RequestMapping("/app_goods")
public class App_GoodsController {
	
	@Resource
	private GoodsService goodsService;
	@Resource
	private ActivityService activityService;
	@Resource
	public SkuService skuService;
	@Resource
	public PictureService pictureService;
	@Resource
	private StoreService storeService;
	
	/**
	 * 通过商品id  获取商品详情
	 * @param j
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsByGoodsId")
	public Json selectGoodsByGoodsId(Json j,@RequestParam int goodsId,HttpServletRequest request){
		
		GoodsDto dto = goodsService.selectGoodsByGoodsId(goodsId);
		
		if(dto != null){
			j.setData(dto);
		}else{
			j.setCode(0);
		}
		return j;
	}
	
	/**
	 * 查询活动商品  selectByStoreIdAndModelId
	 */
	@ResponseBody
	@RequestMapping(value="/selectByStoreIdAndModelId")
	public Json selectByStoreIdAndModelId(Json j,@RequestParam int storeId,@RequestParam(defaultValue="0") int modelId,@RequestParam(defaultValue="1") int page){
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<GoodsListDto_App> dtoList = new ArrayList<GoodsListDto_App>();
		List<Goods> list = null;
		if (modelId != 0) {
			Page<?> startPage = PageHelper.startPage(page,10);
			list = activityService.selectByStoreIdAndModelId(storeId,modelId);
			map.put("totalNum", startPage.getTotal());
			map.put("totalPages", startPage.getPages());
		}else{
			Page<?> startPage = PageHelper.startPage(page,10);
			list = goodsService.selectListByStoreAndType(storeId, 1,page); 
			map.put("totalNum", startPage.getTotal());
			map.put("totalPages", startPage.getPages());
		}
		for (Goods goods : list) {
			dtoList.add(new GoodsListDto_App(goods));
		}
		map.put("list", dtoList);
		
		if (modelId == 2 || modelId == 1) {
			
			for (GoodsListDto_App goodsDto : dtoList) {
				
				goodsDto.getPicture().setUrl(goodsDto.getResultPic());
				goodsDto.getPicture().setHeight(PicUtils.getImgHeight(goodsDto.getResultPic())+"");
				goodsDto.getPicture().setWidth(PicUtils.getImgWidth(goodsDto.getResultPic()) +"");
			}
		}
		
		j.setData(map);
		return j;
	}
	
	/**
	 * 获取分类中的商品
	 * @param j
	 * @param classId
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsByClass")
	public Json getGoodsByClassIdAndStoreId(Json j,@RequestParam int classId,@RequestParam int storeId,@RequestParam(defaultValue="1") int page){
		
		Map<String, Object> map = new HashMap<>();
		Page startPage = PageHelper.startPage(page, 10);
		List<Goods> goodsList = goodsService.selectListByClassIdAndStoreId(classId, storeId);
		List<GoodsDto> list = new ArrayList<>();
		if (page > startPage.getPages()) {
			j.setCode(0);
			j.setMessage("没有更多了~");
			return j;
		}
		if (!goodsList.isEmpty()) {
			
			for (Goods goods : goodsList) {
				list.add(new GoodsDto(goods));
			}
			map.put("goodsList", list);
			map.put("totalNum", startPage.getTotal());
			map.put("totalPage", startPage.getPages());
			j.setData(map);
			j.setMessage("商品列表获取成功!");
		}else{
			
			j.setMessage("该分类下暂无商品 !");
		}
		return j;
	}
	
	@Resource
	private SecondClassService secondClassService;
	
	/**
	 * 通过二级分类的id,获取商品列表
	 * @param j
	 * @param secondClassId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getGoodsBySecondClass")
	public Json getGoodsBySecondClass(Json j,int secondClassId,@RequestParam(defaultValue="1") int page){
		
		Map<String, Object> map = new HashMap<>();
		SecondClass secondClass = secondClassService.selectSecondClassByPrimaryKey(secondClassId);
		
		if (secondClass == null) {
			j.setCode(0);
			j.setMessage("暂无该分类 !");
			return j;
		}
		
		Page startPage = PageHelper.startPage(page, 10);
		List<Goods> goodsList = goodsService.selectGoodsBySecondClassId(secondClassId);
		if (page > startPage.getPages()) {
			j.setCode(0);
			j.setMessage("没有更多了~");
			return j;
		}
		List<GoodsDto> list = new ArrayList<>();
		for (Goods goods : goodsList) {
			list.add(new GoodsDto(goods));
		}
		
		if (goodsList.isEmpty()) {
			
			j.setCode(0);
			j.setMessage("该分类下暂无商品 !");
			return j;
		}else{
			map.put("goodsList", list);
			map.put("totalNum", startPage.getTotal());
			map.put("totalPage", startPage.getPages());
			j.setData(map);
			j.setMessage("获取商品列表成功 !");
		}
		return j;
	}
	
	/**
	 * 通过行业分类查询商品列表
	 * @param j
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectGoodsListByCategory")
	public Json selectGoodsListByCategory(Json j,int categoryId,@RequestParam(defaultValue="1") int page){
		
		Map<String, Object> map = new HashMap<>();
		Page startPage = PageHelper.startPage(page, 10);
		List<Goods> goodsList = goodsService.selectGoodsListByCategoryId(categoryId);
		if (page > startPage.getPages()) {
			j.setCode(0);
			j.setMessage("没有更多了~");
			return j;
		}
		List<GoodsDto> list = new ArrayList<>();
		if (goodsList.isEmpty()) {
			
			j.setCode(0);
			j.setMessage("该行业下暂无商品 !");
			return j;
			
		}else{
			for (Goods goods : goodsList) {
				list.add(new GoodsDto(goods));
			}
			map.put("goodsList", list);
			map.put("totalNum", startPage.getTotal());
			map.put("totalPage", startPage.getPages());
			j.setData(map);
			j.setMessage("获取商品列表成功 !");
			return j;
		}
	}
	
	/**
	 * 二级分类 : 新品
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getNewProductList")
	public Json getNewProductList(Json j,int categoryId,@RequestParam(defaultValue="1") int page){
		
		Map<String, Object> map = new HashMap<>();
		
		Page<?> startPage = PageHelper.startPage(page, 10);
		List<Goods> goodsList = goodsService.selectNewProductByCategoryId(categoryId);
		
		if (page > startPage.getPages()) {
			
			j.setCode(0);
			j.setMessage("没有更多了~");
			return j;
		}
		List<GoodsDto> list = new ArrayList<>();
		
		if (!goodsList.isEmpty()) {
			
			for (Goods goods : goodsList) {
				
				list.add(new GoodsDto(goods));
			}
			map.put("goodsList", list);
			map.put("totalNum", startPage.getTotal());
			map.put("totalPage", startPage.getPages());
			j.setData(map);
			j.setMessage("商品列表获取成功 !");
			return j;
		}else{
			j.setMessage("暂无商品 !");
			j.setCode(0);
			return j;
		}
	}
}
