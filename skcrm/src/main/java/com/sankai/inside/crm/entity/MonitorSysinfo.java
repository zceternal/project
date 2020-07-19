package com.sankai.inside.crm.entity;

public class MonitorSysinfo {
	
	private String type; // 类型
	private String name; // 名称
	private String value; // 值

	public MonitorSysinfo(final String typeParm, final String nameParm, final String valueParm) {
		this.type = typeParm;
		this.name = nameParm;
		this.value = valueParm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
