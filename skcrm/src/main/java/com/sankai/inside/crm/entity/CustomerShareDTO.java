package com.sankai.inside.crm.entity;

import java.util.Date;
import java.util.List;

public class CustomerShareDTO {
	private int id;
	private int customerId;
	private int accountId;
	private int allowAccountId;
	private Date createTime;
	private int order;
	private int isOwn;
	private int state;
	private int isShare;//是否共享销售跟踪记录
	private int isForm;//客户来源
	
	
	public int getIsShare() {
		return isShare;
	}
	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(int allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(int isOwn) {
		this.isOwn = isOwn;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public int getIsForm() {
		return isForm;
	}
	public void setIsForm(int isForm) {
		this.isForm = isForm;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CustomerShareDTO [customerId=" + customerId + ", accountId=" + accountId + ", allowAccountId="
				+ allowAccountId + ", createTime=" + createTime + ", order=" + order + ", isOwn=" + isOwn + ", state="
				+ state + "]";
	}
	
}
