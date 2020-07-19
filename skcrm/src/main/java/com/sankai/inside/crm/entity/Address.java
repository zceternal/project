package com.sankai.inside.crm.entity;

import java.io.Serializable;
/**
 * 用于省市县三级联动
 * @author Jack Cui
 *
 */
@SuppressWarnings("serial")
public class Address implements Serializable {
	private Integer id;
	private Integer	code;
	private Integer parentId;
	private String name;
	private Integer level;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", code=" + code + ", parentId=" + parentId + ", name=" + name + ", level=" + level
				+ "]";
	}
	
}
