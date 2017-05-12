package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.AttentionMapper;
import com.qhtr.model.Attention;
import com.qhtr.model.Store;
import com.qhtr.service.AttentionService;

@Service
public class AttentionServiceImpl implements AttentionService {
	@Resource
	public AttentionMapper attentionMapper;
	
	@Override
	public List<Map<String,Object>> getAttentionList(int userId) {
		return attentionMapper.getAttentionList(userId);
	}

	@Override
	public int addAttention(int userId, int storeId) {
		//检查是否已经关注
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("storeId", storeId);
		List<Map<String, Object>> list = attentionMapper.selectByConditions(map);
		if(!list.isEmpty()){
			return -1;
		}
		
		Attention att = new Attention();
		att.setCreateTime(new Date());
		att.setStoreId(storeId);
		att.setUserId(userId);
		return attentionMapper.insert(att);
	}

	@Override
	public int deleteAttention(int attentionId) {
		return attentionMapper.deleteByPrimaryKey(attentionId);
	}

}
