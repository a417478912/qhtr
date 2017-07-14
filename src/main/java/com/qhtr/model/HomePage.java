package com.qhtr.model;
/**
 * @author Harry
 * @Description 首页轮播图
 * @date  2017年6月5日
 */
public class HomePage {
	
	private Integer id;
	private String pic;
	private Integer storeId;
	
	public HomePage(Integer id, String pic, Integer storeId) {
		super();
		this.id = id;
		this.pic = pic;
		this.storeId = storeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	
}
