package com.sankai.inside.crm.entity;

import java.util.Date;

public class TaskFeedbackFile {
    /***/
    private Integer id;

    /**任务反馈id*/
    private Integer taskFeedbackId;

    /**附件名称*/
    private String fileName;

    /**附件路径*/
    private String filePath;

    /**删除标记：0正常；-1删除；*/
    private Integer delFlag;

    /**创建时间*/
    private Date createTime;

    /**创建人*/
    private String createName;

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
     * 任务反馈id
     * @return task_feedback_id 任务反馈id
     */
    public Integer getTaskFeedbackId() {
        return taskFeedbackId;
    }

    /**
     * 任务反馈id
     * @param taskFeedbackId 任务反馈id
     */
    public void setTaskFeedbackId(Integer taskFeedbackId) {
        this.taskFeedbackId = taskFeedbackId;
    }

    /**
     * 附件名称
     * @return file_name 附件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 附件名称
     * @param fileName 附件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 附件路径
     * @return file_path 附件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 附件路径
     * @param filePath 附件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 删除标记：0正常；-1删除；
     * @return del_flag 删除标记：0正常；-1删除；
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记：0正常；-1删除；
     * @param delFlag 删除标记：0正常；-1删除；
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
}