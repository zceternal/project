package com.sankai.inside.crm.web.model;

import java.util.List;

/**
 * 任务共享
 */
public class TaskShareForm {
	private List<String> taskId;
	private List<String> allowAccountId;

	public List<String> getTaskId() {
		return taskId;
	}

	public void setTaskId(List<String> taskId) {
		this.taskId = taskId;
	}

	public List<String> getAllowAccountId() {
		return allowAccountId;
	}
	public void setAllowAccountId(List<String> allowAccountId) {
		this.allowAccountId = allowAccountId;
	}
}
