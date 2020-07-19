package com.sankai.inside.crm.entity;

/**
 * 部门所有成员用户
 * @author Zzc
 *
 */
public class AccountOfDept {

	private int id;
	private String name;
	private int sex;
	private String number;
	private int departmentId;
	private int isDeptManager;//用户是否是部门领导：1 是；0 不是
	private boolean isMySelf;//是否是自己
	
	
	
	public int getIsDeptManager() {
		return isDeptManager;
	}
	public void setIsDeptManager(int isDeptManager) {
		this.isDeptManager = isDeptManager;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public boolean isMySelf() {
		return isMySelf;
	}
	public void setMySelf(boolean isMySelf) {
		this.isMySelf = isMySelf;
	}
	
}
