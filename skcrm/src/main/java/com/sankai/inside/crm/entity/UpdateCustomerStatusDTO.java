package com.sankai.inside.crm.entity;
/**
 * 修改客户状态用
 * @author cgq
 *
 */
public class UpdateCustomerStatusDTO {
	private Integer customerId;
	private Integer status;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UpdateCustomerStatusDTO [customerId=" + customerId + ", status=" + status + "]";
	}
	
}
