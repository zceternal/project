//package com.sankai.inside.crm;
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.service.QuartzEmailService;
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//
///**
// * 提醒发送邮件
// * @author Zzc
// *
// */
//public class QuartzEmailServiceTest extends AbstractSpringTestCase  {
//	
//	private static final Logger log=LoggerFactory.getLogger(CustomerPoolJob.class);
//	
//	@Resource
//	private QuartzEmailService quartzEmailService;
//	
//	//提醒发送邮件
//	@Test
//	public void executeTest() {
//		try {
//			quartzEmailService.execute();
//			log.info("============>提醒发送邮件跑批：SUCC");
//		} catch (JobExecutionException e) {
//			log.info("============>提醒发送邮件跑批异常：{}",e.getMessage());
//		}
//	}
//	
//}
