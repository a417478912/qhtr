package com.qhtr.service;

public interface ThumbsUpService {
	/**
	 * 是否点赞
	 * @param userId
	 * @param commentId
	 * @return
	 */
	int getIsThumbsUp(int userId, int commentId);

}
