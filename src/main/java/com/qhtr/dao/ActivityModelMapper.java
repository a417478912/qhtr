package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.ActivityModel;

public interface ActivityModelMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityModel record);

    int insertSelective(ActivityModel record);

    ActivityModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityModel record);

    int updateByPrimaryKey(ActivityModel record);
    
     /*以下是自定义方法*/
    List<ActivityModel> selectAll();
}