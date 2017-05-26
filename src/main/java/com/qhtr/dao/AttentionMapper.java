package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Attention;

public interface AttentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attention record);

    int updateByPrimaryKey(Attention record);
    
    
    //以下是自定义方法
    /**
     * 通过用户id查询关注的店铺信息
     * @param userId
     * @return
     */
	List<Map<String, Object>> getAttentionList(int userId);

	List<Attention> selectByConditions(Map<String, Object> map);
}