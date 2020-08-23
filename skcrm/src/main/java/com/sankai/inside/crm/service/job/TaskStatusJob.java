package com.sankai.inside.crm.service.job;

import com.sankai.inside.crm.core.utils.DateUtil;
import com.sankai.inside.crm.dao.IContactShareDAO;
import com.sankai.inside.crm.dao.ICustomerPoolDAO;
import com.sankai.inside.crm.dao.ICustomerRemindDAO;
import com.sankai.inside.crm.dao.IMessageDAO;
import com.sankai.inside.crm.entity.*;
import com.sankai.inside.crm.service.ITaskService;
import com.sankai.inside.crm.service.MessageService;
import org.apache.commons.collections.ArrayStack;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 任务执行状态
 * @author Jack 
 *
 */
@Service
public class TaskStatusJob implements Job {
	private static final Logger log=LoggerFactory.getLogger(TaskStatusJob.class);

	@Autowired
	private ITaskService taskService;
	@Autowired
	private MessageService messageService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	
	}
	public void execute() {
		try {
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			log.info("****【任务执行状态跑批开始...start】:{}",sdf.format(date));
			// 获取任务列表
			List<Task> tasks = taskService.getListForJob();
			if (CollectionUtils.isEmpty(tasks)) {
				log.info("****【任务执行状态跑批开始...end】:无跑批任务；{}",new Date());
				return ;
			}
			for (Task task : tasks) {
				int backWay = task.getBackWay();// 返回方式
				// difDay >= 0  正常状态
				// difDay < 0   超期状态，difDay取整数为超期天数
				long difDay = DateUtil.compareDate4Day(date, task.getBackTime());

				if (difDay < 0) { // 修改状态的超期天数
					Task updateTask = new Task();
					updateTask.setId(task.getId());
					updateTask.setStatus(Integer.valueOf(String.valueOf(Math.abs(difDay))));
					taskService.updateById(updateTask);
				}

				if (Objects.equals(backWay, 1) && difDay == 1) {// 提前一天提醒
					// 发站内信
					sendMsg(task);
				} else if ((Objects.equals(backWay, 2) || Objects.equals(backWay, 3)) && difDay == 0) { // 具体时间/具体节点 提醒
					// 发站内信
					sendMsg(task);
				}
			}
			log.error("############################【任务执行状态】【完成】【end】############################{}",new Date());
		} catch (Exception e) {
			log.error("############################【任务执行状态】【异常】【end】############################");
			e.printStackTrace();
		}
		
	}

	private void sendMsg(Task task){
		String planExecutorUser = task.getPlanExecutorUser();
		String planExecutorContact = task.getPlanExecutorContact();
		List<Integer> idList = new ArrayList<Integer>();
		if(StringUtils.isEmpty(planExecutorUser) && StringUtils.isEmpty(planExecutorContact)){
			return;
		} else if (!StringUtils.isEmpty(planExecutorUser) && !StringUtils.isEmpty(planExecutorContact)) {
			String[] idStrList = planExecutorUser.concat(",").concat(planExecutorContact).split(",");
			for (String id : idStrList) {
				idList.add(Integer.valueOf(id));
			}
		}else if (!StringUtils.isEmpty(planExecutorUser)) {
			String[] idStrList = planExecutorUser.split(",");
			for (String id : idStrList) {
				idList.add(Integer.valueOf(id));
			}
		}else if (!StringUtils.isEmpty(planExecutorContact)) {
			String[] idStrList = planExecutorContact.split(",");
			for (String id : idStrList) {
				idList.add(Integer.valueOf(id));
			}
		}

		Integer[] ids = (Integer[])idList.toArray();
		messageService.sendMessage("执行计划提醒",task.getNextPlan(),ids,0);
	}

}

