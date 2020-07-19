//package com.sankai.inside.crm;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//
//   //@ClassName: AbstractSpringTestCase
//   //@Description: 测试类基类  defaultRollback:true 则不会提交事务(可用于免污染数据库测试)
//   //@author
//   //@date 2016年5月9日 上午11:43:03
// 
//@ContextConfiguration(locations = {"classpath:spring-base.xml"})//,"classpath:logback.xml"
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//public abstract class AbstractSpringTestCase implements ApplicationContextAware {
//
//	protected final static Logger logger = LoggerFactory.getLogger(AbstractSpringTestCase.class);
//
//	protected ApplicationContext applicationContext;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws org.springframework.beans.BeansException {
//		this.applicationContext = applicationContext;
//	}
//
//	public Object getBean(String beanName) {
//		return applicationContext.getBean(beanName);
//	}
//
//	private long beginTime;
//
//	@Before
//	public void before() {
//		beginTime = System.currentTimeMillis();
//		logger.info("===========before================={}"+beginTime);
//	}
//
//	@After
//	public void after(){
//		long endTime = System.currentTimeMillis();
//		Assert.assertNotNull(applicationContext);
//		logger.info("===========end===================={}"+(endTime - beginTime));
//	}
//
//}
