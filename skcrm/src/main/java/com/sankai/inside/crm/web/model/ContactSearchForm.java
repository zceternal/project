package com.sankai.inside.crm.web.model;

public class ContactSearchForm {
	private int role;
	
	private int customerId;
	private String name;
	//联系人详情
	private boolean qq;
	private boolean phone;
	private boolean wechat;
	private boolean email;
	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
	public boolean isQq() {
		return qq;
	}
	public void setQq(boolean qq) {
		this.qq = qq;
	}
	public boolean isPhone() {
		return phone;
	}
	public void setPhone(boolean phone) {
		this.phone = phone;
	}
	public boolean isWechat() {
		return wechat;
	}
	public void setWechat(boolean wechat) {
		this.wechat = wechat;
	}
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
