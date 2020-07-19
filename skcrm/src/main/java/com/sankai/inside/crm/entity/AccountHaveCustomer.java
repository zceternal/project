package com.sankai.inside.crm.entity;

import java.io.Serializable;

/**
 * 用户拥有客户数量
 * @author Zzc
 * 2016-06-23
 */
public class AccountHaveCustomer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String loginName;
	private Integer id;
	private String name;
	private String number;
	private String email;
	private String phone;
	private Integer deptId;//当前部门id
	private Integer deptPid;//部门父级id
	private Integer managerId;//当前部门领导人id
	private Integer isDeptManager;
	private Integer custoemrCount;//客户总数量
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getIsDeptManager() {
		return isDeptManager;
	}
	public void setIsDeptManager(Integer isDeptManager) {
		this.isDeptManager = isDeptManager;
	}
	public Integer getCustoemrCount() {
		return custoemrCount;
	}
	public void setCustoemrCount(Integer custoemrCount) {
		this.custoemrCount = custoemrCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Integer getDeptPid() {
		return deptPid;
	}
	public void setDeptPid(Integer deptPid) {
		this.deptPid = deptPid;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	@Override
	public String toString() {
		return "AccountHaveCustomer [loginName=" + loginName + ", id=" + id + ", name=" + name + ", number=" + number
				+ ", email=" + email + ", phone=" + phone + ", deptId=" + deptId + ", deptPid=" + deptPid
				+ ", managerId=" + managerId + ", isDeptManager=" + isDeptManager + ", custoemrCount=" + custoemrCount
				+ "]";
	}
	
}
