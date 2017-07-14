package com.qhtr.model;

import java.util.Date;
/**
 * @author Harry
 * @Description 优惠券
 * @date  2017年6月5日
 */
public class CouponTemplate {
	
    private Integer id;

    private String name;

    private Integer money;

    private Integer limitMoney;

    private String color;

    private Date validityTimeBegin;

    private Date validityTimeEnd;

    private Integer time1;

    private Integer time2;

    private String effectiveTime;

    private Integer limitNum;

    private Integer totalNum;

    private Integer isShare;

    private Date createTime;

    private Integer storeId;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
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

    public Integer getTime1() {
        return time1;
    }

    public void setTime1(Integer time1) {
        this.time1 = time1;
    }

    public Integer getTime2() {
        return time2;
    }

    public void setTime2(Integer time2) {
        this.time2 = time2;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime == null ? null : effectiveTime.trim();
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}