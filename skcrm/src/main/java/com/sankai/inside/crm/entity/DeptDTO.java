package com.sankai.inside.crm.entity;

public class DeptDTO {
	private Integer deptId;
	private String deptName;
	
	
	
	public DeptDTO() {
		super();
	}
	public DeptDTO(Integer deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
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
		return "DeptDTO [deptId=" + deptId + ", deptName=" + deptName + "]";
	}
	
	
	
	

}
