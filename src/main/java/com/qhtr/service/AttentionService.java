package com.qhtr.service;

import java.util.List;
import java.util.Map;

public interface AttentionService {
	
	/**
	 * 获取关注的店铺列表
	 * @param userId
	 * @return
	 */
	List<Map<String,Object>> getAttentionList(int userId);
	/**
	 * 添加关注
	 * @param userId
	 * @param storeId
	 * @return
	 */
	int addAttention(int userId, int storeId);
	/**
	 * 删除关注
	 * @param attentionId
	 * @return
	 */
	int deleteAttention(int attentionId);

}
