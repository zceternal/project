package com.sankai.inside.crm.service.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sankai.inside.crm.entity.Customer;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.yun.CustomerYun;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.service.MessageService;
import com.sankai.inside.crm.yun.ICustomerYunDAO;

/**
 * 智慧云客户转CRM客户
 * @author Zzc
 * 2016-06-20
 */
@Service
public class CustomerYunTransformCrmService {

	private static final Logger log=LoggerFactory.getLogger(CustomerYunTransformCrmService.class); 
	
	@Resource
	private ICustomerYunDAO customerYunDAO; //智慧云客户 
	
	@Resource
	private ICustomerService customerService;//客户
	
	@Value("${YUN_TRANSFORM_CRM_DEFAULT_ID}")
	private Integer tranDefaultCreateId;//默认转换id
	@Value("${YUN_TRANSFORM_CRM_DEFAULT_TYPE}")
	private Integer defaultCustomerType;//默认客户类型id
	
	@Resource
	private MessageService messageService;//消息管理
	
	
	public List<CustomerYun> TransformCustomerTest(String startTime,String endTime){
		List<CustomerYun> yunList = customerYunDAO.getYunCustomerList(startTime,endTime);
		
		return yunList;
	}
	
	
	public void execute()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Date currDate = new Date();
		//Date currDate = sdf.parse("2016-06-21 16:00:00");
		
		Calendar ca=Calendar.getInstance();
		ca.setTime(currDate);
		ca.add(Calendar.HOUR_OF_DAY, -1);
		String startTime = sdf.format(ca.getTime());//云客户 开始创建时间
		String endTime = sdf.format(currDate);//云客户 结束创建时间
		//智慧云客户转CRM客户
			
		log.info("*********************【定时】客户转换操作**********************");
		log.info("*【定时】跑批开始...start\n");
		log.info("*【跑批时间】..." +sdf.format(currDate));
		log.info("*【跑批条件】..." +"startTime="+startTime+";endTime="+endTime);
		try {
		List<CustomerYun> yunList = customerYunDAO.getYunCustomerList(startTime,endTime);
			if(!yunList.isEmpty())
			{
				try {
					log.info("*【跑批客户数量】...{}" +yunList.size());
					
					for(CustomerYun item :yunList){
						Customer customer=new Customer();//实体化要新增的客户类 
						
						// 客户信息	
						customer.setName(item.getCustomerName());
						customer.setShortName(item.getCustomerName());
						customer.setStatus(item.getStatus());
						if(StringUtils.isBlank(defaultCustomerType.toString()))
							customer.setType("100"); //100标示公有云
						else
							customer.setType(defaultCustomerType.toString()); 
						customer.setPhone(item.getCustomerPhone());
						customer.setProvince(item.getProvince()==null?-1:item.getProvince());
						customer.setCity(item.getCity()==null?-1:item.getCity());
						customer.setCountry(item.getCountry()==null?-1:item.getCountry());
						customer.setAddress(item.getAddress());
						customer.setCreateTime(item.getCreateTime());
						Integer id = tranDefaultCreateId;
						if(id==null)id = 125;
						customer.setCreateId(id);//默认为胡晓婉Id
						
						// 联系人信息
						customer.setContactName(item.getContactName());
						customer.setContactPhone(item.getContactPhone());
						customer.setSex(item.getSex()==null?-1:item.getSex());
											
						ServiceResultBool result =	customerService.addCustomer(customer);
						if(result.isSuccess())
						{
							log.info("*【跑批成功】...{}客户名称：" +item.getCustomerName());
						}else
						{
							int[] ids = new int[1];
							ids[0]= tranDefaultCreateId;
							messageService.sendMessage("系统提醒-客户信息已存在","客户【"+item.getCustomerName()+"】已被其他人创建过。" , -100, ids, 0);
							log.info("*【跑批失败】...{}客户名称已存在：" +item.getCustomerName());
						}
					}
				} catch (Exception e) {
					log.error("*【跑批异常】...{1}" +e.getMessage());
				}
			}
			else{
				log.info("*【跑批客户】...{}Null");
			}
			} catch (Exception e) {
				log.error("*【跑批异常】...{0}" +e.getMessage());
			}
	}
	
}
