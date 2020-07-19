package com.sankai.inside.crm.entity;

public class ImportExcelEntity {
	private String customerName;//客户名称
	private String customerAbbreviation;//客户简称
	private String address;//客户地址
	private String ContactNmae;//联系人姓名
	private String sex;//联系人性别
	private String Phone;//联系方式   可为客户联系方式
	private String createTime;//联系人创建时间  可为客户创建时间
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAbbreviation() {
		return customerAbbreviation;
	}
	public void setCustomerAbbreviation(String customerAbbreviation) {
		this.customerAbbreviation = customerAbbreviation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNmae() {
		return ContactNmae;
	}
	public void setContactNmae(String contactNmae) {
		ContactNmae = contactNmae;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
