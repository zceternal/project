package com.sankai.inside.crm.entity;

import java.util.function.Predicate;

import org.springframework.transaction.annotation.Propagation;

public class CustomerAutocomplate {
	
	private String name;
	private String shortName;
	private String createName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@Override
	public String toString() {
		return "CustomerAutocomplate [name=" + name + ", shortName=" + shortName + ", createName=" + createName + "]";
	}
	

}
