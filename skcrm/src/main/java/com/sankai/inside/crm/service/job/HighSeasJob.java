package com.sankai.inside.crm.service.job;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sankai.inside.crm.dao.IContactShareDAO;
import com.sankai.inside.crm.dao.ICustomerPoolDAO;
import com.sankai.inside.crm.dao.ICustomerRemindDAO;
import com.sankai.inside.crm.entity.ContactShare;
import com.sankai.inside.crm.entity.CustomerNoTraceDto;
import com.sankai.inside.crm.entity.CustomerRemindDTO;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.ContactService;
/**
 * 公海及提醒管理
 * 签约提醒：以30天为期限，在剩余10天时间每2天提醒一次需要跟踪客户，如果30天内没有跟踪，则掉入公海
 * @author Jack 
 *
 */
@Service
public class HighSeasJob implements Job {
	private static final Logger log=LoggerFactory.getLogger(HighSeasJob.class);

	@Value("${NOT_ACCOUNTS}")
	private String notAccounts;//不受公海影响的用户id，此集合下的用户所创建的客户永不掉入公海
	@Value("${SUCCESS_CONTRACT}")
	private String successContract;//成功签约标识
	@Value("${NOT_TRACE_DAY}")
	private Integer notTraceDay;//未跟踪的天数
	@Resource
	private ICustomerPoolDAO customerPoolDAOImpl;//客户公海
	@Resource
	private ICustomerRemindDAO customerRemindDAO;//客户提醒
	@Autowired
	private IContactShareDAO contactShareDAO;//联系人
	 
	/**
	 * 参数初始化操作
	 */
	public void Init()
	{
		if(notTraceDay==null || notTraceDay <=0) notTraceDay = 20;
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
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	
	}
	public void execute() {
		try {
			Init();
		
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			log.info("****【定时跑批开始...start】:{}",sdf.format(date));
			boolean isRunFlag=false;
			try {
				Integer i=this.customerPoolDAOImpl.isRun();
				if(i==0){
					isRunFlag=true;
				}
				log.info("*【查看今天是否执行过跑批】..."+(isRunFlag?"未执行":"已执行"));
			} catch (Exception e) {
				log.error("##############定时公海跑批-异常：{}",e);
			}
			//isRunFlag = true;
			if(isRunFlag){
				CustomerNoTraceDto cntd = new CustomerNoTraceDto();
				cntd.setDay(notTraceDay);
				cntd.setExcludeStatusList(Arrays.asList(successContract.split(",")));
				cntd.setExcludeAccIdList(Arrays.asList(notAccounts.split(",")));
				List<CustomerNoTraceDto> list =customerPoolDAOImpl.selectCustomerNoTrace(cntd);
				if(CollectionUtils.isEmpty(list))return;
				for (CustomerNoTraceDto item : list) {
					Integer customerId = item.getCustomerId();
					if(item.getDiffDay()>30){//掉入公海
						try {
							Integer moveI = customerPoolDAOImpl.moveCustomerToPool(customerId);
							// 客户相关联系人投入公海
							injectHighSeasByCusId(customerId,1);
							boolean flag=moveI>0;
							log.info("*【处理结果】..."+flag);
						} catch (Exception e) {
							log.error("##############定时公海跑批-移到公海客户Id:{}-异常：{}",customerId,e);
						}
					}else{//添加提醒【注：天数为奇数添加，偶数不添加】
						Calendar cal=Calendar.getInstance();    
						int day = cal.get(Calendar.DATE);
						//偶数
						if(day%2==0) continue;
						CustomerRemindDTO dto = new CustomerRemindDTO();
						dto.setCustomerId(customerId);
						dto.setCreateTime(date);
						dto.setCreateId(item.getAccountId());
						dto.setRemindTime(date);
						dto.setType(0);
						dto.setState(0);
						dto.setRemark("公海提醒：客户已经很久没有跟进了，可能会掉入公海，请尽快跟进!");
						customerRemindDAO.insert(dto);
					}
				}
				//增加公海跑批记录
				Integer updateStateI=customerPoolDAOImpl.doCreateQuartzRecord();
				log.info("*【增加跑批记录 msg】..."+updateStateI);
			}
		} catch (Exception e) {
			log.error("############################【定时公海跑批】【异常】【start】############################");
			log.error(e.toString());
			log.error("############################【定时公海跑批】【异常】【end】############################");
		}
		
	}
	public ServiceResultBool injectHighSeasByCusId(Integer customerId,int state){
		ContactShare dto = new ContactShare();
		dto.setCustomerId(customerId);
		dto.setState(state);
		Integer i = contactShareDAO.injectHighSeasByCusId(dto);
		if (i == null) {
			log.info("##############定时公海跑批-投入公海失败##############");
			return new ServiceResultBool("投入公海失败");
		}
		return new ServiceResultBool();
	}
}
