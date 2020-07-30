package com.sankai.inside.crm.entity;

import java.util.List;

/**
 * 展示要分享的客户
 * @author cgq
 *
 */
public class CustomerShareCusDTO {
	private String cusids;//接受
	private String shareids;//共享表Ids
	private Integer customerShareId;
	private String name;
	private Integer customerId;
	private String address;
	private String typeName;
	private String buyService;

	public String getBuyService() {
		return buyService;
	}

	public void setBuyService(String buyService) {
		this.buyService = buyService;
	}

	private List<CustomerContact> contacts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCusids() {
		return cusids;
	}
	public void setCusids(String cusids) {
		this.cusids = cusids;
	}
	
	
	public List<CustomerContact> getContacts() {
		return contacts;
	}
	public void setContacts(List<CustomerContact> contacts) {
		this.contacts = contacts;
	}
	
	
	public String getShareids() {
		return shareids;
	}
	public void setShareids(String shareids) {
		this.shareids = shareids;
	}
	
	public Integer getCustomerShareId() {
		return customerShareId;
	}
	public void setCustomerShareId(Integer customerShareId) {
		this.customerShareId = customerShareId;
	}
	@Override
	public String toString() {
		return "CustomerShareCusDTO [cusids=" + cusids + ", name=" + name + ", customerId=" + customerId + ", address="
				+ address + ", typeName=" + typeName + ", contacts=" + contacts + "]";
	}
	
	
	
	
}
