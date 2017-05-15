package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.dto.CollectDto;
import com.qhtr.model.Collect;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
    
    
    //以下是自定义方法
    /**
     * 通过用户id查询收藏
     * @param userId
     * @return
     */
	List<CollectDto> selectByUserid(int userId);
	/**
	 * 条件查找收藏
	 * @param map
	 * @return
	 */
	List<Collect> selectByConditions(Map<String, Object> map);
	/**
	 * 最近收藏
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	List<Map<String, Object>> selectByRecentCollect(Map<String, Object> map);
}