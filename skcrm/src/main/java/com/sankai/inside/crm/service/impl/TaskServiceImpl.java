package com.sankai.inside.crm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.dao.*;
import com.sankai.inside.crm.entity.*;
import com.sankai.inside.crm.service.ITaskService;
import com.sankai.inside.crm.web.core.TraceType;
import com.sankai.inside.crm.web.core.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskDAO taskDAO;
    @Resource
    private IContactDAO contactDAO;
    @Resource
    private IAccountDAO accountDAO;
    @Resource
    private ITaskFeedbackDAO taskFeedbackDAO;
    @Resource
    private ITaskFeedbackFileDAO taskFeedbackFileDAO;

    @Override
    public int updateById(Task task) {
        return taskDAO.updateByPrimaryKeySelective(task);
    }

    @Override
    public List<Task> getListForJob() {
        return taskDAO.getListForJob();
    }

    @Override
    public Integer insertContactShare(List<TaskShare> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 1;
        }
        return taskDAO.insertTaskShare(list);
    }

    @Override
    public Integer checkExists(int taskId, int allowAccountId) {
        TaskShareExistsCheck check = new TaskShareExistsCheck();
        check.setTaskId(taskId);
        check.setAllowAccountId(allowAccountId);
        return taskDAO.checkExists(check);
    }

    @Override
    public ServiceResultBool addTaskBack(TaskFeedback model) {
        taskFeedbackDAO.insert(model);
        return new ServiceResultBool(true,String.valueOf(model.getId()));
    }

    @Override
    public ServiceResultBool addTaskBackFile(TaskFeedbackFile model) {
        taskFeedbackFileDAO.insert(model);
        return new ServiceResultBool(true,"");
    }

    @Override
    public Task getOne(int taskId) {
        Task task = taskDAO.selectByPrimaryKey(taskId);
        if (Objects.isNull(task)) {
            return new Task();
        }
        String planExecutorAll = "";
        if (!Objects.isNull(task.getPlanExecutorContact())) {
            List<String> ids = Arrays.asList(task.getPlanExecutorContact().split(","));
            planExecutorAll = contactDAO.getNames(ids);
        }
        if (!Objects.isNull(task.getPlanExecutorUser())) {
            String names = accountDAO.getNames(task.getPlanExecutorUser());
            planExecutorAll  = StringUtils.isEmpty(planExecutorAll)?names:names+","+planExecutorAll;
        }
        task.setPlanExecutorAll(planExecutorAll);

        // 指派者
        if (!Objects.isNull(task.getAssignPerson())) {
            if (task.getAssignPerson().equals(UserState.getLoginId())) {
                task.setAssignPerson(null);// null代表自己
            }else{
                String names = accountDAO.getNames(task.getAssignPerson());
                task.setAssignPerson(names);
            }
        }
        return task;
    }

    @Override
    public ServiceResultBool add(Task model) {
        Date date = new Date();
        model.setCreateId(UserState.getLoginId());
        model.setCreateName(UserState.getLoginName());
        model.setCreateTime(date);
        model.setAssignPerson(String.valueOf(UserState.getLoginId()));
        model.setAssignTime(date);
        taskDAO.insert(model);
        return new ServiceResultBool();
    }

    @Override
    public ServiceResult<Page<Task>> getList(TaskListSearch val, int page, int pageSize) {
        PageHelper.startPage(page, pageSize, true);
        Page<Task> list = (Page<Task>) taskDAO.selectList(val);
        if (!CollectionUtils.isEmpty(list)) {
            for (Task task : list) {
                String planExecutorAll = "";
                if (!Objects.isNull(task.getPlanExecutorContact())) {
                    List<String> ids = Arrays.asList(task.getPlanExecutorContact().split(","));
                    planExecutorAll = contactDAO.getNames(ids);
                }
                if (!Objects.isNull(task.getPlanExecutorUser())) {
                    String names = accountDAO.getNames(task.getPlanExecutorUser());
                    planExecutorAll  = StringUtils.isEmpty(planExecutorAll)?names:names+","+planExecutorAll;
                }
                task.setPlanExecutorAll(planExecutorAll);

                // 指派者
                if (!Objects.isNull(task.getAssignPerson())) {
                    if (task.getAssignPerson().equals(UserState.getLoginId())) {
                        task.setAssignPerson(null);// null代表自己
                    }else{
                        String names = accountDAO.getNames(task.getAssignPerson());
                        task.setAssignPerson(names);
                    }
                }
            }
        }
        return new ServiceResult<Page<Task>>(list);
    }
}
