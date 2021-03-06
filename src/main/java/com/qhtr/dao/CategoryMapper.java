package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Category;
import com.qhtr.model.Store;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    //自定义方法
    List<Category> selectByConditions(Category record);

}