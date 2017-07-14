package com.qhtr.model;

public class QueryCount {
	
	private String startTime;
	private String endTime;
	private Integer storeId;
	
	
	public QueryCount(String startTime, String endTime, Integer storeId) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.storeId = storeId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
}
