package com.sankai.inside.crm.entity;

import java.util.Date;

public class SysDict {
	private int id;
	private String value;
	private String name;
	private Integer parentId;
	private Integer order;
	private Integer createId;
	private Date createTime;
	private Integer state;
	private String remark;
	private String pycode;
	private String pyname;
	
	private String createName;
	private String createTimeStr;
	
	private int spendDay;//状态停留时间【20170302 zzc】
	
	
	public int getSpendDay() {
		return spendDay;
	}
	public void setSpendDay(int spendDay) {
		this.spendDay = spendDay;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPycode() {
		return pycode;
	}
	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	public String getPyname() {
		return pyname;
	}
	public void setPyname(String pyname) {
		this.pyname = pyname;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	@Override
	public String toString() {
		return "SysDict [id=" + id + ", value=" + value + ", name=" + name + ", parentId=" + parentId + ", order="
				+ order + ", createId=" + createId + ", createTime=" + createTime + ", state=" + state + ", remark="
				+ remark + ", pycode=" + pycode + ", pyname=" + pyname + ", createName=" + createName
				+ ", createTimeStr=" + createTimeStr + "]";
	}
	
}
