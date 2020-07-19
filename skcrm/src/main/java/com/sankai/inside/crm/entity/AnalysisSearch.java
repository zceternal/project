package com.sankai.inside.crm.entity;

import java.util.List;

public class AnalysisSearch {
private String startTime;
private String endTime;
private List<String> accountIds;
private Integer status;

public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public List<String> getAccountIds() {
	return accountIds;
}
public void setAccountIds(List<String> accountIds) {
	this.accountIds = accountIds;
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


}
