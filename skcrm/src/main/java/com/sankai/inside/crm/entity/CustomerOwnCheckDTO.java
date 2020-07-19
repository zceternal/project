package com.sankai.inside.crm.entity;
/**
 * 查看这个人是不是客户的第一负责人
 * @author cgq
 *
 */
public class CustomerOwnCheckDTO {
	private Integer customerId;
	private Integer loginId;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	@Override
	public String toString() {
		return "CustomerOwnCheckDTO [customerId=" + customerId + ", loginId=" + loginId + "]";
	}
}
