package com.sankai.inside.crm.entity;
/**
 * 点赞用
 * @author cgq
 *
 */
public class LikeItDTO {
	private Integer loginId;
	private Integer recordId;
	
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	@Override
	public String toString() {
		return "LikeItDTO [loginId=" + loginId + ", recordId=" + recordId + "]";
	}
	
	

}
