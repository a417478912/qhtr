package com.qhtr.model;
/**
 * @author Harry
 * @Description 商品点赞实体
 * @date  2017年6月27日
 */
public class GoodsThumbsUp {

	private int id;
	private int userId;
	private int goodsId;
	
	
	public GoodsThumbsUp() {
		super();
	}
	public GoodsThumbsUp(int userId, int goodsId) {
		
		this.userId = userId;
		this.goodsId = goodsId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	
	
}
