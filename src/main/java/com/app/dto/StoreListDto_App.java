package com.app.dto;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhtr.model.Store;

public class StoreListDto_App {
	public int id;
	public String picture = "";
	public String name = "";
	public int sellNum = 0;
	public String introduction = "";//纯文字简介/取详情中的第一段文字
	
	public StoreListDto_App(){
	}
	
	public StoreListDto_App(Store store){
		this.id = store.getId();
		this.picture = store.getAvatar();
		this.name = store.getName();
		this.sellNum = store.getSellNum();
		String detailsStr = store.getDetails();
		if(StringUtils.isNotBlank(detailsStr)){
			JSONArray jArray = JSONArray.parseArray(detailsStr);
			for (int i = 0; i < jArray.size(); i++) {
				JSONObject jObj = jArray.getJSONObject(i);
				Object theContent = jObj.get("type");
				if(theContent != null && theContent.toString().equals("text")){
					this.introduction = jObj.get("content").toString();
					break;
				}
			}
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSellNum() {
		return sellNum;
	}

	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
