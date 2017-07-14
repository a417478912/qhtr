package com.qhtr.model;
/**
 * @author Harry
 * @Description 店铺点赞实体
 * @date  2017年6月27日
 */
public class StoreThumbsUp {

	private int id;
	private int userId;
	private int storeId;
	
	
	public StoreThumbsUp() {
		super();
	}
	public StoreThumbsUp(int storeId, int userId) {
		
		this.storeId = storeId;
		this.userId = userId;
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
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
}
