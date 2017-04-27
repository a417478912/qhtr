package com.sell.dto;

import org.apache.commons.lang.StringUtils;
import com.qhtr.model.Category;
import com.qhtr.model.Store;
import com.qhtr.service.CategoryService;
import com.qhtr.utils.ApplicationContextUtils;

public class StoreDto_Sell {
	public int id;
	public String category;
	public String name;
	public String phone;
	public String sex;
	public String age;
	public String otherShop;
	public String avatar;
	public String bannerPic;
	public String promotionPic;
	public String showPic;
	public String details;
	public int collect_num;
	public int sell_num;
	public String location;
	public String longitude;
	public String latitude;
	public int score;
	public int type;

	public StoreDto_Sell() {

	}

	public StoreDto_Sell(Store store) {
		this.setId(store.getId());
		this.setAge(store.getAge());
		this.setSex(store.getSex());
		CategoryService categoryService = (CategoryService) ApplicationContextUtils.getContext()
				.getBean("CategoryService");
		// 行业分类
		if (store.getCategoryId() != null && store.getCategoryId() != 0) {
			Category category = categoryService.getById(store.getCategoryId());
			this.setCategory(category.getName());
		}
		this.setName(store.getName());
		this.setPhone(store.getPhone());
		this.setAvatar(store.getAvatar());
		this.setOtherShop(store.getOtherShop());
		
		this.setPromotionPic(store.getPromotionPic());
		this.setBannerPic(store.getBannerPic());
		this.setShowPic(store.getShowPic());
		this.setDetails(store.getDetails());
		this.setCollect_num(store.getCollectNum());
		this.setSell_num(store.getSellNum());
		this.setLocation(store.getLocation());
		if (StringUtils.isNotBlank(store.getLongitudeLatitude())) {
			this.setLongitude(store.getLongitudeLatitude().split(",")[0]);
			this.setLatitude(store.getLongitudeLatitude().split(",")[1]);
		}
		this.setScore(store.getScore());
		this.setType(store.getType());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getShowPic() {
		return showPic;
	}

	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}

	public int getCollect_num() {
		return collect_num;
	}

	public void setCollect_num(int collect_num) {
		this.collect_num = collect_num;
	}

	public int getSell_num() {
		return sell_num;
	}

	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getOtherShop() {
		return otherShop;
	}

	public void setOtherShop(String otherShop) {
		this.otherShop = otherShop;
	}

	public String getBannerPic() {
		return bannerPic;
	}

	public void setBannerPic(String bannerPic) {
		this.bannerPic = bannerPic;
	}

	public String getPromotionPic() {
		return promotionPic;
	}

	public void setPromotionPic(String promotionPic) {
		this.promotionPic = promotionPic;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
