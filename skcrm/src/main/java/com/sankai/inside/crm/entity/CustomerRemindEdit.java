package com.sankai.inside.crm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerRemindEdit {

	private Integer id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date remindTime;
	private Integer type;
	private String remark;//备注
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
