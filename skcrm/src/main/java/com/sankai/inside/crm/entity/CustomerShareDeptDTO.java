package com.sankai.inside.crm.entity;

import java.util.List;

/**
 * 部门
 * @author cgq
 *
 */
public class CustomerShareDeptDTO {
	private boolean hasNext;
	private List<CustomerShareDeptDTO> depts;
	private List<CustomerShareAccDTO> accounts;
	private Integer size;
	private Integer deptId;
	private String name;
	private Integer accountId;
	private String accountName;
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public List<CustomerShareDeptDTO> getDepts() {
		return depts;
	}
	public void setDepts(List<CustomerShareDeptDTO> depts) {
		this.depts = depts;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
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
	public List<CustomerShareAccDTO> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<CustomerShareAccDTO> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "CustomerShareDeptDTO [hasNext=" + hasNext + ", depts=" + depts + ", accounts=" + accounts + ", size="
				+ size + ", deptId=" + deptId + ", name=" + name + ", accountId=" + accountId + ", accountName="
				+ accountName + "]";
	}
	
	
	
	
	
	
	
}
