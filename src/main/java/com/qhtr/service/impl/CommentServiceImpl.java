package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.CommentMapper;
import com.qhtr.dto.StoreDto;
import com.qhtr.model.Comment;
import com.qhtr.model.User;
import com.qhtr.service.CommentService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UserService;
import com.sell.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
	@Resource
	public CommentMapper commentMapper;
	@Resource
	public StoreService storeService;
	@Resource
	public UserService userService;

	@Override
	public List<CommentDto> selectCommentListByStoreId(int storeId) {
		return commentMapper.selectCommentListByStoreId(storeId);
	}

	@Override
	public int add(Comment comment) {
		//头像
		if(comment.getUserId() == null){
			StoreDto store = storeService.getStoreById(comment.getStoreId());
			comment.setAvatar(store.getAvatar());
		}else{
			User user = userService.getUserById(comment.getUserId());
			comment.setAvatar(user.getAvatar());
		}
		
		comment.setUpvoteNum(0);
		comment.setCreateTime(new Date());
		if(comment.getParentId() != null){
			Comment parentCom = commentMapper.selectByPrimaryKey(comment.getParentId());
			if(parentCom.getReplyNum() == null){
				parentCom.setReplyNum(1);
			}else{
				parentCom.setReplyNum(parentCom.getReplyNum() + 1);
			}
			commentMapper.updateByPrimaryKey(parentCom);
		}else{
			comment.setParentId(0);
			comment.setReplyNum(0);
		}
		return commentMapper.insert(comment);
	}

	@Override
	public List<Comment> getReplyListByCommentId(int commentId) {
		return commentMapper.getReplyListByCommentId(commentId);
	}

	@Override
	public int updateUpvote(int commentId) {
		Comment comment = commentMapper.selectByPrimaryKey(commentId);
		comment.setUpvoteNum(comment.getUpvoteNum() + 1);
		return commentMapper.updateByPrimaryKey(comment);
	}

}
