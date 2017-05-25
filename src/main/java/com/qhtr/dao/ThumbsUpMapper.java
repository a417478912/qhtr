package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.ThumbsUp;

public interface ThumbsUpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThumbsUp record);

    int insertSelective(ThumbsUp record);

    ThumbsUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThumbsUp record);

    int updateByPrimaryKey(ThumbsUp record);

    //自定义方法
    
    /**
     * 条件查找评论表
     * @param userId
     * @param commentId
     * @return
     */
	List<ThumbsUp> selectByConditions(Map<String,Integer> map);
}