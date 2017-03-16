package com.qhtr.service;

import java.util.List;

import com.qhtr.model.Category;

public interface CategoryService {
	public List<Category> getCategorysByConditions(Category c);
}
