package com.qhtr.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;
/**
 * @author Harry
 * @Description 商品
 * @date  2017年6月5日
 */
public class Goods {
	
    private Integer id;

    private String goodsCode;

    private Integer storeId;

    private String name;
	/**
     * 缩略图
     */
    private String thumb;
	/**
     * 效果图
     */
    private String resultPicture;
	/**
     * 详情图
     */
    private String detailPictures;

    private Integer price;

    private Integer vipPrice;

    private Integer specialSellingPrice;

    private Integer stock;

    private String details;

    private Integer isdefault;
	/**
     * 1已上架 2.已下架  3.已删除
     */
    private Integer status;
    
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date createTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date editTime;

    private Integer collectNum;

    private Integer sellNum;

    private Integer sort;
    
    

    public Goods() {
		super();
	}

	public Goods(Integer id, String goodsCode, Integer storeId, String name, String thumb, String resultPicture,
			String detailPictures, Integer price, Integer vipPrice, Integer specialSellingPrice, Integer stock,
			String details, Integer isdefault, Integer status, Date createTime, Date editTime, Integer collectNum,
			Integer sellNum, Integer sort) {
		super();
		this.id = id;
		this.goodsCode = goodsCode;
		this.storeId = storeId;
		this.name = name;
		this.thumb = thumb;
		this.resultPicture = resultPicture;
		this.detailPictures = detailPictures;
		this.price = price;
		this.vipPrice = vipPrice;
		this.specialSellingPrice = specialSellingPrice;
		this.stock = stock;
		this.details = details;
		this.isdefault = isdefault;
		this.status = status;
		this.createTime = createTime;
		this.editTime = editTime;
		this.collectNum = collectNum;
		this.sellNum = sellNum;
		this.sort = sort;
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
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
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
        this.name = name == null ? null : name.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getResultPicture() {
        return resultPicture;
    }

    public void setResultPicture(String resultPicture) {
        this.resultPicture = resultPicture == null ? null : resultPicture.trim();
    }

    public String getDetailPictures() {
        return detailPictures;
    }

    public void setDetailPictures(String detailPictures) {
        this.detailPictures = detailPictures == null ? null : detailPictures.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Integer vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getSpecialSellingPrice() {
        return specialSellingPrice;
    }

    public void setSpecialSellingPrice(Integer specialSellingPrice) {
        this.specialSellingPrice = specialSellingPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
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

    
}