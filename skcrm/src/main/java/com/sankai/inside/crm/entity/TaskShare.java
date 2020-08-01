package com.sankai.inside.crm.entity;

import java.util.Date;

public class TaskShare {
    /***/
    private Integer id;

    /**任务id*/
    private Integer taskId;

    /**分享人id*/
    private Integer accountId;

    /**允许人id*/
    private Integer allowAccountId;

    /**创建时间*/
    private Date createTime;

    /**创建人*/
    private String createName;

    /**删除标记：0正常，-1删除；*/
    private Integer delFlag;

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
     * 任务id
     * @return task_id 任务id
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * 任务id
     * @param taskId 任务id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 分享人id
     * @return account_id 分享人id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 分享人id
     * @param accountId 分享人id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 允许人id
     * @return allow_account_id 允许人id
     */
    public Integer getAllowAccountId() {
        return allowAccountId;
    }

    /**
     * 允许人id
     * @param allowAccountId 允许人id
     */
    public void setAllowAccountId(Integer allowAccountId) {
        this.allowAccountId = allowAccountId;
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
     * 删除标记：0正常，-1删除；
     * @return del_flag 删除标记：0正常，-1删除；
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记：0正常，-1删除；
     * @param delFlag 删除标记：0正常，-1删除；
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}