//package com.sankai.inside.crm;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.dao.ICustomerDAO;
//import com.sankai.inside.crm.entity.CustomerStatus;
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//
//public class CustomerTest extends AbstractSpringTestCase {
//	private static final Logger log = LoggerFactory.getLogger(CustomerPoolJob.class);
//	@Resource
//	private ICustomerDAO customerDAO;
//
//	// 客户状态和状态对应的客户数量
//	@Test
//	public void selectStatusNoTest() {
//		Integer accId = 133;
//		List<CustomerStatus> list = customerDAO.selectStatusNo(accId);
//		for (CustomerStatus item : list) {
//			System.out.println(item.getStatus() + "--" + item.getStatusNo());
//		}
//	}
//
//}
