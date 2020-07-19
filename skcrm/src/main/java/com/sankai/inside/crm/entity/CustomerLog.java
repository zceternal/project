package com.sankai.inside.crm.entity;

import java.util.Date;

/**
 * 客户管理日志表
 * @author Zzc
 *
 */
public class CustomerLog {

	private Integer id;
	private Integer customerId;
	private Integer type;//0:新增;1:共享;2:转移;-1:删除
	private String accountIds;//用户id集合
	private Date createTime;
	private Integer createId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAccountIds() {
		return accountIds;
	}
	public void setAccountIds(String accountIds) {
		this.accountIds = accountIds;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
}
