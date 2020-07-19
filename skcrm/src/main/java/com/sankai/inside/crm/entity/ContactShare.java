package com.sankai.inside.crm.entity;

import java.util.Date;
import java.util.List;

public class ContactShare {

	/**
	 * 主键
	 */
	private int id;
	/**
	 * 联系人Id
	 */
	private int contactId;
	/**
	 * 谁操作分享联系人的（当前登录人）
	 */
	private int accountId;
	/**
	 * 分享给谁的Id
	 */
	private int allowAccountId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 联系人关联的客户Id
	 */
	private int customerId;
	private Integer leaderId;//部门负责人id
	private List<Integer> ids;//客户分享id集合
	/**
	 * 状态:0正常；1公海
	 */
	private int state;
	
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(int allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
