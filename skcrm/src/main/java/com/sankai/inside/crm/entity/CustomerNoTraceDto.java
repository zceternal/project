package com.sankai.inside.crm.entity;

import java.util.List;

/**
 * 客户未跟踪实体
 * @author Zzc
 * 
 *
 */
public class CustomerNoTraceDto  {
	
	//查询条件 
	/**
	 * 未跟踪天数，超过此数值的会陪处理
	 */
	private int day;
	/**
	 * 排除用户集合【集合中账号下的客户不受公海影响】
	 */
	private List<String> excludeAccIdList;
	/**
	 * 排除客户状态集合【集合中客户状态下的客户不受公海影响】
	 */
	private List<String> excludeStatusList;
	
	//获取的实体
	private Integer customerId;
	private Integer accountId;
	/**
	 * 实际未跟踪天数
	 */
	private int diffDay;
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public List<String> getExcludeAccIdList() {
		return excludeAccIdList;
	}
	public void setExcludeAccIdList(List<String> excludeAccIdList) {
		this.excludeAccIdList = excludeAccIdList;
	}
	public List<String> getExcludeStatusList() {
		return excludeStatusList;
	}
	public void setExcludeStatusList(List<String> excludeStatusList) {
		this.excludeStatusList = excludeStatusList;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public int getDiffDay() {
		return diffDay;
	}
	public void setDiffDay(int diffDay) {
		this.diffDay = diffDay;
	}
	
	
	
}
