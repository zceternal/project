//package com.sankai.inside.crm;
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//import com.sankai.inside.crm.service.job.HighSeasJob;
//
///**
// * 公海及提醒管理
// * @author Zzc
// *
// */
//public class HighSeasJobTest extends AbstractSpringTestCase  {
//	
//	private static final Logger log=LoggerFactory.getLogger(CustomerPoolJob.class);
//	
//	@Resource
//	private HighSeasJob highSeasJob;
//	
//	//提醒发送邮件
//	@Test
//	public void executeTest() {
//		try {
//			highSeasJob.execute();
//			log.info("============>公海及提醒管理跑批：SUCC");
//		} catch (Exception e) {
//			log.info("============>公海及提醒管理跑批异常：{}",e.getMessage());
//		}
//	}
//	
//}
