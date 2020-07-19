package com.sankai.inside.crm.entity;
/**
 * 部门和部门领导人信息
 * @author Zzc
 * 20160627
 */
public class DeptAndManagerDTO {

	private Integer managerId;
	private Integer deptPId;
	private Integer deptId;
	private String deptName;
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getDeptPId() {
		return deptPId;
	}
	public void setDeptPId(Integer deptPId) {
		this.deptPId = deptPId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "DeptAndManagerDTO [managerId=" + managerId + ", deptPId=" + deptPId + ", deptId=" + deptId
				+ ", deptName=" + deptName + "]";
	}
	
	
	

}
