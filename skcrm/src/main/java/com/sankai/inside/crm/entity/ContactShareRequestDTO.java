package com.sankai.inside.crm.entity;
/**
 * 获取共享联系人的时候用
 * @author cgq
 *
 */
public class ContactShareRequestDTO {
	/**
	 * 登录ID
	 */
	private Integer loginId;
	/**
	 * 客户ID
	 */
	private Integer customerId;
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "ContactShareRequestDTO [loginId=" + loginId + ", customerId=" + customerId + "]";
	}
	
	

}
