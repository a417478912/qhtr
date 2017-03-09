package com.qhtr.dao;

import com.qhtr.model.AttrTemplate;

public interface AttrTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AttrTemplate record);

    int insertSelective(AttrTemplate record);

    AttrTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttrTemplate record);

    int updateByPrimaryKey(AttrTemplate record);
}