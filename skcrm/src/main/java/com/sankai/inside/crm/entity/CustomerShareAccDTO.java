package com.sankai.inside.crm.entity;

public class CustomerShareAccDTO {
	private Integer accountId;
	private String accountName;
	private Integer deptId;
	
	
	
	public CustomerShareAccDTO() {
		super();
	}
	public CustomerShareAccDTO(Integer accountId, String accountName, Integer deptId) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.deptId = deptId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Override
	public String toString() {
		return "CustomerShareAccDTO [accountId=" + accountId + ", accountName=" + accountName + ", deptId=" + deptId
				+ "]";
	}
	
	
	
	
	
	
}
