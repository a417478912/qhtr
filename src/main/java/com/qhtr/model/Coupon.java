package com.qhtr.model;

import java.util.Date;

public class Coupon {
    private Integer id;

    private String name;

    private Integer money;

    private Integer limitMoney;

    private Integer storeId;

    private Integer userId;

    private Integer couponId;

    private Date validityTimeBegin;

    private Date validityTimeEnd;

    private String effectiveTime;

    private Integer isShare;

    private Date createTime;

    private Date userTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(Integer limitMoney) {
        this.limitMoney = limitMoney;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Date getValidityTimeBegin() {
        return validityTimeBegin;
    }

    public void setValidityTimeBegin(Date validityTimeBegin) {
        this.validityTimeBegin = validityTimeBegin;
    }

    public Date getValidityTimeEnd() {
        return validityTimeEnd;
    }

    public void setValidityTimeEnd(Date validityTimeEnd) {
        this.validityTimeEnd = validityTimeEnd;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime == null ? null : effectiveTime.trim();
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}