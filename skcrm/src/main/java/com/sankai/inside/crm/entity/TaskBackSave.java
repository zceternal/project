package com.sankai.inside.crm.entity;

import com.sankai.inside.crm.web.model.FormPage;
import org.springframework.web.multipart.MultipartFile;

/**
 * 任务反馈实体
 */
public class TaskBackSave {

    private int taskId;//任务id
	private String content;//反馈成果
	private String summary;//小结
	private MultipartFile[] uploadFile;//附件集合

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public MultipartFile[] getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile[] uploadFile) {
        this.uploadFile = uploadFile;
    }
}
