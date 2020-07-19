package com.sankai.inside.crm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerRemindAdd  {

	private Integer customerId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date remindTime;
	private String remark;//备注
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
		
}
