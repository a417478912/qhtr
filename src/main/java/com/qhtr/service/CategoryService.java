package com.qhtr.service;

import java.util.List;

import com.qhtr.model.Category;
import com.qhtr.model.Store;

public interface CategoryService {

	List<Category> selectAll();

	Category getById(Integer categoreId);

}
