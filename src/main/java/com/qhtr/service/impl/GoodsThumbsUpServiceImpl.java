package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.GoodsThumbsUpMapper;
import com.qhtr.model.GoodsThumbsUp;
import com.qhtr.service.GoodsThumbsUpService;
@Service
public class GoodsThumbsUpServiceImpl implements GoodsThumbsUpService{
	
	@Resource
	private GoodsThumbsUpMapper gtum;

	@Override
	public int goodsThumbsUp(int userId, int goodsId) {
		
		try {
			GoodsThumbsUp gtu = new GoodsThumbsUp(userId,goodsId);
			gtum.thumbsUp(gtu);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int isThumbsUp(int userId, int goodsId) {
		GoodsThumbsUp gtu = new GoodsThumbsUp(userId,goodsId);
		GoodsThumbsUp goodsThumbsUp = gtum.selectGoodsThumbsUpByCondition(gtu);
		
		if (goodsThumbsUp == null) {
			
			return 1;
		}else{
			
			return 0;
		}
	}

	@Override
	public int delThumbsUp(int userId, int goodsId) {
		try {
			
			GoodsThumbsUp gtu = new GoodsThumbsUp(userId,goodsId);
			gtum.delThumbsUp(gtu);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int countThumbsUp(int goodsId) {
		
		return gtum.countThumbsUp(goodsId);
	}

}
