package com.sankai.inside.crm.service.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sankai.inside.crm.dao.ICustomerPoolDAO;
/**
 * 每天凌晨1点跑批  公海
 * @author Jack 
 *
 */
@Service
public class CustomerPoolJob implements Job {
	private static final Logger log=LoggerFactory.getLogger(CustomerPoolJob.class); 
	@Resource
	private ICustomerPoolDAO customerPoolDAOImpl;
	
	@Value("${START_FOLLOW}")
	private Integer startFollow;//初步沟通
	@Value("${FACE_TO_FACE}")
	private Integer faceToFace;//见面拜访
	@Value("${PURCHASE_INTENTION}")
	private Integer purchaseIntention;//确定意向
	@Value("${QUOTED_CUS}")
	private Integer quotedCus;//正式报价
	@Value("${NEGOTIATIONS_CUS}")
	private Integer negotiationsCus;//商务洽谈
	@Value("${NOT_CONTRACT}")
	private Integer notContract;//未签约成功的，到期直接掉入公海 【默认180天】
	@Value("${SUCCESS_CONTRACT}")
	private String successContract;//成功签约标识
	@Value("${NOT_ACCOUNTS}")
	private String notAccounts;//不受公海影响的用户id，此集合下的用户所创建的客户永不掉入公海
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub

	}
	/**
	 * 参数初始化操作
	 */
	public void Init()
	{
		if(notContract==null || notContract <=0) notContract = 180;
		
		if(successContract.isEmpty()) 
		{
			successContract = null;
		}else{
			successContract = successContract.replace(",,", ",");
			if(successContract.lastIndexOf(",")==(successContract.length()-1))
				successContract = successContract.substring(0, successContract.lastIndexOf(","));
		}
		if(notAccounts.isEmpty()){
			notAccounts = null;
		}else{
			notAccounts = notAccounts.replace(",,", ",");
			if(notAccounts.lastIndexOf(",")==(notAccounts.length()-1))
				notAccounts = notAccounts.substring(0, notAccounts.lastIndexOf(","));
		}
	}
	public void execute() throws JobExecutionException{
		
		Init();
		
		long start=System.currentTimeMillis();
		Date date=new Date();
 		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		log.info("【定时】客户公海跑批操作...");
		
		log.info("*****************************************************************************************************************");
		log.info("*【定时】跑批开始...start\n");
		log.info("*【跑批时间】..." +sdf.format(date));
		boolean isRunFlag=false;
		try {
			Integer i=this.customerPoolDAOImpl.isRun();
			
			if(i==0){
				isRunFlag=true;
			}
			log.info("*【查看今天是否执行过跑批】..."+(isRunFlag?"未执行":"已执行"));
		} catch (Exception e) {
			log.error("############################【定时公海跑批】【异常】【start】############################");
			e.printStackTrace();
			log.error(e.toString());
			log.error("############################【定时公海跑批】【异常】【end】############################");
		}
		if(isRunFlag){
			
		
			
			log.info("*【获取需要进入公海的客户ID】...start");
			String checkDate=null;
			//初步沟通
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			Map<String,Object> map1=new HashMap<String,Object>();
			cal.add(Calendar.DAY_OF_MONTH, 0-startFollow);
			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH)+1;
			int day=cal.get(Calendar.DAY_OF_MONTH);
			
			checkDate=year+"-"+month+"-"+day;
 			map1.put("checkDate", checkDate);
			map1.put("statusType", 38);//字典数据
			map1.put("notAccounts", notAccounts);
			
		
			try {
				
				
				log.info("*【初步沟通需要移入公海客户 】...................................start");
				List<Integer> list1=this.customerPoolDAOImpl.getCostomerIdsUnfollowed(map1);
				log.info("*【移动数量】..."+list1.size());
				for(int x=0;x<list1.size();x++){
					Integer cusId=list1.get(x);
					log.info("*【cusid】..."+cusId);
					Integer moveI=this.customerPoolDAOImpl.moveCustomerToPool(cusId);
					boolean flag=moveI>0;
					log.info("*【处理结果】..."+flag);
				}
				log.info("*【初步沟通需要移入公海客户 】...................................end");
				
				log.info("*【见面拜访需要移入公海客户 】...................................start");
				cal.setTime(date);
				Map<String,Object> map2=new HashMap<String,Object>();
				cal.add(Calendar.DAY_OF_MONTH, 0-faceToFace);
				year=cal.get(Calendar.YEAR);
				month=cal.get(Calendar.MONTH)+1;
				day=cal.get(Calendar.DAY_OF_MONTH);
				
				checkDate=year+"-"+month+"-"+day;
				map2.put("checkDate", checkDate);
				map2.put("statusType", 39);//字典数据
				map2.put("notAccounts", notAccounts);
				
				List<Integer> list2=this.customerPoolDAOImpl.getCostomerIdsUnfollowed(map2);
				log.info("*【移动数量】..."+list2.size());
				for(int x=0;x<list2.size();x++){
					Integer cusId=list2.get(x);
					log.info("*【cusid】..."+cusId);
					Integer moveI=this.customerPoolDAOImpl.moveCustomerToPool(cusId);
					boolean flag=moveI>0;
					log.info("*【处理结果】..."+flag);
				}
				log.info("*【见面拜访需要移入公海客户 】...................................end");
				
				log.info("*【确定意向需要移入公海客户 】...................................start");
				cal.setTime(date);
				Map<String,Object> map3=new HashMap<String,Object>();
				cal.add(Calendar.DAY_OF_MONTH, 0-purchaseIntention);
				year=cal.get(Calendar.YEAR);
				month=cal.get(Calendar.MONTH)+1;
				day=cal.get(Calendar.DAY_OF_MONTH);
				
				checkDate=year+"-"+month+"-"+day;
				map3.put("checkDate", checkDate);
				map3.put("statusType", 40);//字典数据
				map3.put("notAccounts", notAccounts);
				
				List<Integer> list3=this.customerPoolDAOImpl.getCostomerIdsUnfollowed(map3);
				log.info("*【移动数量】..."+list3.size());
				for(int x=0;x<list3.size();x++){
					Integer cusId=list3.get(x);
					log.info("*【cusid】..."+cusId);
					Integer moveI=this.customerPoolDAOImpl.moveCustomerToPool(cusId);
					boolean flag=moveI>0;
					log.info("*【处理结果】..."+flag);
				}
				log.info("*【确定意向需要移入公海客户 】...................................end");
				
				log.info("*【正式报价需要移入公海客户 】...................................start");
				cal.setTime(date);
				Map<String,Object> map4=new HashMap<String,Object>();
				cal.add(Calendar.DAY_OF_MONTH, 0-quotedCus);
				year=cal.get(Calendar.YEAR);
				month=cal.get(Calendar.MONTH)+1;
				day=cal.get(Calendar.DAY_OF_MONTH);
				
				checkDate=year+"-"+month+"-"+day;
				map4.put("checkDate", checkDate);
				map4.put("statusType", 41);//字典数据
				map4.put("notAccounts", notAccounts);
				
				List<Integer> list4=this.customerPoolDAOImpl.getCostomerIdsUnfollowed(map4);
				log.info("*【移动数量】..."+list4.size());
				for(int x=0;x<list4.size();x++){
					Integer cusId=list4.get(x);
					log.info("*【cusid】..."+cusId);
					Integer moveI=this.customerPoolDAOImpl.moveCustomerToPool(cusId);
					boolean flag=moveI>0;
					log.info("*【处理结果】..."+flag);
				}
				log.info("*【正式报价需要移入公海客户 】...................................end");
				
				
				log.info("*【商务洽谈需要移入公海客户 】...................................start");
				cal.setTime(date);
				Map<String,Object> map5=new HashMap<String,Object>();
				cal.add(Calendar.DAY_OF_MONTH, 0-negotiationsCus);
				year=cal.get(Calendar.YEAR);
				month=cal.get(Calendar.MONTH)+1;
				day=cal.get(Calendar.DAY_OF_MONTH);
				
				checkDate=year+"-"+month+"-"+day;
				map5.put("checkDate", checkDate);
				map5.put("statusType", 42);//字典数据
				map5.put("notAccounts", notAccounts);
				
				List<Integer> list5=this.customerPoolDAOImpl.getCostomerIdsUnfollowed(map5);
				log.info("*【移动数量】..."+list5.size());
				for(int x=0;x<list5.size();x++){
					Integer cusId=list5.get(x);
					log.info("*【cusid】..."+cusId);
					Integer moveI=this.customerPoolDAOImpl.moveCustomerToPool(cusId);
					boolean flag=moveI>0;
					log.info("*【处理结果】..."+flag);
				}
				log.info("*【商务洽谈需要移入公海客户 】...................................end");
				
				
				log.info("*【非签约成交需要移入公海客户 】...................................start");
			
				List<Integer> list6=this.customerPoolDAOImpl.getCostomerIdsUnSigned(successContract,notContract,notAccounts);//获取没有签约切距离创建时间已经超过180天的客户集合
				log.info("*【移动数量】..."+list6.size());
				for(int x=0;x<list6.size();x++){
					Integer cusId=list6.get(x);
					log.info("*【cusid】..."+cusId);
					Integer moveI=this.customerPoolDAOImpl.moveCustomerToPool(cusId);
					boolean flag=moveI>0;
					log.info("*【处理结果】..."+flag);
				}
				log.info("*【非签约成交需要移入公海客户 】...................................end");
				
				log.info("*【增加跑批记录】...");
				Integer updateStateI=this.customerPoolDAOImpl.doCreateQuartzRecord();
				log.info("*【增加跑批记录 msg】..."+updateStateI);
				
				
			} catch (Exception e) {
				log.error("############################【定时公海跑批】【异常】【start】############################");
				e.printStackTrace();
				log.error(e.toString());
				log.error("############################【定时公海跑批】【异常】【end】############################");
			}
			
			log.info("*【客户ID数量】...start");
			log.info("用时"+(System.currentTimeMillis()-start)*0.001+"s");
			log.info("*【获取需要进入公海的客户ID】...end");
			log.info("*****************************************************************************************************************");
			
			
		}
		
	
		
	}

}
