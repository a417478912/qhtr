package com.qhtr.model;

import java.util.Date;

public class Withdraw {
    private Integer id;

    private String orderCode;

    private Integer bankcardId;

    private Integer storeId;

    private Integer totalPrice;
    /**
     * 状态 1待审核  2 已转账  3审核失败
     */
    private Integer status;

    private Date createTime;

    private Date transferAccountsTime;
    /**
     * 提现方式  1.支付宝  2微信  3银行卡
     */
    private Integer payType;

    private String reason;

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

    public Integer getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(Integer bankcardId) {
        this.bankcardId = bankcardId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Date getTransferAccountsTime() {
        return transferAccountsTime;
    }

    public void setTransferAccountsTime(Date transferAccountsTime) {
        this.transferAccountsTime = transferAccountsTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}