package com.sankai.inside.crm.service;

import com.github.pagehelper.Page;
import com.sankai.inside.crm.entity.*;

public interface ITaskService {

    /** 获取任务列表 */
    ServiceResult<Page<Task>> getList(TaskListSearch val, int page, int pageSize) ;
    /** 新增任务 */
    ServiceResultBool add(Task model);
    /** 获取任务 */
    Task getOne(int taskId);
    /** 新增任务反馈 */
    ServiceResultBool addTaskBack(TaskFeedback model);
    /** 新增任务反馈附件 */
    ServiceResultBool addTaskBackFile(TaskFeedbackFile model);
}
