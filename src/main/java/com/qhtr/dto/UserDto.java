package com.qhtr.dto;

import com.qhtr.model.User;

public class UserDto {
	public Integer id;
	public String name = "";
	public String nickName = "";
	public String phone;
	public String sex = "";
	public String avatar = "";
	public String birthday = "";
	public String qqCode = "";
	public String weixinCode = "";
	public String sinaCode = "";
	public Integer totalMoney = 0;
	
	public UserDto(){
		
	}
	
	public UserDto(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.nickName = user.getNickName();
		this.phone = user.getPhone();
		this.sex = user.getSex();
		this.avatar = user.getAvatar();
		this.birthday = user.getBirthday();
		this.qqCode = user.getQqCode();
		this.weixinCode = user.getWeixinCode();
		this.sinaCode = user.getSinaCode();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getQqCode() {
		return qqCode;
	}
	public void setQqCode(String qqCode) {
		this.qqCode = qqCode;
	}
	public String getWeixinCode() {
		return weixinCode;
	}
	public void setWeixinCode(String weixinCode) {
		this.weixinCode = weixinCode;
	}
	public String getSinaCode() {
		return sinaCode;
	}
	public void setSinaCode(String sinaCode) {
		this.sinaCode = sinaCode;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
}
