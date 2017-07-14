package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.UserMessageMapper;
import com.qhtr.model.UserMessage;
import com.qhtr.service.UserMessageService;
import com.sun.tools.javac.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService{

	@Resource
	private UserMessageMapper userMsgMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		try {
			
			userMsgMapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int insert(UserMessage record) {
		
		try {
			
			userMsgMapper.insert(record);
			return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteBatch(int[] ids) {
		try {
			if (ids.length > 0) {
				
				for (int i : ids) {
					userMsgMapper.deleteByPrimaryKey(i);
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteMsgAll(int userId) {
		
		try {
			
			userMsgMapper.deleteMsgAll(userId);
			return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<UserMessage> getMsgListByUserId(int userId) {
		
		return userMsgMapper.getMsgListByUserId(userId);
	}
}
