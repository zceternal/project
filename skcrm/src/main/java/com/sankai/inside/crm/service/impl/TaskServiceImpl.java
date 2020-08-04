package com.sankai.inside.crm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.dao.ITaskDAO;
import com.sankai.inside.crm.entity.*;
import com.sankai.inside.crm.service.ITaskService;
import com.sankai.inside.crm.web.core.TraceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskDAO taskDAO;

    @Override
    public ServiceResult<Page<Task>> getList(TaskListSearch val, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        Page<Task> list = (Page<Task>) taskDAO.selectList(val);

        return new ServiceResult<Page<Task>>(list);
    }
}
