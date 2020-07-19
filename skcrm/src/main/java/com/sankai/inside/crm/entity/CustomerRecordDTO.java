package com.sankai.inside.crm.entity;

import java.util.Date;
import java.util.List;

public class CustomerRecordDTO {
	private Integer id;
	private Integer accountId;
	private Integer customerId;
	private Date lastTime;//上次跟踪时间
	private Date createTime;//本次跟踪时间
	private Date communicationTime;//沟通时间 预留
	private Integer type;//记录类别 字典
	private Integer source;//来源：1 PC; 2 手机
	private String remark;//记录内容
	
	private String accountName;//记录人的名字
	private Integer likeQty;//本条记录的点赞数量
	private Integer liked;//是否已经赞过，如果是0非，1是
	private String typeName;//记录类别名称 字典
	private String deptName;
	
	private List<CustomerRecordRevert> listCustomerRecordRevert;
	
	public List<CustomerRecordRevert> getListCustomerRecordRevert() {
		return listCustomerRecordRevert;
	}

	public void setListCustomerRecordRevert(List<CustomerRecordRevert> listCustomerRecordRevert) {
		this.listCustomerRecordRevert = listCustomerRecordRevert;
	}


	private String avatar;
	

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

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCommunicationTime() {
		return communicationTime;
	}

	public void setCommunicationTime(Date communicationTime) {
		this.communicationTime = communicationTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getLikeQty() {
		return likeQty;
	}

	public void setLikeQty(Integer likeQty) {
		this.likeQty = likeQty;
	}

	public Integer getLiked() {
		return liked;
	}

	public void setLiked(Integer liked) {
		this.liked = liked;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	@Override
	public String toString() {
		return "CustomerRecordDTO [id=" + id + ", accountId=" + accountId + ", customerId=" + customerId + ", lastTime="
				+ lastTime + ", createTime=" + createTime + ", communicationTime=" + communicationTime + ", type="
				+ type + ", source=" + source + ", remark=" + remark + ", accountName=" + accountName + ", likeQty="
				+ likeQty + ", liked=" + liked + ", typeName=" + typeName + ", deptName=" + deptName + ", avatar="
				+ avatar + "]";
	}

	
	
}
