package com.sankai.inside.crm.entity;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginName;
	private String password;
	private int state;
	private Integer id;
	private int sex;
	private String name;
	
	private String nameSimplePy;
	private String namePy;
	
	private String number;
	private String email;
	private String phone;
	
	private int deptId;

	private Date createTime;
	
	private int createId;
	private String createName;
	
	private String avatar;
	
	private Integer type;
	
	private int isDeptManager;
	
	private String recordType;

	

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public int getIsDeptManager() {
		return isDeptManager;
	}

	public void setIsDeptManager(int isDeptManager) {
		this.isDeptManager = isDeptManager;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCreateId() {
		return createId;
	}
	public void setCreateId(int createId) {
		this.createId = createId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNameSimplePy() {
		return nameSimplePy;
	}
	public void setNameSimplePy(String nameSimplePy) {
		this.nameSimplePy = nameSimplePy;
	}
	public String getNamePy() {
		return namePy;
	}
	public void setNamePy(String namePy) {
		this.namePy = namePy;
	}

	@Override
	public String toString() {
		return "Account [loginName=" + loginName + ", password=" + password + ", state=" + state + ", id=" + id
				+ ", sex=" + sex + ", name=" + name + ", nameSimplePy=" + nameSimplePy + ", namePy=" + namePy
				+ ", number=" + number + ", email=" + email + ", phone=" + phone + ", deptId=" + deptId
				+ ", createTime=" + createTime + ", createId=" + createId + ", createName=" + createName + ", avatar="
				+ avatar + ", type=" + type + ", isDeptManager=" + isDeptManager + "]";
	}

	
	
	
	
	
}
