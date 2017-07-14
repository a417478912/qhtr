package com.qhtr.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;

public class UserMessage {

    private Integer id;

    private Integer userId;

    private String messageContent;

    @JsonSerialize(using=CustomDateSerializer.class)
    private String createTime;

    /**
     * type = 1 : 店铺,
     * type = 2 : 商品
     */
    private Integer type;

    private Integer linkId;

    private String storeAvatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getStoreAvatar() {
        return storeAvatar;
    }

    public void setStoreAvatar(String storeAvatar) {
        this.storeAvatar = storeAvatar == null ? null : storeAvatar.trim();
    }
}