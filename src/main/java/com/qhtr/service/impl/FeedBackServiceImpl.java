package com.qhtr.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.UserFeedbackMapper;
import com.qhtr.model.UserFeedback;
import com.qhtr.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{
	@Resource
	public UserFeedbackMapper userFeedbackMapper;
	@Override
	public int insert(int userId,String content) {
		UserFeedback uf = new UserFeedback();
		uf.setContent(content);
		uf.setCreateTime(new Date());
		uf.setUserId(userId);
		return userFeedbackMapper.insert(uf);
	}

}
