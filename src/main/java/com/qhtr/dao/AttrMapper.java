package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Attr;

public interface AttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attr record);

    int insertSelective(Attr record);

    Attr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attr record);

    int updateByPrimaryKey(Attr record);
    
    //以下是自定义方法
    List<Attr> selectByConditions(Attr attr);
}