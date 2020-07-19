package com.sankai.inside.crm.entity;

public class CustomerShareTransDTO {
	private Integer id;
	private Integer allowAccountId;
	private String isOwn;
	private Integer state;
	
	//扩展
	private String allowName;//客户负责人的名字
	private String phone;//电话
	private String deptName;//部门名称
	private Integer deptPid;//部门父节点
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(Integer allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
	public String getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(String isOwn) {
		this.isOwn = isOwn;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAllowName() {
		return allowName;
	}
	public void setAllowName(String allowName) {
		this.allowName = allowName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getDeptPid() {
		return deptPid;
	}
	public void setDeptPid(Integer deptPid) {
		this.deptPid = deptPid;
	}
	@Override
	public String toString() {
		return "CustomerShare [id=" + id + ", allowAccountId=" + allowAccountId + ", isOwn=" + isOwn + ", state="
				+ state + ", allowName=" + allowName + ", phone=" + phone + ", deptName=" + deptName + ", deptPid="
				+ deptPid + "]";
	}
	
}
