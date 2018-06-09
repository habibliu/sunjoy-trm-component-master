package com.sunjoy.trm.master.dao.entity;

import java.util.Date;

import com.sunjoy.common.utils.JsonUtil;
import com.sunjoy.framework.dao.BaseEntity;
/**
 * Student Entity Class
 *
 */
public class Student extends BaseEntity{
	private String code;
	private String name;
	private Date birthDate;
	private Short sex;
	private Integer height;
	private String school;
	private String phone;
	private String address;
	private String image;
	
	private String parentName;
	private Short parentSex;
	private String parentPhone;
	private String parentWx;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Short getParentSex() {
		return parentSex;
	}
	public void setParentSex(Short parentSex) {
		this.parentSex = parentSex;
	}
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	public String getParentWx() {
		return parentWx;
	}
	public void setParentWx(String parentWx) {
		this.parentWx = parentWx;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JsonUtil.toJsonString(this);
	}
}
