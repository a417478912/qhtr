package com.qhtr.service;

import java.util.List;
import java.util.Map;

public interface FundFlowService {
	/**
	 * 添加买家流水
	 * @param userId  用户id
	 * @param type 	  变动类型
	 * @param changeMoney 金额
	 * @param reason  原因
	 * @return
	 */
	int insertByUser(int userId,int type,int changeMoney,String reason);
	/**
	 * 添加卖家流水
	 * @param userId
	 * @param type
	 * @param changeMoney
	 * @param reason
	 * @return
	 */
	int insertByStore(int storeId,int type,int changeMoney,String reason);
	/**
	 * 查询卖家的各种资金数
	 * @param storeId
	 * @return
	 */
	List<Map<String, Object>> selectMoneysByStoreId(int storeId);
}
