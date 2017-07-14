package com.sell.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Comment;
import com.qhtr.model.Store;
import com.qhtr.model.User;
import com.qhtr.service.CommentService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UserService;
import com.sell.dto.CommentDto;

@Controller
@RequestMapping("/sell_comment")
public class Sell_CommentController {
	@Resource
	public CommentService commentService;
	@Resource
	public UserService userService;
	
	
	/**
	 * 获取留言列表
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCommentList")
	public Json getCommentList(Json j,@RequestParam int storeId){
		List<CommentDto> list = commentService.selectCommentListByStoreId(storeId);
		j.setData(list);
		return j;
	}
	
	/**
	 * 增加留言
	 * @param j
	 * @param comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Json add(Json j,Comment comment){
		int result = commentService.add(comment);
		if(result == 1){
			j.setMessage("评论成功!");
		}else{
			j.setCode(0);
			j.setMessage("评论失败!");
		}
		return j;
	}
	@Resource
	private StoreService storeService;
	/**
	 *  通过留言id查询下级回复
	 */
	@ResponseBody
	@RequestMapping(value="/getReplyList")
	public Json getReplyList(Json j,@RequestParam int commentId){
		
		List<Comment> list = commentService.getReplyListByCommentId(commentId);
		/*// 获取用户昵称
		for (Comment comment : list) {
			
			Integer userId = comment.getUserId();
			User user = userService.selectByPrimaryKey(userId);
			String nickName = user.getNickName();
		}*/
		List<Comment> list1 = new ArrayList<Comment>();
		list1.add(commentService.getByCommentId(commentId));
		list1.addAll(list);
		if (!list1.isEmpty()) {
			
			for (Comment comment : list1) {
				
				Integer storeId = comment.getStoreId();
				Store store = storeService.selectStoreById(storeId);
				if (store != null) {
					
					if (store.getName() != null && !"".equals(store.getName())) {
						
						comment.setStoreName(store.getName());
					}else{
						comment.setStoreName("");
					}
				}
				if (comment.getStoreName() == null || "".equals(comment.getStoreName())) {
					comment.setStoreName("");
				}
			}
		}
		j.setData(list1);
		return j;
	}
}
