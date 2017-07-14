package com.qhtr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.ThumbsUpMapper;
import com.qhtr.model.ThumbsUp;
import com.qhtr.service.ThumbsUpService;

@Service
public class ThumbsUpServiceImpl implements ThumbsUpService {
	@Resource
	public ThumbsUpMapper thumbsUpMapper;
	
	@Override
	public int getIsThumbsUp(int userId, int commentId) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		map.put("userId", userId);
		map.put("commentId", commentId);
		
		List<ThumbsUp> list = thumbsUpMapper.selectByConditions(map);
		
		if(list.isEmpty()){
			// 未点赞
			return 0;
		}else{
			return list.get(0).getId();
		}
	}

}
