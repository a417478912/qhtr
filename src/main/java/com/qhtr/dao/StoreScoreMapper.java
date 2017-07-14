package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.StoreScore;

public interface StoreScoreMapper {
	
	public void addScore(StoreScore ss);

	public List<StoreScore> selectScoreByStoreId(StoreScore ss);
}
