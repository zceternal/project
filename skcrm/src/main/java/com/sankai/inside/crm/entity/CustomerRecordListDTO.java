package com.sankai.inside.crm.entity;

import java.util.Date;

/**
 * 查询销售跟踪记录列表
 * @author cgq
 *
 */
public class CustomerRecordListDTO {
	private Integer loginId;//当前登录ID
	private Integer customerId;
	private Integer accountId;
	private Integer typeId;//记录状态id
	private boolean isDeptLeader;//是否是部门领导
	private Integer state;//用户的状态
	private Date startTime;//冗余开始时间
	private Date endTime;//冗余结束时间
	
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	@Override
	public String toString() {
		return "CustomerRecordListDTO [loginId=" + loginId + ", customerId=" + customerId + ", accountId=" + accountId
				+ "]";
	}
	public boolean isDeptLeader() {
		return isDeptLeader;
	}
	public void setDeptLeader(boolean isDeptLeader) {
		this.isDeptLeader = isDeptLeader;
	}
	
	
}
