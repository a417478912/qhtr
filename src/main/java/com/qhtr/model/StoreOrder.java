package com.qhtr.model;

import java.util.Date;

public class StoreOrder {
    private Integer id;

    private String orderCode;

    private String payOrderCode;

    private Integer storeId;

	/**
     * 取货方式/配送方式    1.快递   2.自取
     */

    private Integer distributionType;

    private Integer userId;

    private Integer couponId;

    private Integer totalPrice;

    private Integer addressId;

    private Integer expressPrice;

    private Integer couponPrice;

    private Integer refundPrice;

    private Integer resultPrice;

    private Integer status;

    private Date createTime;

    private Date paymentTime;

    private Date cancalTime;

    private String userRemark;
    
    private String sellerRemark;

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

    public String getPayOrderCode() {
        return payOrderCode;
    }

    public void setPayOrderCode(String payOrderCode) {
        this.payOrderCode = payOrderCode == null ? null : payOrderCode.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(Integer distributionType) {
        this.distributionType = distributionType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(Integer expressPrice) {
        this.expressPrice = expressPrice;
    }

    public Integer getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Integer refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Integer getResultPrice() {
        return resultPrice;
    }

    public void setResultPrice(Integer resultPrice) {
        this.resultPrice = resultPrice;
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

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getCancalTime() {
        return cancalTime;
    }

    public void setCancalTime(Date cancalTime) {
        this.cancalTime = cancalTime;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
    }

	public String getSellerRemark() {
		return sellerRemark;
	}

	public void setSellerRemark(String sellerRemark) {
		this.sellerRemark = sellerRemark;
	}
}