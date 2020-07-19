package com.sankai.inside.crm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户提醒
 * @author Zzc
 *
 */
public class CustomerRemindDTO  {

	private Integer id;
	private Integer customerId;
	private Date createTime;
	private Integer createId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date remindTime;
	private Integer type;
	private Integer state;
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
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
		
}
