package com.sankai.inside.crm.entity;

import java.util.Date;


public class CustomerLogList {

	private Integer id;
	private Integer type;
	private String accountNames;
	private String customerName;
	private String createName;
	private Date createTime;
	private Integer state;
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAccountNames() {
		return accountNames;
	}
	public void setAccountNames(String accountNames) {
		this.accountNames = accountNames;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
