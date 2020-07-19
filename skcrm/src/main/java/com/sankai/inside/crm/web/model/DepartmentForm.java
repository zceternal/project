package com.sankai.inside.crm.web.model;

import jxl.write.DateTime;

/**
 * @author Administrator
 *
 */
public class DepartmentForm {
	
	private int id;
	//@NotEmpty(message="{accountForm.loginName.empty}",groups=ValidAdd.class)
	//@Length(max=50,message="{Length}",groups=ValidAdd.class)
	private String remark;
	private int createId;
	private DateTime createTime;
	private int pid;
//	private int order;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCreateId() {
		return createId;
	}
	public void setCreateId(int createId) {
		this.createId = createId;
	}
	public DateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
//	public int getOrder() {
//		return order;
//	}
//	public void setOrder(int order) {
//		this.order = order;
//	}
	

	
}
