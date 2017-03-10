package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //自定义方法
    List<User> selectByConditions(User user);
}