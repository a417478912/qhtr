package com.qhtr.model;
/**
 * @author Harry
 * @Description 店铺评分
 * @date  2017年6月13日
 */
public class StoreScore {
	
	private Integer id;
	private Integer storeId;
	private Double score;
	
	public StoreScore(){}
	
	public StoreScore(Integer id, Integer storeId, Double score) {
		super();
		this.id = id;
		this.storeId = storeId;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
}
