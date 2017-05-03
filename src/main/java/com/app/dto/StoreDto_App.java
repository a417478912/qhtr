package com.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhtr.dto.LinkDto;
import com.qhtr.dto.PictureDto;
import com.qhtr.model.Category;
import com.qhtr.model.Store;
import com.qhtr.service.CategoryService;
import com.qhtr.utils.ApplicationContextUtils;

public class StoreDto_App {
	public int id;
	public String category;
	public String name;
	public String phone;
	public String sex;
	public String age;
	public String otherShop;
	public String avatar;
	public List<PictureDto> promotionPic;
	public List<PictureDto> bannerPic;
	public String showPic;
	public List<Map<String,Object>> details;
	public String details1;
	public int collect_num;
	public int sell_num;
	public String location;
	public String longitude;
	public String latitude;
	public int score;
	public int type;

	public StoreDto_App() {

	}

	public StoreDto_App(Store store) {
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
		
		//banner图
		List<PictureDto> picList = new ArrayList<PictureDto>();
		String bannerStr = store.getBannerPic();
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
		
		//促销图  promotionPic
 		List<PictureDto> picList1 = new ArrayList<PictureDto>();
		String promotionStr = store.getPromotionPic();
		if (StringUtils.isNotBlank(promotionStr)) {
			JSONArray jArray = JSONArray.parseArray(promotionStr);
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
				
				picList1.add(picDto);
			}
		}
		this.setPromotionPic(picList1);
		
		// 详情，简介
		if (store.getDetails() != null) {
			JSONArray jArray = JSONArray.parseArray(store.getDetails());
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < jArray.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject jObj = jArray.getJSONObject(i);
				Object type = jObj.get("type");
				Object content = jObj.get("content");
				Object url = jObj.get("url");
				map.put("type", type);
				if(content != null){
					map.put("content", content);
				}
				if(url != null){
					map.put("url", url);
				}
				

				mapList.add(map);
			}
			this.setDetails(mapList);
		}
		
		this.setDetails1(store.getDetails());
		this.setBannerPic(picList);
		this.setShowPic(store.getShowPic());
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

	public List<PictureDto> getBannerPic() {
		return bannerPic;
	}

	public void setBannerPic(List<PictureDto> bannerPic) {
		this.bannerPic = bannerPic;
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

	public List<PictureDto> getPromotionPic() {
		return promotionPic;
	}

	public void setPromotionPic(List<PictureDto> promotionPic) {
		this.promotionPic = promotionPic;
	}

	public void setDetails(List<Map<String, Object>> details) {
		this.details = details;
	}

	public String getDetails1() {
		return details1;
	}

	public void setDetails1(String details1) {
		this.details1 = details1;
	}

	public List<Map<String, Object>> getDetails() {
		return details;
	}
}
