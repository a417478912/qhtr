package com.qhtr.service;

public interface SystemLogService {
	/**
	 * 
	 * @param name 名字
	 * @param operationPeopleId 操作人id
	 * @param type	'l类型  1.用户  2.卖家 。管理员'
	 * @param operation 操作
	 * @return
	 */
	int add(String name,int operationPeopleId,int type,String operation);
}
