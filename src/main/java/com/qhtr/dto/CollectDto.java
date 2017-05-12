package com.qhtr.dto;

import java.util.List;

import com.qhtr.model.Collect;

public class CollectDto {
	public int categoryId;
	public String categoryName;
	public List<Collect> collects;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Collect> getCollects() {
		return collects;
	}
	public void setCollects(List<Collect> collects) {
		this.collects = collects;
	}
}
