package com.qhtr.service;

import com.qhtr.model.UserMessage;
import com.sun.tools.javac.util.List;

public interface UserMessageService {

	/**
	 * 删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 添加
     * @param record
     * @return
     */
    int insert(UserMessage record);
    
    /**
     * 批量删除
     */
    int deleteBatch(int[] ids);

    /**
     * 删除所有
     * @param userId
     * @return
     */
	int deleteMsgAll(int userId);

	/**
	 * 根据用户id查询消息列表
	 * @param userId
	 * @return
	 */
	List<UserMessage> getMsgListByUserId(int userId);
}
