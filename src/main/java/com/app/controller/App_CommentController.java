package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.common.comet.CacheManager;
import com.qhtr.common.comet.Comet;
import com.qhtr.model.Comment;
import com.qhtr.service.CommentService;
import com.qhtr.service.ThumbsUpService;
import com.qhtr.utils.CometSellUtil;
import com.sell.dto.CommentDto;
@Controller
@RequestMapping("/app_comment")
public class App_CommentController {
	@Resource
	public CommentService commentService;
	@Resource
	public ThumbsUpService thumbsUpService;

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
			
			//留言消息推送
			Comet comet = new Comet();
			comet.setStoreId(comment.getStoreId() + "");//id为1的  推送
			comet.setMsgCount(String.valueOf("您有新的留言!"));
			new CometSellUtil().pushToStore(comet);
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
	
	public Json isThumbsUp(Json j,@RequestParam int userId,@RequestParam int commentId){
		int result = thumbsUpService.getIsThumbsUp(userId,commentId);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(result != 0){
			map.put("isThumbsUp", 1);
		}else{
			map.put("isThumbsUp", 0);
		}
		j.setData(map);
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
	@RequestMapping(value = "/thumbsUp")
	public Json thumbsUp(Json j, @RequestParam int commentId) {
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
