package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CategoryMapper;
import com.qhtr.model.Category;
import com.qhtr.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource 
	public CategoryMapper categoryMapper;

	@Override
	public List<Category> getCategorysByConditions(Category c) {
		return categoryMapper.selectByConditions(c);
	}

}