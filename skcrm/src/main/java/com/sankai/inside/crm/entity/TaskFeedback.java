package com.sankai.inside.crm.entity;

import java.util.Date;

public class TaskFeedback {
    /***/
    private Integer id;

    /**任务id*/
    private Integer taskId;

    /**反馈内容*/
    private String content;

    /**小结*/
    private String summary;

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
     * 反馈内容
     * @return content 反馈内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 反馈内容
     * @param content 反馈内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 小结
     * @return summary 小结
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 小结
     * @param summary 小结
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
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