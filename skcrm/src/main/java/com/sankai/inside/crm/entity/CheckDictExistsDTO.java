package com.sankai.inside.crm.entity;

public class CheckDictExistsDTO {
	private String name;
	private Integer pid;
	private Integer id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CheckDictExistsDTO [name=" + name + ", pid=" + pid + ", id=" + id + "]";
	}
	
}
