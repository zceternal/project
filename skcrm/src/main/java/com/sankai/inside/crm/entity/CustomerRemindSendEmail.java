package com.sankai.inside.crm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户提醒——发送Email
 * @author Zzc
 *
 */
public class CustomerRemindSendEmail  {

	private Integer remindId;
	private Date remindTime;
	private String customerName;
	private String accountName;
	private String email;
	private String remark;//备注
	
	public Integer getRemindId() {
		return remindId;
	}
	public void setRemindId(Integer remindId) {
		this.remindId = remindId;
	}
	public Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "CustomerRemindSendEmail [remindId=" + remindId + ", customerName=" + customerName + ", accountName="
				+ accountName + ", email=" + email + ", remark=" + remark + ", toString()=" + super.toString() + "]";
	}
}
