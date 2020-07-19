package com.sankai.inside.crm.entity.yun;

import java.util.Date;

/**
 * 采集 智慧云客户
 * @author Zzc
 * @date 2016-06-20
 */
public class CustomerYun {

	private String customerName;//客户名称
	private Integer status;//客户状态
	private String type;//客户类型
	private String customerPhone;//客户电话
	private Integer province;//省
	private Integer city;//市
	private Integer country;//县
	private String address;//客户详细地址
	private Date createTime;//客户创建时间
	
	private String contactName;//联系人名称
	private Integer Sex;//联系人性别
	private String department;//联系人部门
	private String contactPhone;//联系人电话
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Integer getSex() {
		return Sex;
	}
	public void setSex(Integer sex) {
		Sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	

	
	
}
