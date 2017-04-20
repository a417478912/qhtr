package com.sell.param;


public class GoodsParam {
	public int id;
	public String descript;
	public int storeId;
	public String name;
	public String detail_pictures;
	public String thumb;
	public String resultPicture;
	public int[] classId;
	public String sku; 
	public int[] activityId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail_pictures() {
		return detail_pictures;
	}
	public void setDetail_pictures(String detail_pictures) {
		this.detail_pictures = detail_pictures;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getResultPicture() {
		return resultPicture;
	}
	public void setResultPicture(String resultPicture) {
		this.resultPicture = resultPicture;
	}
	public int[] getClassId() {
		return classId;
	}
	public void setClassId(int[] classId) {
		this.classId = classId;
	}
	public int[] getActivityId() {
		return activityId;
	}
	public void setActivityId(int[] activityId) {
		this.activityId = activityId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
}
