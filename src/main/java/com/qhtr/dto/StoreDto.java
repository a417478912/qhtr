package com.qhtr.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhtr.model.Category;
import com.qhtr.model.Store;
import com.qhtr.service.CategoryService;
import com.qhtr.service.impl.CategoryServiceImpl;
import com.qhtr.utils.ApplicationContextUtils;

public class StoreDto {
	public int id;
	public String category;
	public String name;
	public String phone;
	public String avatar;
	public List<PictureDto> bannerPic;
	public String showPic;
	public String detail;
	public int collect_num;
	public int sell_num;
	public String location;
	public String longitude;
	public String latitude;
	public int score;
	public int type;

	public StoreDto() {

	}

	public StoreDto(Store store) {
		this.setId(store.getId());

		CategoryService categoryService = (CategoryService) ApplicationContextUtils.getContext()
				.getBean("CategoryService");
		// 行业分类
		if (store.getCategoreId() != null && store.getCategoreId() != 0) {
			Category category = categoryService.getById(store.getCategoreId());
			this.setCategory(category.getName());
		}
		this.setName(store.getName());
		this.setPhone(store.getPhone());
		this.setAvatar(store.getAvatar());

		List<PictureDto> picList = new ArrayList<PictureDto>();
		String bannerStr = store.getPicture1();
		if (StringUtils.isNotBlank(bannerStr)) {
			JSONArray jArray = JSONArray.parseArray(bannerStr);
			for (int i = 0; i < jArray.size(); i++) {
				PictureDto picDto = new PictureDto();
				JSONObject jObj = jArray.getJSONObject(i);
				Object imageURLObj = jObj.get("imageURL");
				Object linkObj = jObj.get("link");

				JSONObject link1 = (JSONObject) JSONObject.parse(linkObj.toString());
				LinkDto linkDto = new LinkDto();
				linkDto.setId(link1.get("id").toString());
				linkDto.setType(link1.get("type").toString());

				picDto.setImageURL(imageURLObj.toString());
				picDto.setLink(linkDto);

				picList.add(picDto);
			}
		}
		this.setBannerPic(picList);
		this.setShowPic(store.getPicture2());
		this.setDetail(store.getDetails());
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public List<PictureDto> getBannerPic() {
		return bannerPic;
	}

	public void setBannerPic(List<PictureDto> bannerPic) {
		this.bannerPic = bannerPic;
	}
}
