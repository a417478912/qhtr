package com.qhtr.dao;

import com.qhtr.model.UserFeedback;

public interface UserFeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFeedback record);

    int insertSelective(UserFeedback record);

    UserFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFeedback record);

    int updateByPrimaryKey(UserFeedback record);
}