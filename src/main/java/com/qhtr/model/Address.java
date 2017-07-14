package com.qhtr.model;
/**
 * @author Harry
 * @Description 收货地址信息实体
 * @date  2017年6月2日
 */
public class Address {
	
    private Integer id;

    private Integer userId;

    private String receivingName;

    private String receivingPhone;
    // 地名
    private String addressName;
    // 详细地址
    private String details;

    private Integer isdefault;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReceivingName() {
		return receivingName;
	}

	public void setReceivingName(String receivingName) {
		this.receivingName = receivingName;
	}

	public String getReceivingPhone() {
		return receivingPhone;
	}

	public void setReceivingPhone(String receivingPhone) {
		this.receivingPhone = receivingPhone;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

    
}