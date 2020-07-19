package com.sankai.inside.crm.entity;
/**
 * 用于上次跟踪时间
 * @author cgq
 *
 */
public class LastTimeDTO {
	private Integer accountId;
	private Integer customerId;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "LastTimeDTO [accountId=" + accountId + ", customerId=" + customerId + "]";
	}
	
	
}
