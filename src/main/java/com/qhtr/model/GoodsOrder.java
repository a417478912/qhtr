package com.qhtr.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;

public class GoodsOrder {
    private Integer id;

    private String orderCode;

    private String storeOrderCode;

    private Integer storeOrderId;

    private Integer goodsId;

    private Integer userId;

    private Integer storeId;

    private Integer skuId;

    private Integer status;

    private Integer num;

    private Integer price;
    
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date createTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date cancalTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date shipmentsTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date receiveTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date commentTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date afterSaleTime;

    private String goodsName;

    private String goodsPicture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getStoreOrderCode() {
        return storeOrderCode;
    }

    public void setStoreOrderCode(String storeOrderCode) {
        this.storeOrderCode = storeOrderCode == null ? null : storeOrderCode.trim();
    }

    public Integer getStoreOrderId() {
        return storeOrderId;
    }

    public void setStoreOrderId(Integer storeOrderId) {
        this.storeOrderId = storeOrderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCancalTime() {
        return cancalTime;
    }

    public void setCancalTime(Date cancalTime) {
        this.cancalTime = cancalTime;
    }

    public Date getShipmentsTime() {
        return shipmentsTime;
    }

    public void setShipmentsTime(Date shipmentsTime) {
        this.shipmentsTime = shipmentsTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getAfterSaleTime() {
        return afterSaleTime;
    }

    public void setAfterSaleTime(Date afterSaleTime) {
        this.afterSaleTime = afterSaleTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture == null ? null : goodsPicture.trim();
    }
}