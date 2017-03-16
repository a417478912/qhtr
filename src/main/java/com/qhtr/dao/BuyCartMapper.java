package com.qhtr.dao;

import java.util.List;

import com.app.dto.BuyCartDto;
import com.qhtr.model.BuyCart;
import com.qhtr.model.Category;

public interface BuyCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyCart record);

    int insertSelective(BuyCart record);

    BuyCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuyCart record);

    int updateByPrimaryKey(BuyCart record);
    
  //自定义方法
    List<BuyCart> selectByConditions(BuyCart record);
    
    List<BuyCartDto> selectCartsByUserId(BuyCart record);
    
	int deleteByIds(String[] ids);
	
}