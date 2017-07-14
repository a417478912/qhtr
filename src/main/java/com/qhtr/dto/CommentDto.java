package com.qhtr.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;
import com.qhtr.model.Comment;

public class CommentDto {

	private Integer id;

    private String content;
    
    private Integer userId;

    private Integer storeId;
    
    private String storeName;

    private Integer parentId;

    private Integer istop;
    
    @JsonSerialize(using = CustomDateSerializer.class)  
    private Date createTime;

    private String avatar;

    private Integer replyNum = 0;

    private Integer upvoteNum = 0;
    
    private Integer isThumbsUp;
    
	public CommentDto() {
		super();
	}

	public CommentDto(Comment comment) {
		
		this.id = comment.getId();
		this.content = comment.getContent();
		this.userId = comment.getUserId();
		this.storeId = comment.getStoreId();
		this.storeName = comment.getStoreName();
		this.parentId = comment.getParentId();
		this.istop = comment.getIstop();
		this.createTime = comment.getCreateTime();
		this.avatar = comment.getAvatar();
		this.replyNum = comment.getReplyNum();
		this.upvoteNum = comment.getUpvoteNum();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIstop() {
		return istop;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Integer getUpvoteNum() {
		return upvoteNum;
	}

	public void setUpvoteNum(Integer upvoteNum) {
		this.upvoteNum = upvoteNum;
	}

	public Integer getIsThumbsUp() {
		return isThumbsUp;
	}

	public void setIsThumbsUp(Integer isThumbsUp) {
		this.isThumbsUp = isThumbsUp;
	}
}
