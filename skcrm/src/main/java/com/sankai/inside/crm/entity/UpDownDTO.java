package com.sankai.inside.crm.entity;

public class UpDownDTO {
	private Integer order;
	private Integer pid;
	private Integer id;
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UpDownDTO [order=" + order + ", pid=" + pid + ", id=" + id + "]";
	}
	
	
}
