package com.qhtr.service;

import java.util.List;
import java.util.Map;

import com.qhtr.dto.WithdrawApplyListDto;

public interface WithdrawService {
	/**
	 * 后台管理查询提现申请
	 * @return
	 */
	List<WithdrawApplyListDto> selectDtoListByAdmin(Map<String,String> map);
	/**
	 * 通过id查询 申请内容
	 */
	WithdrawApplyListDto selectDtoByWithdrawId(Map<String,String> map);
	/**
	 * 申请处理结果
	 * @param id
	 * @param type
	 * @param reason
	 */
	int updateApplyResult(int id, int type, String reason);

}
