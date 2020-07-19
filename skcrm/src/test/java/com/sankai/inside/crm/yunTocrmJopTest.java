//package com.sankai.inside.crm;
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//import com.sankai.inside.crm.service.job.CustomerYunTransformCrmService;
//
///**
// * 智慧云客户转到crm
// * @author Zzc
// *
// */
//public class yunTocrmJopTest extends AbstractSpringTestCase  {
//	
//	private static final Logger log=LoggerFactory.getLogger(CustomerPoolJob.class);
//	
//	@Resource
//	private CustomerYunTransformCrmService service;
//	
//	//智慧云客户转到crm
//	@Test
//	public void executeTest() {
//		try {
//			service.execute();
//			log.info("============>转换：SUCC");
//		} catch (Exception e) {
//			log.info("============>转换异常：{}",e.getMessage());
//		}
//	}
//	
//}
