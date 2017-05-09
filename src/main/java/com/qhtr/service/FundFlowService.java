package com.qhtr.service;

public interface FundFlowService {
	/**
	 * 买家流水
	 * @param userId  用户id
	 * @param type 	  变动类型
	 * @param changeMoney 金额
	 * @param reason  原因
	 * @return
	 */
	int insertByUser(int userId,int type,int changeMoney,String reason);
	/**
	 * 卖家流水
	 * @param userId
	 * @param type
	 * @param changeMoney
	 * @param reason
	 * @return
	 */
	int insertByStore(int storeId,int type,int changeMoney,String reason);
}
