package com.qhtr.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;
/**
 * @author Harry
 * @Description 店铺订单(包含多个商品订单)
 * @date  2017年6月5日
 */
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

    private Integer couponPrice = 0;

    private Integer refundPrice;

    private Integer resultPrice;

    // 10 : 待付款      20 : 已付款   21 : 已付款带自取    30 : 已发货      40 : 已收货   50 : 已评价
    // 100 : 申请售后   110 : 已退款/已退货
    private Integer status;
    
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date createTime;
    
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date paymentTime;
    
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date cancalTime;

    private String userRemark;

    private String sellerRemark;

    private String receivingName;

    private String receivingPhone;

    private String addressDetails;

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
        this.sellerRemark = sellerRemark == null ? null : sellerRemark.trim();
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName == null ? null : receivingName.trim();
    }

    public String getReceivingPhone() {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone == null ? null : receivingPhone.trim();
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails == null ? null : addressDetails.trim();
    }
}