package com.qhtr.model;

import java.util.Date;

public class Store {
    private Integer id;

    private Integer categoreId;

    private String name;

    private String phone;

    private String password;

    private String sex;

    private String age;

    private String otherShop;

    private String avatar;
	/**
     * banner轮播图(pictureid)
     */
    private String bannerPic;
	/**
     * 地图上的展示大图(封面图)
     */
    private String showPic;

    private String details;

    private Integer collectNum;

    private Integer sellNum;

    private String location;

    private String longitudeLatitude;
	 /**
     * 0 1  2 - 新店 普通 热销
     */
    private Integer type;

    private Date createTime;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoreId() {
        return categoreId;
    }

    public void setCategoreId(Integer categoreId) {
        this.categoreId = categoreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getOtherShop() {
        return otherShop;
    }

    public void setOtherShop(String otherShop) {
        this.otherShop = otherShop == null ? null : otherShop.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic == null ? null : bannerPic.trim();
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLongitudeLatitude() {
        return longitudeLatitude;
    }

    public void setLongitudeLatitude(String longitudeLatitude) {
        this.longitudeLatitude = longitudeLatitude == null ? null : longitudeLatitude.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}