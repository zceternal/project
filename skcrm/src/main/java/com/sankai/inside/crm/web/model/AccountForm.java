package com.sankai.inside.crm.web.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Administrator
 *
 */
public class AccountForm {
	
	private int id = 0;
	//@NotEmpty(message="{accountForm.loginName.empty}",groups=ValidAdd.class)
	//@Length(max=50,message="{Length}",groups=ValidAdd.class)
	private String loginName;
	
	private String newPassword;
	
	private String name;
	
	private int sex;
	
	private String phone;
	
	private String email;
	
	private int deptId;
	
	private String avatar;//客户头像
	
	private String recordType;
	
	

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private String number;
	
	private int isDeptManager;
	

	public int getIsDeptManager() {
		return isDeptManager;
	}

	public void setIsDeptManager(int isDeptManager) {
		this.isDeptManager = isDeptManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	
	
}
