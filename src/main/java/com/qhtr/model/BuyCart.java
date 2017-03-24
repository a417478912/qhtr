package com.qhtr.model;

public class BuyCart {
    private Integer id;

    private Integer goodsId;

    private Integer skuId;

    private Integer num;

    private Integer storeId;

    private Integer userId;
    
    /**
     * 商品名字
     */
    private String string1;
    /**
     * sku详情
     */
    private String string2;
    /**
     * 价格
     */
    private String string3;
    /**
     * goods code
     */
    private String string4;
    /**
     * 商品图片
     */
    private String string5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getString4() {
        return string4;
    }

    public void setString4(String string4) {
        this.string4 = string4 == null ? null : string4.trim();
    }

    public String getString5() {
        return string5;
    }

    public void setString5(String string5) {
        this.string5 = string5 == null ? null : string5.trim();
    }
}