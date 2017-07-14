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
    private String goodsName;
    /**
     * sku详情
     */
    private String skuDetail;
    /**
     * 价格
     */
    private String price;
    /**
     * goods code
     */
    private String goodsCode;
    /**
     * 商品图片
     */
    private String goodsThumbs;

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsThumbs() {
		return goodsThumbs;
	}

	public void setGoodsThumbs(String goodsThumbs) {
		this.goodsThumbs = goodsThumbs;
	}

	public String getSkuDetail() {
		return skuDetail;
	}

	public void setSkuDetail(String skuDetail) {
		this.skuDetail = skuDetail;
	}

}