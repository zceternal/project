package com.sankai.inside.crm.entity;

import java.util.Date;

public class CustomerTransDTO {
	private Integer id;
	private String name;
	private String shortName;
	private Integer status;//客户状态,字典
	private String type;//客户类型,字典
	private String source;//客户来源,字典
	private String url;
	private String phone;
	private Integer province;//,字典
	private Integer city;
	private Integer country;
	private String address;
	private Double xCoord;
	private Double yCoord;
	private String remark;
	private Integer state;
	private Integer createId;
	private Date createTime;
	
	private String statusName;//状态名
	private String typeName;//类型名
	private String sourceName;//来源名
	private String provinceName;
	private String cityName;
	private String countryName;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", shortName=" + shortName + ", status=" + status + ", type="
				+ type + ", source=" + source + ", url=" + url + ", phone=" + phone + ", province=" + province
				+ ", city=" + city + ", country=" + country + ", address=" + address + ", xCoord=" + xCoord
				+ ", yCoord=" + yCoord + ", remark=" + remark + ", state=" + state + ", createId=" + createId
				+ ", createTime=" + createTime + ", statusName=" + statusName + ", typeName=" + typeName
				+ ", sourceName=" + sourceName + ", provinceName=" + provinceName + ", cityName=" + cityName
				+ ", countryName=" + countryName + "]";
	}
}
