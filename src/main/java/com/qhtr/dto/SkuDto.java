package com.qhtr.dto;

import java.math.BigDecimal;

import com.qhtr.model.Sku;

public class SkuDto {
	public Integer id;
	public Integer goodsId;
	public String attrDetails;
	public BigDecimal price;
	public Integer stock;
	public Integer status;
	
	public SkuDto(){
	}
	
	public SkuDto(Sku sku){
		this.id = sku.getId();
		this.goodsId = sku.getGoodsId();
		this.attrDetails = sku.getAttrDetails();
		this.price = new BigDecimal(sku.getPrice()/100);
		this.stock = sku.getStock();
		this.status = sku.getStatus();
	}
	
	public Sku dtoToSku(SkuDto dto){
		Sku sku = new Sku();
		sku.setId(dto.getId());
		sku.setGoodsId(dto.getGoodsId());
		sku.setAttrDetails(dto.getAttrDetails());
		sku.setPrice(dto.getPrice().multiply(BigDecimal.valueOf(100)).intValue());
		sku.setStatus(dto.getStatus());
		sku.setStock(dto.getStock());
		return sku;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getAttrDetails() {
		return attrDetails;
	}
	public void setAttrDetails(String attrDetails) {
		this.attrDetails = attrDetails;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
