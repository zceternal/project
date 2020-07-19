//package com.sankai.inside.crm;
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//
//
//public class customerPoolJopTest extends AbstractSpringTestCase  {
//	
//	private static final Logger log=LoggerFactory.getLogger(CustomerPoolJob.class);
//	
//	@Resource
//	private CustomerPoolJob customerPoolJob;//公海跑批
//	
//	//公海跑批
//	@Test
//	public void executeTest() {
//		try {
//			customerPoolJob.execute();
//			log.info("============>公海跑批：SUCC");
//		} catch (JobExecutionException e) {
//			log.info("============>公海跑批异常：{}",e.getMessage());
//		}
//	}
//	
//}
