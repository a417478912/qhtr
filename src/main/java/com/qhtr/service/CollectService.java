package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.dto.CollectDto;

public interface CollectService {
	/**
	 * 通过用户id查询收藏列表
	 * @param userId
	 * @return
	 */
	List<CollectDto> selectByUserid(int userId);
	/**
	 * 添加收藏
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	int addCollect(int userId, int goodsId);
	/**
	 * 删除收藏
	 * @param userId
	 * @param storeId
	 * @return
	 */
	int deleteCollect(int collectId);
	/**
	 * 最近的收藏
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	List<Map<String, Object>> selectByRecentCollect(int userId, int categoryId);
	/**
	 * 是否收藏
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	int selectIsCollect(int userId, int goodsId);

}
