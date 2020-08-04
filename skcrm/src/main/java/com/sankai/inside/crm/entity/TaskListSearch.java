package com.sankai.inside.crm.entity;

import com.sankai.inside.crm.web.model.FormPage;

import java.util.List;

/**
 * 任务查询条件实体
 */
public class TaskListSearch extends FormPage{

	private String quadrant;//任务象限
	private String status;//任务状态
	private String source;//任务来源
	private int accountId;//负责人

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
}
