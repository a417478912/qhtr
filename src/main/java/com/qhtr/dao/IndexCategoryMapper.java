package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.IndexCategory;

public interface IndexCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexCategory record);

    int insertSelective(IndexCategory record);

    IndexCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexCategory record);

    int updateByPrimaryKey(IndexCategory record);
    
    
    //以下是自定义方法

	List<IndexCategory> getByCategoreId(int categoreId);

	List<IndexCategory> selectAll();
}