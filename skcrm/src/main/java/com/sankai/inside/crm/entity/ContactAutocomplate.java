package com.sankai.inside.crm.entity;

import java.util.Date;


public class ContactAutocomplate {

	private String name;
	private String phone;
	private String createName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@Override
	public String toString() {
		return "CustomerAutocomplate [name=" + name + ", phone=" + phone + ", createName=" + createName + "]";
	}
}
