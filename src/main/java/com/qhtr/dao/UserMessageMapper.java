package com.qhtr.dao;

import com.qhtr.model.UserMessage;
import com.sun.tools.javac.util.List;

public interface UserMessageMapper {
	
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
     * 添加
     * @param record
     * @return
     */
    int insertSelective(UserMessage record);

    /**
     * 查询
     * @param id
     * @return
     */
    UserMessage selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserMessage record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserMessage record);

    /**
     * 删除所有
     * @param userId
     */
	void deleteMsgAll(int userId);

	/**
	 * 根据用户id查询消息列表
	 * @param userId
	 * @return
	 */
	List<UserMessage> getMsgListByUserId(int userId);
}