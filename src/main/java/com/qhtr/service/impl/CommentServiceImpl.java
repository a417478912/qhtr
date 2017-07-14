package com.qhtr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.StoreDto_App;
import com.qhtr.dao.CommentMapper;
import com.qhtr.dao.ThumbsUpMapper;
import com.qhtr.model.Comment;
import com.qhtr.model.ThumbsUp;
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
	public List<Comment> selectCommentListByStoreId2(int storeId) {
		
		return commentMapper.selectCommentListByStoreId2(storeId);
	}

	@Override
	public int add(Comment comment) {
		//头像
		if(comment.getUserId() == null){
			
			StoreDto_App store = storeService.getStoreById(comment.getStoreId());
			comment.setAvatar(store.getAvatar());
		}else{
			
			User user = userService.getUserById(comment.getUserId());
			comment.setAvatar(user.getAvatar());
		}
		
		comment.setUpvoteNum(0);
		comment.setCreateTime(new Date());
		if(comment.getParentId() != null && comment.getParentId() != 0){
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

	@Resource
	private ThumbsUpMapper thumbsUpMapper;
	@Override
	public int updateUpvote(int commentId,int userId) {
		try {
			// 点赞数量增加
			Comment comment = commentMapper.selectByPrimaryKey(commentId);
			comment.setUpvoteNum(comment.getUpvoteNum() + 1);
			
			// 点赞表中添加数据
			ThumbsUp thumbsUp = new ThumbsUp();
			
			thumbsUp.setCommentId(commentId);
			thumbsUp.setUserId(userId);
			thumbsUpMapper.insert(thumbsUp);
			commentMapper.updateByPrimaryKey(comment);
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Comment getByCommentId(int commentId) {
		return commentMapper.selectByPrimaryKey(commentId);
	}

	@Override
	public int cancelThumbsUp(int commentId, int userId) {
		try {
			// 点赞数量减少
			Comment comment = commentMapper.selectByPrimaryKey(commentId);
			comment.setUpvoteNum(comment.getUpvoteNum() - 1);
			commentMapper.updateByPrimaryKey(comment);
			
			// 点赞表中删除数据
			Map<String, Integer> map = new HashMap<>();
			map.put("commentId", commentId);
			map.put("userId", userId);
			List<ThumbsUp> thumbsUpList = thumbsUpMapper.selectByConditions(map);
			if (!thumbsUpList.isEmpty()) {
				
				thumbsUpMapper.deleteByPrimaryKey(thumbsUpList.get(0).getId());
			}else{
				
				return -1;
			}
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
