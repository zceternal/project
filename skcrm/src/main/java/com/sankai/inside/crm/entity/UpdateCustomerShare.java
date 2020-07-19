package com.sankai.inside.crm.entity;

import java.util.List;

public class UpdateCustomerShare {

	private Integer customerId;
	private Integer allowAccountId;
	private Integer isShare;
	
	private Integer leaderId;//部门负责人id
	private List<Integer> ids;//客户分享id集合
	
	public Integer getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
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
	public Integer getIsShare() {
		return isShare;
	}
	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
}
