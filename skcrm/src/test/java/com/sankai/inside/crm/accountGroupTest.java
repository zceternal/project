//package com.sankai.inside.crm;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.sankai.inside.crm.dao.IAccountGroupDao;
//import com.sankai.inside.crm.entity.AccountGroupEntity;
//import com.sankai.inside.crm.entity.AccountGroupFrom;
//import com.sankai.inside.crm.service.AccountGroupService;
//
//public class accountGroupTest extends AbstractSpringTestCase {
//
//	private static final Logger log=LoggerFactory.getLogger(accountGroupTest.class);
//	
//	@Autowired
//	IAccountGroupDao dao;
//	@Autowired
//	AccountGroupService service;
//	
//
//	
//	@Test
//	public void getAccountIdStrTest() {
//		String v = service.getAccountIdStr(86);
//		log.info("==========>结果:{}",v);
//	}
//	
//	@Test
//	public void addTest() {
//		try {
//			AccountGroupFrom dto = new AccountGroupFrom();
//			dto.setAccountList("1,2,3");
//			dto.setCreateName("zhangsan");
//			dto.setCreateTime(new Date());
//			dto.setLastModifyName("lisi");
//			dto.setName("AGroup");
//			dto.setRemark("beizhu");
//			service.add(dto);
//		} catch (Exception e) {
//			log.info("==========>异常:{}",e);
//		}
//	}
//	
//	@Test
//	public void insertTest() {
//		try {
//			AccountGroupEntity entity = new AccountGroupEntity();
//			entity.setAccountList("1,2,3");
//			entity.setCreateName("zhangsan");
//			entity.setCreateTime(new Date());
//			entity.setLastModifyName("lisi");
//			entity.setName("AGroup");
//			entity.setRemark("beizhu");
//			dao.insert(entity);
//		} catch (Exception e) {
//			log.info("==========>异常:{}",e);
//		}
//	}
//	
//
//}
