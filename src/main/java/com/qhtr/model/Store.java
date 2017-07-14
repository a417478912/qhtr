package com.qhtr.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qhtr.common.CustomDateSerializer;
/**
 * @author Harry
 * @Description 店铺
 * @date  2017年6月5日
 */
public class Store {
	
    private Integer id;

    private Integer categoryId;

    private String name;

    private String phone;

    private String password;

    private String sex;

    private String age;

    private String otherShop;

    private String avatar;

    private String bannerPic;
	/**
     * 地图上的展示大图
     */
    private String showPic;
	/**
	 *  (封面图)
	 */
    private String coverPic;

    private String promotionPic;

    private String details;

    private Integer collectNum;

    private Integer sellNum;

    private String location;

    private String longitudeLatitude;

    private Integer type;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    private Double score;
    
    private List<GoodsPic> goodsThumbs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic == null ? null : coverPic.trim();
    }

    public String getPromotionPic() {
        return promotionPic;
    }

    public void setPromotionPic(String promotionPic) {
        this.promotionPic = promotionPic == null ? null : promotionPic.trim();
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

	public List<GoodsPic> getGoodsThumbs() {
		return goodsThumbs;
	}

	public void setGoodsThumbs(List<GoodsPic> goodsThumbs) {
		this.goodsThumbs = goodsThumbs;
	}

	
}