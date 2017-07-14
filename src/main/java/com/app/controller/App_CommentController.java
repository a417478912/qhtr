package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.common.comet.Comet;
import com.qhtr.dto.CommentDto;
import com.qhtr.model.Comment;
import com.qhtr.service.CommentService;
import com.qhtr.service.ThumbsUpService;
import com.qhtr.utils.CometSellUtil;
/**
 * 
 * @author Harry
 * @Description 留言相关操作的 Controller
 * @date  2017年6月2日
 */
@Controller
@RequestMapping("/app_comment")
public class App_CommentController {
	
	@Resource
	public CommentService commentService;
	@Resource
	public ThumbsUpService thumbsUpService;

	/**
	 * 获取留言列表
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommentList")//,method=RequestMethod.POST
	public Json getCommentList(Json j, @RequestParam int storeId,@RequestParam int userId) {
		
		List<Comment> list = commentService.selectCommentListByStoreId2(storeId);
		List<CommentDto> dtoList = new ArrayList<>();
		if (!list.isEmpty()) {
			
			for (Comment comment : list) {
				
				CommentDto commentDto = new CommentDto(comment);
				
				int commentId = comment.getId();
				int isThumbsUp = thumbsUpService.getIsThumbsUp(userId,commentId);
				if (isThumbsUp == 0) {
					
					commentDto.setIsThumbsUp(0);
				}else{
					
					commentDto.setIsThumbsUp(1);
				}
				dtoList.add(commentDto);
			}
		}
		
		j.setData(dtoList);
		return j;
	}

	/**
	 * 增加留言
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
	 * 通过留言id查询,下级回复
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
	 * 查询是否已赞
	 * @param j
	 * @param userId
	 * @param commentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isThumbsUp")
	public Json isThumbsUp(Json j,@RequestParam int userId,@RequestParam int commentId){
		
		int result = thumbsUpService.getIsThumbsUp(userId,commentId);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(result != 0){
			// 已点赞
			map.put("isThumbsUp", 1);
		}else{
			// 未点赞
			map.put("isThumbsUp", 0);
		}
		j.setData(map);
		return j;
	}
	
	/**
	 * 留言板点赞
	 * @param j
	 * @param commentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/thumbsUp")
	public Json thumbsUp(Json j, @RequestParam int commentId,@RequestParam int userId) {
		
		int isThumbsUp = thumbsUpService.getIsThumbsUp(userId,commentId);
		
		if (isThumbsUp == 0) {
			
			int result = commentService.updateUpvote(commentId,userId);
			if (result == 1) {
				
				j.setMessage("点赞成功!");
			} else {
				j.setCode(0);
				j.setMessage("点赞失败!");
			}
			return j;
		}else{
			
			j.setCode(0);
			j.setMessage("您已点赞 !");
			return j;
		}
	}
	
	
	/**
	 * 取消赞
	 * @param j
	 * @param commentId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cancelThumbsUp")
	public Json cancelThumbsUp(Json j,@RequestParam int commentId ,@RequestParam int userId){
		
		int result = commentService.cancelThumbsUp(commentId,userId);
		
		if (result == 1) {

			j.setMessage("取消成功 !");
			return j;
		}else{
			
			j.setMessage("取消失败 !");
			j.setCode(0);
			return j;
		}
	}
}
