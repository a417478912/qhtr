package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.Comment;
import com.sell.dto.CommentDto;

public interface CommentMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    
    /*以下是自定义方法*/
    /**
	 * 查询留言
	 * @param storeId
	 * @return
	 */
	List<CommentDto> selectCommentListByStoreId(int storeId);
	
	/**
	 * 查询留言
	 * @param storeId
	 * @return
	 */
	List<Comment> selectCommentListByStoreId2(int storeId);

	/**
	 * 通过留言id查询下面的回复
	 * @param commentId
	 * @return
	 */
	List<Comment> getReplyListByCommentId(int commentId);
}