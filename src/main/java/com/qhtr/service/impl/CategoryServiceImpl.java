package com.qhtr.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CategoryMapper;
import com.qhtr.model.Category;
import com.qhtr.model.Store;
import com.qhtr.service.CategoryService;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
	@Resource 
	public CategoryMapper categoryMapper;

	@Override
	public List<Category> selectAll() {
		return categoryMapper.selectByConditions(new Category());
	}

	@Override
	public Category getById(Integer categoreId) {
		return categoryMapper.selectByPrimaryKey(categoreId);
	}


}
