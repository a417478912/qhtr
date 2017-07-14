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
		
		
		this.name = store.getName();
		this.sellNum = store.getSellNum();
		String detailsStr = store.getDetails();
		if (store.getCoverPic() == null) {
			this.picture = "";
		}
		this.picture = store.getCoverPic();
		
		//图片和介绍  //取的是里面的第一张图片和第一个内容
		if(StringUtils.isNotBlank(detailsStr)){
			JSONArray jArray = JSONArray.parseArray(detailsStr);
			//introduction
			for (int i = 0; i < jArray.size(); i++) {
				JSONObject jObj = jArray.getJSONObject(i);
				Object theContent = jObj.get("type");
				if(theContent != null && theContent.toString().equals("text")){
					this.introduction = jObj.get("content").toString();
					break;
				}
			}
			
			/*//picture
			for (int i = 0; i < jArray.size(); i++) {
				JSONObject jObj = jArray.getJSONObject(i);
				Object theContent = jObj.get("type");
				if(theContent != null && theContent.toString().equals("image")){
					this.picture = jObj.get("url").toString();
					break;
				}
			}*/
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + sellNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreListDto_App other = (StoreListDto_App) obj;
		if (id != other.id)
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (sellNum != other.sellNum)
			return false;
		return true;
	}
	
	
}
