package com.qhtr.service;

import java.util.List;

import com.qhtr.model.Category;

public interface CategoryService {

	List<Category> selectAll();

	Category getById(Integer categoreId);
}
