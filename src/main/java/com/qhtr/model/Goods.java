package com.qhtr.model;

import java.util.Date;

public class Goods {
    private Integer id;

    private Integer sellerId;

    private String name;

    private Integer categoryId;

    private String thumb;

    private String picture1;

    private String picture2;

    private String picture3;

    private Integer price;

    private Integer vipPrice;

    private Integer specialSellingPrice;

    private Integer stock;

    private Integer isdefault;
	
	/**
     * 1.待审核  2已上架  3,审核失败  4.已下架  5.已删除
     */
    private Integer status;

    private Date createTime;

    private Date pullOnTime;

    private Date newPullOnTime;

    private Date startTime;

    private Date endTime;

    private Integer isNewPullOn;

    private Integer isRecommendedByStoreManager;

    private Integer isSpecialSelling;

    private String string1;

    private String string2;

    private String string3;

    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1 == null ? null : picture1.trim();
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2 == null ? null : picture2.trim();
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3 == null ? null : picture3.trim();
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

    public Date getPullOnTime() {
        return pullOnTime;
    }

    public void setPullOnTime(Date pullOnTime) {
        this.pullOnTime = pullOnTime;
    }

    public Date getNewPullOnTime() {
        return newPullOnTime;
    }

    public void setNewPullOnTime(Date newPullOnTime) {
        this.newPullOnTime = newPullOnTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsNewPullOn() {
        return isNewPullOn;
    }

    public void setIsNewPullOn(Integer isNewPullOn) {
        this.isNewPullOn = isNewPullOn;
    }

    public Integer getIsRecommendedByStoreManager() {
        return isRecommendedByStoreManager;
    }

    public void setIsRecommendedByStoreManager(Integer isRecommendedByStoreManager) {
        this.isRecommendedByStoreManager = isRecommendedByStoreManager;
    }

    public Integer getIsSpecialSelling() {
        return isSpecialSelling;
    }

    public void setIsSpecialSelling(Integer isSpecialSelling) {
        this.isSpecialSelling = isSpecialSelling;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1 == null ? null : string1.trim();
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2 == null ? null : string2.trim();
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3 == null ? null : string3.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}