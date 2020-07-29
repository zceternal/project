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
	/**推荐人姓名*/
	private String recommender;

	/**产品及服务*/
	private String buyService;

	/**客户来源*/
	private String cusSource;

	/**客户来源类型：1渠道；2直销*/
	private String cusSourceType;

	/**客户简介*/
	private String synopsis;

	/**客户行业*/
	private String business;

	/**客户出钱性质*/
	private String payNature;

	/**销售推进状态*/
	private String followState;

	/**销售推进状态-明细（多个之间用 逗号 分割）*/
	private String followStateDetails;

	/**渠道伙伴*/
	private String channelPartner;

	/**客户信任人*/
	private String trustPerson;

	/**客户决策人*/
	private String decisionPerson;

	/**客户管理人*/
	private String managePerson;

	/**客户办事人*/
	private String handlePerson;

	/**客户业务人*/
	private String professionalPerson;

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getBuyService() {
		return buyService;
	}

	public void setBuyService(String buyService) {
		this.buyService = buyService;
	}

	public String getCusSource() {
		return cusSource;
	}

	public void setCusSource(String cusSource) {
		this.cusSource = cusSource;
	}

	public String getCusSourceType() {
		return cusSourceType;
	}

	public void setCusSourceType(String cusSourceType) {
		this.cusSourceType = cusSourceType;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getPayNature() {
		return payNature;
	}

	public void setPayNature(String payNature) {
		this.payNature = payNature;
	}

	public String getFollowState() {
		return followState;
	}

	public void setFollowState(String followState) {
		this.followState = followState;
	}

	public String getFollowStateDetails() {
		return followStateDetails;
	}

	public void setFollowStateDetails(String followStateDetails) {
		this.followStateDetails = followStateDetails;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public String getChannelPartner() {
		return channelPartner;
	}

	public void setChannelPartner(String channelPartner) {
		this.channelPartner = channelPartner;
	}

	public String getTrustPerson() {
		return trustPerson;
	}

	public void setTrustPerson(String trustPerson) {
		this.trustPerson = trustPerson;
	}

	public String getDecisionPerson() {
		return decisionPerson;
	}

	public void setDecisionPerson(String decisionPerson) {
		this.decisionPerson = decisionPerson;
	}

	public String getManagePerson() {
		return managePerson;
	}

	public void setManagePerson(String managePerson) {
		this.managePerson = managePerson;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getProfessionalPerson() {
		return professionalPerson;
	}

	public void setProfessionalPerson(String professionalPerson) {
		this.professionalPerson = professionalPerson;
	}

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
