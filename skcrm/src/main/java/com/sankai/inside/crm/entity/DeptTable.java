package com.sankai.inside.crm.entity;

import java.util.List;

public class DeptTable {
	
	private String nameLevel1;
	
	private Integer idLevel1;
	
	private String nameLevel2;
	
	private Integer idLevel2;
	
	
	private List<Integer> deptLevel2Child;
	private String deptLevel2ChildStr;
	
	private List<CustomerShareAccDTO> users;

	public String getNameLevel1() {
		return nameLevel1;
	}

	public void setNameLevel1(String nameLevel1) {
		this.nameLevel1 = nameLevel1;
	}

	public Integer getIdLevel1() {
		return idLevel1;
	}

	public void setIdLevel1(Integer idLevel1) {
		this.idLevel1 = idLevel1;
	}

	public String getNameLevel2() {
		return nameLevel2;
	}

	public void setNameLevel2(String nameLevel2) {
		this.nameLevel2 = nameLevel2;
	}

	public Integer getIdLevel2() {
		return idLevel2;
	}

	public void setIdLevel2(Integer idLevel2) {
		this.idLevel2 = idLevel2;
	}

	public List<Integer> getDeptLevel2Child() {
		return deptLevel2Child;
	}

	public void setDeptLevel2Child(List<Integer> deptLevel2Child) {
		this.deptLevel2Child = deptLevel2Child;
	}

	public List<CustomerShareAccDTO> getUsers() {
		return users;
	}

	public void setUsers(List<CustomerShareAccDTO> users) {
		this.users = users;
	}

	public String getDeptLevel2ChildStr() {
		return deptLevel2ChildStr;
	}

	public void setDeptLevel2ChildStr(String deptLevel2ChildStr) {
		this.deptLevel2ChildStr = deptLevel2ChildStr;
	}

	@Override
	public String toString() {
		return "DeptTable [nameLevel1=" + nameLevel1 + ", idLevel1=" + idLevel1 + ", nameLevel2=" + nameLevel2
				+ ", idLevel2=" + idLevel2 + ", deptLevel2Child=" + deptLevel2Child + ", deptLevel2ChildStr="
				+ deptLevel2ChildStr + ", users=" + users + "]";
	}
	
	
	
	
	
	
	
	
}
