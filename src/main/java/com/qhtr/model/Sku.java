package com.qhtr.model;
/**
 * @author Harry
 * @Description 商品 sku
 * @date  2017年6月5日
 */
public class Sku {
	
    private Integer id;

    private Integer goodsId;

    private String attrValue;

    private String attrDetails;

    private Integer price;

    private Integer stock;

    private Integer status;

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

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    public String getAttrDetails() {
        return attrDetails;
    }

    public void setAttrDetails(String attrDetails) {
        this.attrDetails = attrDetails == null ? null : attrDetails.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Sku [id=" + id + ", goodsId=" + goodsId + ", attrValue=" + attrValue + ", attrDetails=" + attrDetails
				+ ", price=" + price + ", stock=" + stock + ", status=" + status + "]";
	}
    
    
}