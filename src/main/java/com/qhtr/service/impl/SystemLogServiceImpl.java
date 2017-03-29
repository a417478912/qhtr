package com.qhtr.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.SystemLogMapper;
import com.qhtr.model.SystemLog;
import com.qhtr.service.SystemLogService;

@Service
public class SystemLogServiceImpl implements SystemLogService {
	@Resource
	public SystemLogMapper systemLogMapper;
	
	@Override
	public int add(String name, int operationPeopleId, int type, String operation) {
		SystemLog sl = new SystemLog();
		sl.setName(name);
		sl.setOperationPeopleId(operationPeopleId);
		sl.setType(type);
		sl.setOperation(operation);
		sl.setCreateTime(new Date());
		return systemLogMapper.insert(sl);
	}

}
