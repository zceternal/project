package com.sankai.inside.crm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 沟通记录
 */
public class CommunicationForm extends Customer {

	private int userId;// 操作人id
	private String userName;// 操作人名称

	//===================1【客户信息通过继承】====================

	//===================2【客户跟踪记录】====================
	private String reportRemark;//记录内容
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date communicationTime;//沟通日期
	private String communicationWay;//沟通方式

	//===================3【任务信息】====================
	/**客户类型*/
	private String customerType;
	/**现有客户id*/
	private Integer customerId;
	/**公司内部*/
	private String companyInterior;
	/**供应商*/
	private String supplier;
	/**下一步工作计划*/
	private String nextPlan;
	/**计划标准*/
	private String planStandard;
	/**计划执行人（内部员工）*/
	private String planExecutorUser;
	/**计划执行人（联系人）*/
	private String planExecutorContact;
	private String planExecutorAll;
	/**告知执行人方式*/
	private String executeWay;
	/**任务象限*/
	private String quadrant;
	/**计划反馈时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date backTime;
	/**任务性质*/
	private String taskNature;
	/**指派者*/
	private String assignPerson;
	/**指派时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date assignTime;
	/**反馈方式*/
	private Integer backWay;


	public Date getCommunicationTime() {
		return communicationTime;
	}

	public void setCommunicationTime(Date communicationTime) {
		this.communicationTime = communicationTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getTaskNature() {
		return taskNature;
	}

	public void setTaskNature(String taskNature) {
		this.taskNature = taskNature;
	}

	public String getAssignPerson() {
		return assignPerson;
	}

	public void setAssignPerson(String assignPerson) {
		this.assignPerson = assignPerson;
	}

	public Date getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}

	public Integer getBackWay() {
		return backWay;
	}

	public void setBackWay(Integer backWay) {
		this.backWay = backWay;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReportRemark() {
		return reportRemark;
	}

	public void setReportRemark(String reportRemark) {
		this.reportRemark = reportRemark;
	}

	public String getCommunicationWay() {
		return communicationWay;
	}

	public void setCommunicationWay(String communicationWay) {
		this.communicationWay = communicationWay;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCompanyInterior() {
		return companyInterior;
	}

	public void setCompanyInterior(String companyInterior) {
		this.companyInterior = companyInterior;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getNextPlan() {
		return nextPlan;
	}

	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}

	public String getPlanStandard() {
		return planStandard;
	}

	public void setPlanStandard(String planStandard) {
		this.planStandard = planStandard;
	}

	public String getPlanExecutorUser() {
		return planExecutorUser;
	}

	public void setPlanExecutorUser(String planExecutorUser) {
		this.planExecutorUser = planExecutorUser;
	}

	public String getPlanExecutorContact() {
		return planExecutorContact;
	}

	public void setPlanExecutorContact(String planExecutorContact) {
		this.planExecutorContact = planExecutorContact;
	}

	public String getPlanExecutorAll() {
		return planExecutorAll;
	}

	public void setPlanExecutorAll(String planExecutorAll) {
		this.planExecutorAll = planExecutorAll;
	}

	public String getExecuteWay() {
		return executeWay;
	}

	public void setExecuteWay(String executeWay) {
		this.executeWay = executeWay;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
}
