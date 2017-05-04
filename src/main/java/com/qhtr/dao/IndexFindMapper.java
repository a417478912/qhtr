package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.IndexFind;

public interface IndexFindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexFind record);

    int insertSelective(IndexFind record);

    IndexFind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexFind record);

    int updateByPrimaryKey(IndexFind record);

    /*以下是自定义方法*/
	List<IndexFind> findAll();
	/**
	 * 通过父类id查询
	 * @param id
	 * @return
	 */
	List<IndexFind> selectListByParentId(int id);
}