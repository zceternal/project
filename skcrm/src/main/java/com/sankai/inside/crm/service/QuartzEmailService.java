package com.sankai.inside.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sankai.inside.crm.core.mail.SpringMailSender;
import com.sankai.inside.crm.dao.ICustomerRemindDAO;
import com.sankai.inside.crm.entity.CustomerRemindSendEmail;
import com.sankai.inside.crm.web.controllers.CustomerController;

/**
 * 提醒客户——到期发送邮件——调度作业
 * 
 * @author Zzc
 *
 */
@Component
public class QuartzEmailService implements Job {

	// 日志管理
	public final static Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerRemindDAO customerRemindDAO;// 客户提醒

	@Autowired
	private SpringMailSender mailSender;// 邮件发送


	public void execute() throws JobExecutionException {
		Date date = new Date();
		log.info("QuartzEmailService 开始 -- " + date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String remindDate = sdf.format(date);
		List<CustomerRemindSendEmail> reminds = customerRemindDAO.selectListByRemindTime(remindDate);
		if (reminds != null) {
			for (CustomerRemindSendEmail item : reminds) {

				// 发送邮件
				try {

					String email = item.getEmail();
					String title = "提醒跟踪：" + item.getCustomerName();
					String content = "提醒日期：" + sdf.format(item.getRemindTime()) + "</br>" +"提醒客户：" + item.getCustomerName() +  "</br>" + "提醒内容：" + item.getRemark();

					log.info("QuartzEmailService 发送邮件到 -- " + email);

					mailSender.sendMail("邮件服务", email, title, content);
				} catch (Exception ex) {
					log.error("客户提醒---邮件发送失败", ex);
				}
			}
		}

		log.info("QuartzEmailService 结束 -- " + new Date());

	}


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
