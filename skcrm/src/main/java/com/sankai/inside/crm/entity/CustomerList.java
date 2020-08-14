package com.sankai.inside.crm.entity;

import java.util.Date;

public class CustomerList {

	
	private String traceName;//跟踪名称
	
	public String getTraceName() {
		return traceName;
	}
	public void setTraceName(String traceName) {
		this.traceName = traceName;
	}
	private int shareId;//分享Id
	private int customerId;//客户Id
	
	public int getShareId() {
		return shareId;
	}
	public void setShareId(int shareId) {
		this.shareId = shareId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	private int order;
	private String shortName;//客户简称
	private int status ;
	private String statusName;
	private Date finalTime;
	private int province;
	private String provinceName;
	private int type;
	private String typeName;
	//yxb-2016/12/8
	private int isFrom;//客户来源
	private String createName;//客户的创建人-转移需要知道
	private String optName;//共享人来源
	
	private int allowAccountId;
	private String allowAccountName;
	private Date createTime;
	private String phone;
	/**推荐人姓名*/
	private String recommender;

	/**产品及服务*/
	private String buyService;

	/**客户来源(销售形式)*/
	private String cusSource;
	/**客户来源类型(销售形式类型)：1渠道；2直销*/
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

	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Date getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(int allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
	public String getAllowAccountName() {
		return allowAccountName;
	}
	public void setAllowAccountName(String allowAccountName) {
		this.allowAccountName = allowAccountName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	private String name;//客户全名称
	private String url;//客户网址
	private String contactName;//联系人名称
	private String contactPhone;//联系人电话

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public int getIsFrom() {
		return isFrom;
	}
	public void setIsFrom(int isFrom) {
		this.isFrom = isFrom;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
}

