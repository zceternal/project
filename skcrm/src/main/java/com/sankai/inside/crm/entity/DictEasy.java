package com.sankai.inside.crm.entity;

public class DictEasy {
	private String name;
	private Integer id;
	private int customerNo;// 客户状态 所对应的客户数量

	public int getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
