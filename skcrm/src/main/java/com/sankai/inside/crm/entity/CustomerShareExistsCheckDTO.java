package com.sankai.inside.crm.entity;

public class CustomerShareExistsCheckDTO {
	private Integer customerId;
	private Integer allowAccountId;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(Integer allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
	@Override
	public String toString() {
		return "CustomerShareExistsCheckDTO [customerId=" + customerId + ", allowAccountId=" + allowAccountId + "]";
	}
	

}
