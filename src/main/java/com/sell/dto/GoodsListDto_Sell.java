package com.sell.dto;

import java.util.List;

import com.qhtr.model.Goods;
import com.qhtr.model.Picture;
import com.qhtr.model.Sku;
import com.qhtr.service.PictureService;
import com.qhtr.service.SkuService;
import com.qhtr.utils.ApplicationContextUtils;

public class GoodsListDto_Sell {
	public int id;
	public String picture;
	public String name;
	public int topPrice;
	public int lowPrice;
	public int totalStock = 0;
	public int status;
	
	public GoodsListDto_Sell(){
	}
	
	public GoodsListDto_Sell(Goods goods){
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
		this.setLowPrice(lowPrice);
		this.setTopPrice(topPrice);
		this.setName(goods.getName());
		
		//详情图
		if(goods.getDetailPictures() != null){
			int picId = Integer.parseInt(goods.getDetailPictures().split(",")[0]);
			Picture pic = pictureService.getById(picId);
			this.setPicture(pic.getUrl());
		}
		
		this.setStatus(goods.getStatus());
		this.setTotalStock(totalStock);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
