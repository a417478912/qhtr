package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.WithdrawMapper;
import com.qhtr.dto.WithdrawApplyListDto;
import com.qhtr.model.Withdraw;
import com.qhtr.service.WithdrawService;

@Service
public class WithdrawServiceImpl implements WithdrawService {
	@Resource
	public WithdrawMapper withdrawMapper;
	
	@Override
	public List<WithdrawApplyListDto> selectDtoListByAdmin(Map<String,String> map) {
		return withdrawMapper.selectDtoListByAdmin(map);
	}

	@Override
	public WithdrawApplyListDto selectDtoByWithdrawId(Map<String,String> map) {
		List<WithdrawApplyListDto> list = withdrawMapper.selectDtoListByAdmin(map);
		return list.isEmpty() == true ? null:list.get(0);
	}

	@Override
	public int updateApplyResult(int id, int type, String reason) {
		Withdraw withdraw = withdrawMapper.selectByPrimaryKey(id);
		if(withdraw.getStatus() == 1){
			if(type == 1){
				withdraw.setStatus(2);
			}else{
				withdraw.setStatus(3);
			}
			withdraw.setReason(reason);
			withdraw.setTransferAccountsTime(new Date());
			withdrawMapper.updateByPrimaryKey(withdraw);
			return 1;
		}else{
			return -1;
		}
	}

}
