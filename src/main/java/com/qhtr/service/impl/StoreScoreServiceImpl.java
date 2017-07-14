package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.StoreScoreMapper;
import com.qhtr.model.StoreScore;
import com.qhtr.service.StoreScoreService;

@Service
public class StoreScoreServiceImpl implements StoreScoreService{

	@Resource
	private StoreScoreMapper ssm;
	@Override
	public int addScore(StoreScore ss) {
		
		int result = 1;
		try {
			ssm.addScore(ss);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
			
		}
		return result;
	}
	@Override
	public List<StoreScore> selectScoreByStoreId(StoreScore ss) {
		return ssm.selectScoreByStoreId(ss);
	}

}
