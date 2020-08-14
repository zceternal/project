package com.sankai.inside.crm.entity;

import java.util.Date;

public class SysCustomerRelations {
    /***/
    private Integer id;

    /**客户id*/
    private Integer customerId;

    /**渠道伙伴*/
    private String channelPartner;
    private String channelPartnerName;

    /**客户信任人*/
    private String trustPerson;
    private String trustPersonName;

    /**客户决策人*/
    private String decisionPerson;
    private String decisionPersonName;

    /**客户管理人*/
    private String managePerson;
    private String managePersonName;

    /**客户办事人*/
    private String handlePerson;
    private String handlePersonName;

    /**客户业务人*/
    private String professionalPerson;
    private String professionalPersonName;

    /**创建人Id*/
    private Integer createId;

    /**创建时间*/
    private Date createTime;

    public String getChannelPartnerName() {
        return channelPartnerName;
    }

    public void setChannelPartnerName(String channelPartnerName) {
        this.channelPartnerName = channelPartnerName;
    }

    public String getTrustPersonName() {
        return trustPersonName;
    }

    public void setTrustPersonName(String trustPersonName) {
        this.trustPersonName = trustPersonName;
    }

    public String getDecisionPersonName() {
        return decisionPersonName;
    }

    public void setDecisionPersonName(String decisionPersonName) {
        this.decisionPersonName = decisionPersonName;
    }

    public String getManagePersonName() {
        return managePersonName;
    }

    public void setManagePersonName(String managePersonName) {
        this.managePersonName = managePersonName;
    }

    public String getHandlePersonName() {
        return handlePersonName;
    }

    public void setHandlePersonName(String handlePersonName) {
        this.handlePersonName = handlePersonName;
    }

    public String getProfessionalPersonName() {
        return professionalPersonName;
    }

    public void setProfessionalPersonName(String professionalPersonName) {
        this.professionalPersonName = professionalPersonName;
    }

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 客户id
     * @return customer_id 客户id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 客户id
     * @param customerId 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 渠道伙伴
     * @return channel_partner 渠道伙伴
     */
    public String getChannelPartner() {
        return channelPartner;
    }

    /**
     * 渠道伙伴
     * @param channelPartner 渠道伙伴
     */
    public void setChannelPartner(String channelPartner) {
        this.channelPartner = channelPartner == null ? null : channelPartner.trim();
    }

    /**
     * 客户信任人
     * @return trust_person 客户信任人
     */
    public String getTrustPerson() {
        return trustPerson;
    }

    /**
     * 客户信任人
     * @param trustPerson 客户信任人
     */
    public void setTrustPerson(String trustPerson) {
        this.trustPerson = trustPerson == null ? null : trustPerson.trim();
    }

    /**
     * 客户决策人
     * @return decision_person 客户决策人
     */
    public String getDecisionPerson() {
        return decisionPerson;
    }

    /**
     * 客户决策人
     * @param decisionPerson 客户决策人
     */
    public void setDecisionPerson(String decisionPerson) {
        this.decisionPerson = decisionPerson == null ? null : decisionPerson.trim();
    }

    /**
     * 客户管理人
     * @return manage_person 客户管理人
     */
    public String getManagePerson() {
        return managePerson;
    }

    /**
     * 客户管理人
     * @param managePerson 客户管理人
     */
    public void setManagePerson(String managePerson) {
        this.managePerson = managePerson == null ? null : managePerson.trim();
    }

    /**
     * 客户办事人
     * @return handle_person 客户办事人
     */
    public String getHandlePerson() {
        return handlePerson;
    }

    /**
     * 客户办事人
     * @param handlePerson 客户办事人
     */
    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson == null ? null : handlePerson.trim();
    }

    /**
     * 客户业务人
     * @return professional_person 客户业务人
     */
    public String getProfessionalPerson() {
        return professionalPerson;
    }

    /**
     * 客户业务人
     * @param professionalPerson 客户业务人
     */
    public void setProfessionalPerson(String professionalPerson) {
        this.professionalPerson = professionalPerson == null ? null : professionalPerson.trim();
    }

    /**
     * 创建人Id
     * @return create_id 创建人Id
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 创建人Id
     * @param createId 创建人Id
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}