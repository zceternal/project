//package com.sankai.inside.crm;
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//import com.sankai.inside.crm.service.job.QuartzCustomerLimitService;
//
///**
// * 客户上限下限提醒
// * @author Zzc
// *
// */
//public class QuartzCustomerLimitServiceTest extends AbstractSpringTestCase  {
//	
//	private static final Logger log=LoggerFactory.getLogger(CustomerPoolJob.class);
//	
//	@Resource
//	private QuartzCustomerLimitService quartzCustomerLimitService;
//	
//	//客户上限下限提醒
//	@Test
//	public void executeTest() {
//		try {
//			quartzCustomerLimitService.execute();
//			log.info("============>客户上限下限提醒跑批：SUCC");
//		} catch (Exception e) {
//			log.info("============>客户上限下限提醒异常：{}",e.getMessage());
//		}
//	}
//	
//}
