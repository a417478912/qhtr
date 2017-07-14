package com.qhtr.service;

import java.util.List;

import com.qhtr.model.StoreScore;

public interface StoreScoreService {

	/**
	 * 插入评分
	 * @param ss
	 * @return
	 */
	int addScore(StoreScore ss);

	/**
	 * 查询评分
	 * @param id
	 * @return
	 */
	List<StoreScore> selectScoreByStoreId(StoreScore ss);

}
