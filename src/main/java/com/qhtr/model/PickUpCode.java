package com.qhtr.model;

/**
 * 取货码
 * @author Harry
 * @date  2017年7月11日
 */
public class PickUpCode {

	private Integer id;
	private String pickUpCode;
	private Integer storeOrderId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPickUpCode() {
		return pickUpCode;
	}
	public void setPickUpCode(String pickUpCode) {
		this.pickUpCode = pickUpCode;
	}
	public Integer getStoreOrderId() {
		return storeOrderId;
	}
	public void setStoreOrderId(Integer storeOrderId) {
		this.storeOrderId = storeOrderId;
	}
}
