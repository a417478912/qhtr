package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Comment;
import com.qhtr.service.CommentService;
import com.sell.dto.CommentDto;

@Controller
@RequestMapping("/app_comment")
public class App_CommentController {
	@Resource
	public CommentService commentService;

	/**
	 * 获取留言列表
	 * 
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommentList")
	public Json getCommentList(Json j, @RequestParam int storeId) {
		List<CommentDto> list = commentService.selectCommentListByStoreId(storeId);
		j.setData(list);
		return j;
	}

	/**
	 * 增加留言
	 * 
	 * @param j
	 * @param comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add")
	public Json add(Json j, Comment comment) {
		int result = commentService.add(comment);
		if (result == 1) {
			j.setMessage("评论成功!");
		} else {
			j.setCode(0);
			j.setMessage("评论失败!");
		}
		return j;
	}

	/**
	 * 通过留言id查询下级回复
	 */
	@ResponseBody
	@RequestMapping(value = "/getReplyList")
	public Json getReplyList(Json j, @RequestParam int commentId) {
		List<Comment> list = commentService.getReplyListByCommentId(commentId);
		List<Comment> list1 = new ArrayList<Comment>();
		list1.add(commentService.getByCommentId(commentId));
		list1.addAll(list);
		j.setData(list1);
		return j;
	}

	/**
	 * 点赞
	 * 
	 * @param j
	 * @param commentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upvote")
	public Json upvote(Json j, @RequestParam int commentId) {
		int result = commentService.updateUpvote(commentId);
		if (result == 1) {
			j.setMessage("点赞成功!");
		} else {
			j.setCode(0);
			j.setMessage("点赞失败!");
		}
		return j;
	}
}
