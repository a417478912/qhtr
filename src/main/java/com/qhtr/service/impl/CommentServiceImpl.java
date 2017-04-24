package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CommentMapper;
import com.qhtr.model.Comment;
import com.qhtr.service.CommentService;
import com.sell.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
	@Resource
	public CommentMapper commentMapper;

	@Override
	public List<CommentDto> selectCommentListByStoreId(int storeId) {
		return commentMapper.selectCommentListByStoreId(storeId);
	}

	@Override
	public int add(Comment comment) {
		if(comment.getParentId() != null){
			Comment parentCom = commentMapper.selectByPrimaryKey(comment.getParentId());
			if(parentCom.getReplyNum() == null){
				parentCom.setReplyNum(0);
			}else{
				parentCom.setReplyNum(parentCom.getReplyNum() + 1);
			}
			commentMapper.updateByPrimaryKey(parentCom);
		}
		return commentMapper.insert(comment);
	}

	@Override
	public List<Comment> getReplyListByCommentId(int commentId) {
		return commentMapper.getReplyListByCommentId(commentId);
	}

}
