package com.sankai.inside.crm.entity;

import java.util.List;

public class ContactSearch {
	private List<String> principal;//销售负责人
	private String contactRole;//联系人角色
	//联系人详情
	private boolean isqq;
	private boolean isphone;
	private boolean iswechat;
	private boolean isemail;

	private int isGetValue;
	private String sor;// 跳转来源 1 人际关系弹框；其他属于联系人跳转

	public String getSor() {
		return sor;
	}

	public void setSor(String sor) {
		this.sor = sor;
	}

	public int getIsGetValue() {
		return isGetValue;
	}
	public void setIsGetValue(int isGetValue) {
		this.isGetValue = isGetValue;
	}
	
	private boolean isDeptManaer;//是否是部门领导人
	
	public boolean isDeptManaer() {
		return isDeptManaer;
	}
	public void setDeptManaer(boolean isDeptManaer) {
		this.isDeptManaer = isDeptManaer;
	}
	private String customerType;//客户类型
	private String content;//文本查询条件 
	
	private int accountId;//当前登录人Id
	
	private Integer customerId;//客户Id
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	private String orderField;//排序字段
	private String orderType;//排序类型
	
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public boolean isIsqq() {
		return isqq;
	}
	public void setIsqq(boolean isqq) {
		this.isqq = isqq;
	}
	public boolean isIsphone() {
		return isphone;
	}
	public void setIsphone(boolean isphone) {
		this.isphone = isphone;
	}
	public boolean isIswechat() {
		return iswechat;
	}
	public void setIswechat(boolean iswechat) {
		this.iswechat = iswechat;
	}
	public boolean isIsemail() {
		return isemail;
	}
	public void setIsemail(boolean isemail) {
		this.isemail = isemail;
	}
	
	
	
	
	
	
	
	public List<String> getPrincipal() {
		return principal;
	}
	public void setPrincipal(List<String> principal) {
		this.principal = principal;
	}
	public String getContactRole() {
		return contactRole;
	}
	public void setContactRole(String contactRole) {
		this.contactRole = contactRole;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
