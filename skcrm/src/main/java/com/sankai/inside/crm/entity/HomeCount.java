package com.sankai.inside.crm.entity;

import java.util.List;

/**
 * 
 * 首页统计数量
 * @author Zzc
 *
 */
public class HomeCount {

	/**
	 * 客户总数量
	 * 当前登录人的客户总量【包含自己新增、其他人共享】
	 */
	private Integer totalCustomerCount;
	/**
	 * 客户的联系人总数量
	 * 当前登录人的联系人总量【包含自己新增、其他人共享】
	 */
	private Integer totalContactCount;
	/**
	 * 7天未跟踪客户数量
	 */
	private Integer totalSevenTraceCount;
	/**
	 * 10天未跟踪客户数量
	 */
	private Integer totalTenTraceCount;	
	/**
	 * 15天未跟踪客户数量
	 */
	private Integer totalFifteenTraceCount;
	/**
	 * 30天未跟踪客户数量
	 */
	private Integer totalThirtyTraceCount;
	/**
	 * 60天未跟踪客户数量
	 */
	private Integer totalSixtyTraceCount;
	/**
	 * 未跟踪客户数量
	 */
	private Integer totalNotTraceCount;
	/**
	 * 客户状态和状态对应的客户数量
	 */
	private List<CustomerStatus> customerStatusList;
	
	public List<CustomerStatus> getCustomerStatusList() {
		return customerStatusList;
	}
	public void setCustomerStatusList(List<CustomerStatus> customerStatusList) {
		this.customerStatusList = customerStatusList;
	}
	public Integer getTotalCustomerCount() {
		return totalCustomerCount;
	}
	public void setTotalCustomerCount(Integer totalCustomerCount) {
		this.totalCustomerCount = totalCustomerCount;
	}
	public Integer getTotalContactCount() {
		return totalContactCount;
	}
	public void setTotalContactCount(Integer totalContactCount) {
		this.totalContactCount = totalContactCount;
	}
	public Integer getTotalSevenTraceCount() {
		return totalSevenTraceCount;
	}
	public void setTotalSevenTraceCount(Integer totalSevenTraceCount) {
		this.totalSevenTraceCount = totalSevenTraceCount;
	}
	public Integer getTotalTenTraceCount() {
		return totalTenTraceCount;
	}
	public void setTotalTenTraceCount(Integer totalTenTraceCount) {
		this.totalTenTraceCount = totalTenTraceCount;
	}
	public Integer getTotalFifteenTraceCount() {
		return totalFifteenTraceCount;
	}
	public void setTotalFifteenTraceCount(Integer totalFifteenTraceCount) {
		this.totalFifteenTraceCount = totalFifteenTraceCount;
	}
	public Integer getTotalThirtyTraceCount() {
		return totalThirtyTraceCount;
	}
	public void setTotalThirtyTraceCount(Integer totalThirtyTraceCount) {
		this.totalThirtyTraceCount = totalThirtyTraceCount;
	}
	public Integer getTotalSixtyTraceCount() {
		return totalSixtyTraceCount;
	}
	public void setTotalSixtyTraceCount(Integer totalSixtyTraceCount) {
		this.totalSixtyTraceCount = totalSixtyTraceCount;
	}
	public Integer getTotalNotTraceCount() {
		return totalNotTraceCount;
	}
	public void setTotalNotTraceCount(Integer totalNotTraceCount) {
		this.totalNotTraceCount = totalNotTraceCount;
	}
	
}
