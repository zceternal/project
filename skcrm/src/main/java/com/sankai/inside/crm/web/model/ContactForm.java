package com.sankai.inside.crm.web.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ContactForm {
	
	private Integer id;	
	private String name;
	private Integer sex;
	private String position;
	private String department;
	private Integer customerId;
	private String customerName;
	
	
	private Integer role;	
	private String directSupervisor;
	private String subordinate;
	private String phone;
	private String phone1;
	private String phone2;
	private String wechat;
	private String specialPhone;
	private String qq;
	private String email;
	private String microblog;
	private String fax;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	private String hobby;
	private String visitingCard;
	private Integer createId;
	private String CName;
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;

	private String source;
	private String referrerPerson;
	/**产品及服务*/
	private String buyService;

	public String getBuyService() {
		return buyService;
	}

	public void setBuyService(String buyService) {
		this.buyService = buyService;
	}
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReferrerPerson() {
		return referrerPerson;
	}

	public void setReferrerPerson(String referrerPerson) {
		this.referrerPerson = referrerPerson;
	}

	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDirectSupervisor() {
		return directSupervisor;
	}
	public void setDirectSupervisor(String directSupervisor) {
		this.directSupervisor = directSupervisor;
	}
	public String getSubordinate() {
		return subordinate;
	}
	public void setSubordinate(String subordinate) {
		this.subordinate = subordinate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getSpecialPhone() {
		return specialPhone;
	}
	public void setSpecialPhone(String specialPhone) {
		this.specialPhone = specialPhone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMicroblog() {
		return microblog;
	}
	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getVisitingCard() {
		return visitingCard;
	}
	public void setVisitingCard(String visitingCard) {
		this.visitingCard = visitingCard;
	}
	
}
