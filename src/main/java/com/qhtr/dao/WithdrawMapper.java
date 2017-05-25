package com.qhtr.dao;

import java.util.List;
import java.util.Map;

import com.qhtr.dto.WithdrawApplyListDto;
import com.qhtr.model.Withdraw;

public interface WithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Withdraw record);

    int insertSelective(Withdraw record);

    Withdraw selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Withdraw record);

    int updateByPrimaryKey(Withdraw record);
    
    
    //自定义方法
    /**
     * 后台管理查询提现申请列表
     * @return
     */
	List<WithdrawApplyListDto> selectDtoListByAdmin(Map<String,String> map);
}