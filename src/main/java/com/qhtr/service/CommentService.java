package com.qhtr.service;

import java.util.List;

import com.qhtr.model.Comment;
import com.sell.dto.CommentDto;

public interface CommentService {
	/**
	 * 查询留言
	 * @param storeId
	 * @return
	 */
	List<CommentDto> selectCommentListByStoreId(int storeId);

	int add(Comment comment);
	
	/**
	 * 通过留言id查询下面的回复
	 * @param commentId
	 * @return
	 */
	List<Comment> getReplyListByCommentId(int commentId);

}
