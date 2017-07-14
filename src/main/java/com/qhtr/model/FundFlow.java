package com.qhtr.model;

import java.util.Date;
/**
 * @author Harry
 * @Description 资金流转
 * @date  2017年6月5日
 */
public class FundFlow {
	
    private Integer id;

    private Integer changeMoney;
    /**
     * 11 买家购买商品付款   
     * 21 卖家卖出商品金额   22 收货以后，卖家实际收货金额 
     */
    private Integer type;

    private Date createTime;

    private Integer userId;

    private Integer storeId;

    private String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Integer changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}