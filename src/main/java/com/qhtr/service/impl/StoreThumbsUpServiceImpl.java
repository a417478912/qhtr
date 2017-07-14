package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.StoreThumbsUpMapper;
import com.qhtr.model.StoreThumbsUp;
import com.qhtr.service.StoreThumbsUpService;
@Service
public class StoreThumbsUpServiceImpl implements StoreThumbsUpService{

	@Resource
	private StoreThumbsUpMapper stum;
	@Override
	public int isThumbsUp(int storeId, int userId) {
		
		StoreThumbsUp stu = new StoreThumbsUp(storeId,userId);
		StoreThumbsUp stu1 = stum.isThumbsUp(stu);
		if (stu1 == null) {
			return 0;
		}else{
			return 1;
		}
	}
	@Override
	public int storeThumbsUp(int userId, int storeId) {
		try {
			StoreThumbsUp stu = new StoreThumbsUp(storeId,userId);
			stum.thumbsUp(stu);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int delThumbsUp(int userId, int storeId) {
		try {
			
			StoreThumbsUp stu = new StoreThumbsUp(storeId,userId);
			stum.delThumbsUp(stu);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int countThumbsUp(int storeId) {
		
		return stum.countThumbsUp(storeId);
	}

}
