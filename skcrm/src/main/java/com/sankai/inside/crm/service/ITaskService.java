package com.sankai.inside.crm.service;

import com.github.pagehelper.Page;
import com.sankai.inside.crm.entity.*;

public interface ITaskService {

    /** 获取任务列表 */
    ServiceResult<Page<Task>> getList(TaskListSearch val, int page, int pageSize) ;

}
