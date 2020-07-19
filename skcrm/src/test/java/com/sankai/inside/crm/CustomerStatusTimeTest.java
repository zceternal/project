//package com.sankai.inside.crm;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sankai.inside.crm.entity.CustomerStatueTimeDto;
//import com.sankai.inside.crm.service.CustomerStatusTimeService;
//import com.sankai.inside.crm.service.job.CustomerPoolJob;
//
//public class CustomerStatusTimeTest extends AbstractSpringTestCase {
//	private static final Logger log = LoggerFactory.getLogger(CustomerPoolJob.class);
//	@Resource
//	private CustomerStatusTimeService customerStatusTimeService;
//	
//	@Test
//	public void addTest() {
//		CustomerStatueTimeDto model = new CustomerStatueTimeDto();
//		model.setCustomerId(132);
//		model.setOperateTime(new Date());
//		model.setSpendDay(20);
//		model.setStatus(52);
//		customerStatusTimeService.add(model);
//		log.info("新增Id="+model.getId().toString());
//	}
//	@Test
//	public void getListTest() {
//		List<CustomerStatueTimeDto> list = customerStatusTimeService.getList(132);
//		log.info("数据条数="+list.size());
//	}
//	@Test
//	public void modifyDayTest() {
//		CustomerStatueTimeDto model = new CustomerStatueTimeDto();
//		
//		model.setId(2);
//		//model.setCustomerId(132);
//		//model.setStatus(52);
//		
//		model.setOperateTime(new Date());
//		model.setSpendDay(2);
//		
//		customerStatusTimeService.modifyDay(model);
//		log.info("修改结果="+customerStatusTimeService.modifyDay(model));
//	}
//}
