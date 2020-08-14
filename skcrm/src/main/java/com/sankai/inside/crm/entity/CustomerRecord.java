package com.sankai.inside.crm.entity;

import java.util.Date;

public class CustomerRecord {
	private Integer id;
	private Integer accountId;
	private Integer customerId;
	private Date lastTime;//上次跟踪时间
	private Date createTime;//本次跟踪时间
	private Date communicationTime;//沟通时间 预留
	private Integer type;//记录类别 字典
	private Integer source;//来源：1 PC; 2 手机
	private String remark;//记录内容
	
	private Date finalTime;//没有用。。待删除
	private String isFirst;//是否是第一负责人
	
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String betweenTime;//时间区间
	private String backType;//返回界面类型
	private String communicationWay;//沟通方式

	public String getCommunicationWay() {
		return communicationWay;
	}

	public void setCommunicationWay(String communicationWay) {
		this.communicationWay = communicationWay;
	}

	public String getBackType() {
		return backType;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBetweenTime() {
		return betweenTime;
	}

	public void setBetweenTime(String betweenTime) {
		this.betweenTime = betweenTime;
	}

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

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	@Override
	public String toString() {
		return "CustomerRecord [id=" + id + ", accountId=" + accountId + ", customerId=" + customerId + ", lastTime="
				+ lastTime + ", createTime=" + createTime + ", communicationTime=" + communicationTime + ", type="
				+ type + ", source=" + source + ", remark=" + remark + ", finalTime=" + finalTime + ", isFirst=" + isFirst + "]";
	}

	
}
