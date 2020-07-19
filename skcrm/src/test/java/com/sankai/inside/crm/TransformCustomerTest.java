//package com.sankai.inside.crm;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.springframework.util.CollectionUtils;
//
//import com.sankai.inside.crm.entity.Customer;
//import com.sankai.inside.crm.entity.ServiceResultBool;
//import com.sankai.inside.crm.entity.yun.CustomerYun;
//import com.sankai.inside.crm.service.ICustomerService;
//import com.sankai.inside.crm.service.job.CustomerYunTransformCrmService;
//import com.sankai.inside.crm.service.job.QuartzCustomerLimitService;
//
//
//public class TransformCustomerTest extends AbstractSpringTestCase  {
//	
//	@Resource
//	private CustomerYunTransformCrmService customerYunTransformCrmService;//智慧云客户
//	
//	@Resource
//	private ICustomerService CustomerService;//客户
//	
////	@Resource
////	private  QuartzCustomerLimitService cutomerLimit ;
//	
////	//客户数量限制
////	@SuppressWarnings("unused")
////	@Test
////	public void custoemrLimitTest() {
////		//Integer a = cutomerLimit.getDeptManager(7);//125
////		//Integer b = cutomerLimit.getDeptManager(5);//126
////		//Integer c = cutomerLimit.getDeptManager(6);//0
////		//Integer d = cutomerLimit.getDeptManager(10);//系统默认值
////		cutomerLimit.execute();
////	}
//	
//	//客户转换
//	@Test
//	public void transformTest() throws ParseException
//	{
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
//		//Date currDate = new Date();
//		Date currDate = sdf.parse("2016-10-20 23:00:00");
//		
//		Calendar ca=Calendar.getInstance();
//		ca.setTime(currDate);
//		ca.add(Calendar.HOUR_OF_DAY, -1);
//		String startTime = sdf.format(ca.getTime());//云客户 开始创建时间
//		String endTime = sdf.format(currDate);//云客户 结束创建时间
//		
//			List<CustomerYun> yunList = customerYunTransformCrmService.TransformCustomerTest(startTime,endTime);
//			if(!CollectionUtils.isEmpty(yunList))
//			{
//				for(CustomerYun item :yunList){
//					Customer customer=new Customer();//实体化要新增的客户类 
//					
//					// 客户信息	
//					customer.setName(item.getCustomerName());
//					customer.setShortName(item.getCustomerName());
//					customer.setStatus(item.getStatus());
//					customer.setType(item.getType());
//					customer.setPhone(item.getCustomerPhone());
//					customer.setProvince(item.getProvince()==null?-1:item.getProvince());
//					customer.setCity(item.getCity()==null?-1:item.getCity());
//					customer.setCountry(item.getCountry()==null?-1:item.getCountry());
//					customer.setAddress(item.getAddress());
//					customer.setCreateTime(item.getCreateTime());
//					customer.setCreateId(125);//默认为胡晓婉Id
//					
//					// 联系人信息
//					customer.setContactName(item.getContactName());
//					customer.setContactPhone(item.getContactPhone());
//					customer.setSex(item.getSex()==null?-1:item.getSex());
//										
//					ServiceResultBool result =	CustomerService.addCustomer(customer);
//					if(result.isSuccess())
//					{
//						System.out.println("true");
//					}else
//					{
//						System.out.println("false");
//					}
//				}
//			}
//	}
//	
//}
