package com.sankai.inside.crm.service;

import com.github.pagehelper.Page;
import com.sankai.inside.crm.entity.*;

import java.util.List;

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
    /** 任务不可重复共享 */
    Integer checkExists(int taskId, int allowAccountId) ;
    /** 共享任务，批量新增 */
    Integer insertContactShare(List<TaskShare> list);
    /** 获取列表-任务状态逾期低于30天的 */
    List<Task> getListForJob();
    /** 修改任务信息 */
    int updateById(Task task);
}
