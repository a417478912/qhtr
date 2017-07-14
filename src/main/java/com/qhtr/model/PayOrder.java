package com.qhtr.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;

/**
 * @author Harry
 * @Description 支付订单(支付时有效)
 * @date  2017年6月5日
 */
public class PayOrder {
    private Integer id;

    private String orderCode;

    private Integer userId;

    private Integer totalPrice;

    // 10 : 待付款      20 : 已付款   21 : 已付款带自取    30 : 已发货      40 : 已收货   50 : 已评价
    // 100 : 申请售后   110 : 已退款/已退货
    private Integer status;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date createTime;
    @JsonSerialize(using = CustomDateSerializer.class) 
    private Date paymentTime;

    private Integer payType;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}