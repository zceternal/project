package com.sankai.inside.crm.entity;

import java.util.Date;

public class Customer {
	private int id;
	private String name;
	private String shortName;
	private int status;//客户状态,字典
	private String type;//客户类型,字典
	private String source;//客户来源,字典
	private String url;
	private String phone;
	private int province;//,字典
	private int city;
	private int country;
	private String address;
	private Double xCoord;
	private Double yCoord;
	private String remark;
	private int state;
	private int createId;
	private Date createTime;
	private String nameSimplePy;
	private String namePy;
	public Integer contactId;
	private String contactName;
	private String contactPhone;
	private int salesSuccessRate;
	private int sex;
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSalesSuccessRate() {
		return salesSuccessRate;
	}
	public void setSalesSuccessRate(int salesSuccessRate) {
		this.salesSuccessRate = salesSuccessRate;
	}
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	
	public String getNameSimplePy() {
		return nameSimplePy;
	}
	public void setNameSimplePy(String nameSimplePy) {
		this.nameSimplePy = nameSimplePy;
	}
	public String getNamePy() {
		return namePy;
	}
	public void setNamePy(String namePy) {
		this.namePy = namePy;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Double getxCoord() {
		return xCoord;
	}
	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}
	public Double getyCoord() {
		return yCoord;
	}
	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", shortName=" + shortName + ", status=" + status + ", type="
				+ type + ", source=" + source + ", url=" + url + ", phone=" + phone + ", province=" + province
				+ ", city=" + city + ", country=" + country + ", address=" + address + ", xCoord=" + xCoord
				+ ", yCoord=" + yCoord + ", remark=" + remark + ", state=" + state + ", createId=" + createId
				+ ", createTime=" + createTime + "]";
	}
	
	/**
	 * 
	 * 客户置顶
	 * @author Zzc
	 *
	 */
	public class OrderUp{
		private int shareId ;
		private int allowAccountId;
		private int order ;
		public int getShareId() {
			return shareId;
		}
		public void setShareId(int shareId) {
			this.shareId = shareId;
		}
		public int getAllowAccountId() {
			return allowAccountId;
		}
		public void setAllowAccountId(int allowAccountId) {
			this.allowAccountId = allowAccountId;
		}
		public int getOrder() {
			return order;
		}
		public void setOrder(int order) {
			this.order = order;
		}
	}
	
}
