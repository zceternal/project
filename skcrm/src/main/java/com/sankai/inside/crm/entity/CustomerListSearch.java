package com.sankai.inside.crm.entity;

import java.util.List;

import com.sankai.inside.crm.web.model.FormPage;

public class CustomerListSearch  extends FormPage{
	
	private int deptLeadser;//是否是部门领导人

	private String content;//文本查询条件
	private String traceType;//跟踪状态
	
	private String status;//销售状态
	
	private List<Integer> principal;//销售负责人
	
	private String customerType;//客户类型 
	private String principalStr;//销售负责人字符串
	
	private int accountId;
	
	private String orderField;//排序字段
	private String orderType;//排序类型
	
	private String excludeAccountIds;//排序字段
	
	private int loginId;
	
	/**
	 * 平台版本
	 */
	private String salesSuccessRate;//平台版本  (old客户类型) 
	private int isFrom;//客户来源
	
	private String allowAccountName;
	private List<Integer> accountIds;

	private String cusSource;//客户来源(销售形式)
	private String buyService;//产品与服务
	private String followState;//销售推进状态
	private String nextPlanState;//下一步计划状态


	public String getCusSource() {
		return cusSource;
	}

	public void setCusSource(String cusSource) {
		this.cusSource = cusSource;
	}

	public String getBuyService() {
		return buyService;
	}

	public void setBuyService(String buyService) {
		this.buyService = buyService;
	}

	public String getFollowState() {
		return followState;
	}

	public void setFollowState(String followState) {
		this.followState = followState;
	}

	public String getNextPlanState() {
		return nextPlanState;
	}

	public void setNextPlanState(String nextPlanState) {
		this.nextPlanState = nextPlanState;
	}

	public List<Integer> getAccountIds() {
		return accountIds;
	}
	public void setAccountIds(List<Integer> accountIds) {
		this.accountIds = accountIds;
	}
	public String getAllowAccountName() {
		return allowAccountName;
	}
	public void setAllowAccountName(String allowAccountName) {
		this.allowAccountName = allowAccountName;
	}
	public String getExcludeAccountIds() {
		return excludeAccountIds;
	}
	public void setExcludeAccountIds(String excludeAccountIds) {
		this.excludeAccountIds = excludeAccountIds;
	}
	public String getsalesSuccessRate() {
		return salesSuccessRate;
	}
	public void setsalesSuccessRate(String salesSuccessRate) {
		this.salesSuccessRate = salesSuccessRate;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getPrincipalStr() {
		return principalStr;
	}
	public void setPrincipalStr(String principalStr) {
		this.principalStr = principalStr;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public int getDeptLeadser() {
		return deptLeadser;
	}
	public void setDeptLeadser(int deptLeadser) {
		this.deptLeadser = deptLeadser;
	}
	
	public List<Integer> getPrincipal() {
		return principal;
	}
	public void setPrincipal(List<Integer> principal) {
		this.principal = principal;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getTraceType() {
		return traceType;
	}
	public void setTraceType(String trace) {
		this.traceType = trace;
	}
	public int getIsFrom() {
		return isFrom;
	}
	public void setIsFrom(int isFrom) {
		this.isFrom = isFrom;
	}

}
