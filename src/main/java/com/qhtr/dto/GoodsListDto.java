package com.qhtr.dto;

import java.math.BigDecimal;
import java.util.List;

import com.qhtr.model.Goods;
import com.qhtr.model.Picture;
import com.qhtr.model.Sku;
import com.qhtr.service.PictureService;
import com.qhtr.service.SkuService;
import com.qhtr.utils.ApplicationContextUtils;

public class GoodsListDto {
	public int id;
	public Picture picture;
	public String name;
	public BigDecimal topPrice;
	public BigDecimal lowPrice;
	public int totalStock;
	
	public GoodsListDto(){
	}
	
	public GoodsListDto(Goods goods){
		SkuService skuService = (SkuService) ApplicationContextUtils.getContext().getBean("SkuService");
		PictureService pictureService = (PictureService) ApplicationContextUtils.getContext().getBean("PictureService");
		
		this.id = goods.getId();
		
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
			totalStock += sku.getStock();
		}
		this.setLowPrice(new BigDecimal(lowPrice).divide(new BigDecimal(100).setScale(2)));
		this.setTopPrice(new BigDecimal(topPrice).divide(new BigDecimal(100).setScale(2)));
		this.setName(goods.getName());
		
		//详情图
		if(goods.getDetailPictures() != null){
			int picId = Integer.parseInt(goods.getDetailPictures().split(",")[0]);
			Picture pic = pictureService.getById(picId);
			this.setPicture(pic);
		}
		
		this.setTotalStock(totalStock);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(BigDecimal topPrice) {
		this.topPrice = topPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}
}
