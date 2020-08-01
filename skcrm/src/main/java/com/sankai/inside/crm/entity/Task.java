package com.sankai.inside.crm.entity;

import java.util.Date;

public class Task {
    /***/
    private Integer id;

    /**客户类型*/
    private String customerType;

    /**现有客户id*/
    private Integer customerId;

    /**公司内部*/
    private String companyInterior;

    /**供应商*/
    private String supplier;

    /**下一步工作计划*/
    private String nextPlan;

    /**计划标准*/
    private String planStandard;

    /**计划执行人*/
    private String planExecutor;

    /**告知执行人方式*/
    private String executeWay;

    /**任务象限*/
    private String quadrant;

    /**计划反馈时间*/
    private Date backTime;

    /**创建时间*/
    private Date createTime;

    /**创建人*/
    private String createName;

    /**最后修改时间*/
    private Date modifyTime;

    /**最后修改人*/
    private String modifyName;

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
     * 客户类型
     * @return customer_type 客户类型
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 客户类型
     * @param customerType 客户类型
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 现有客户id
     * @return customer_id 现有客户id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 现有客户id
     * @param customerId 现有客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 公司内部
     * @return company_interior 公司内部
     */
    public String getCompanyInterior() {
        return companyInterior;
    }

    /**
     * 公司内部
     * @param companyInterior 公司内部
     */
    public void setCompanyInterior(String companyInterior) {
        this.companyInterior = companyInterior == null ? null : companyInterior.trim();
    }

    /**
     * 供应商
     * @return supplier 供应商
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 供应商
     * @param supplier 供应商
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    /**
     * 下一步工作计划
     * @return next_plan 下一步工作计划
     */
    public String getNextPlan() {
        return nextPlan;
    }

    /**
     * 下一步工作计划
     * @param nextPlan 下一步工作计划
     */
    public void setNextPlan(String nextPlan) {
        this.nextPlan = nextPlan == null ? null : nextPlan.trim();
    }

    /**
     * 计划标准
     * @return plan_standard 计划标准
     */
    public String getPlanStandard() {
        return planStandard;
    }

    /**
     * 计划标准
     * @param planStandard 计划标准
     */
    public void setPlanStandard(String planStandard) {
        this.planStandard = planStandard == null ? null : planStandard.trim();
    }

    /**
     * 计划执行人
     * @return plan_executor 计划执行人
     */
    public String getPlanExecutor() {
        return planExecutor;
    }

    /**
     * 计划执行人
     * @param planExecutor 计划执行人
     */
    public void setPlanExecutor(String planExecutor) {
        this.planExecutor = planExecutor == null ? null : planExecutor.trim();
    }

    /**
     * 告知执行人方式
     * @return execute_way 告知执行人方式
     */
    public String getExecuteWay() {
        return executeWay;
    }

    /**
     * 告知执行人方式
     * @param executeWay 告知执行人方式
     */
    public void setExecuteWay(String executeWay) {
        this.executeWay = executeWay == null ? null : executeWay.trim();
    }

    /**
     * 任务象限
     * @return quadrant 任务象限
     */
    public String getQuadrant() {
        return quadrant;
    }

    /**
     * 任务象限
     * @param quadrant 任务象限
     */
    public void setQuadrant(String quadrant) {
        this.quadrant = quadrant == null ? null : quadrant.trim();
    }

    /**
     * 计划反馈时间
     * @return back_time 计划反馈时间
     */
    public Date getBackTime() {
        return backTime;
    }

    /**
     * 计划反馈时间
     * @param backTime 计划反馈时间
     */
    public void setBackTime(Date backTime) {
        this.backTime = backTime;
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

    /**
     * 创建人
     * @return create_name 创建人
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 创建人
     * @param createName 创建人
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * 最后修改时间
     * @return modify_time 最后修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 最后修改时间
     * @param modifyTime 最后修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 最后修改人
     * @return modify_name 最后修改人
     */
    public String getModifyName() {
        return modifyName;
    }

    /**
     * 最后修改人
     * @param modifyName 最后修改人
     */
    public void setModifyName(String modifyName) {
        this.modifyName = modifyName == null ? null : modifyName.trim();
    }
}