package com.qhtr.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.qhtr.model.Attr;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsClasses;
import com.qhtr.model.Picture;
import com.qhtr.model.Sku;
import com.qhtr.service.PictureService;
import com.qhtr.service.SkuService;
import com.qhtr.utils.ApplicationContextUtils;

public class GoodsDto {
	public Integer id;
	public String goodsCode;
	public Integer storeId;
	public String name;
	public String thumb;
	public String resultPicture;
	public int topPrice;
	public int lowPrice;
	public int totalStock = 0;
	public String details;
	public Integer status;
	private Integer collectNum;
	private Integer sellNum;
	private Integer sort;
	private Integer secondClassId;
	
	
	/**
     * 详情图
     */
    private List<Picture> detailPictures;
	
	public List<GoodsClasses> goodsClasses;
	public List<Map<String,Object>> activityList;
	public List<Attr> attrList;
	public List<Sku> skuList;
	public GoodsDto(){
	}

	public GoodsDto(Goods goods){
		
		SkuService skuService = (SkuService) ApplicationContextUtils.getContext().getBean("SkuService");
		
		this.setId(goods.getId());
		this.setGoodsCode(goods.getGoodsCode());
		this.setStoreId(goods.getStoreId());
		this.setName(goods.getName());
		this.setThumb(goods.getThumb());
		this.setResultPicture(goods.getResultPicture());
		this.setDetails(goods.getDetails());
		this.setStatus(goods.getStatus());
		this.setCollectNum(goods.getCollectNum());
		this.setSellNum(goods.getSellNum());
		this.setSort(goods.getSort());
		
		int topPrice = 0;
		int lowPrice = 10000000;
		int totalStock = 0;
		List<Sku> skuList = skuService.selectListByGoodsId(goods.getId());
		for (Sku sku : skuList) {
			if(sku.getPrice() > topPrice){
				topPrice = sku.getPrice();
			}
			if(sku.getPrice() < lowPrice){
				lowPrice = sku.getPrice();
			}
			if (sku.getStock()!=null) {
				totalStock += sku.getStock();
			}
		}
		this.setLowPrice(lowPrice);
		this.setTopPrice(topPrice);
		this.setTotalStock(totalStock);
		
		if(StringUtils.isNotBlank(goods.getDetailPictures())){
			List<Picture> pictures1 = new ArrayList<Picture>();
			String[] ids = goods.getDetailPictures().split(",");
			for (String string : ids) {
				PictureService pictureService = (PictureService) ApplicationContextUtils.getContext().getBean("PictureService");
				Picture pic = pictureService.getById(Integer.parseInt(string));
				pictures1.add(pic);
			}
			this.setDetailPictures(pictures1);
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getResultPicture() {
		return resultPicture;
	}
	public void setResultPicture(String resultPicture) {
		this.resultPicture = resultPicture;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	public Integer getSellNum() {
		return sellNum;
	}
	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<GoodsClasses> getGoodsClasses() {
		return goodsClasses;
	}
	public void setGoodsClasses(List<GoodsClasses> goodsClasses) {
		this.goodsClasses = goodsClasses;
	}
	public List<Map<String, Object>> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Map<String, Object>> activityList) {
		this.activityList = activityList;
	}
	public List<Attr> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<Attr> attrList) {
		this.attrList = attrList;
	}

	public List<Picture> getDetailPictures() {
		return detailPictures;
	}

	public void setDetailPictures(List<Picture> detailPictures) {
		this.detailPictures = detailPictures;
	}

	public List<Sku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

	public int getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(int topPrice) {
		this.topPrice = topPrice;
	}

	public int getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public Integer getSecondClassId() {
		return secondClassId;
	}

	public void setSecondClassId(Integer secondClassId) {
		this.secondClassId = secondClassId;
	}
}
