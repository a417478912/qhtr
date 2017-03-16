package com.qhtr.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String phone;

    private String password;

    private String sex;

    private String avatar;

    private Integer status;

    private Date createTime;

    private String string1;

    private String string2;

    private String string3;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1 == null ? null : string1.trim();
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2 == null ? null : string2.trim();
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3 == null ? null : string3.trim();
    }
}