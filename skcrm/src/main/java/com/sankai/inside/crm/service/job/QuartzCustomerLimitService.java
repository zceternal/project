package com.sankai.inside.crm.service.job;

import com.sankai.inside.crm.entity.AccountHaveCustomer;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.DeptAndManagerDTO;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.service.MessageService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 账号的客户总数量限制 
 * @author Zzc
 * 2016-06-22
 */
@Service
public class QuartzCustomerLimitService implements Job {
	private static final Logger log=LoggerFactory.getLogger(QuartzCustomerLimitService.class);
	
	@Value("${CUSTOMER_LIMIT_MAX}")
	private Integer customerLimitMax;//客户上限
	
	@Value("${CUSTOMER_LIMIT_MIN}")
	private Integer customerLimitMin;//客户下限
	
	@Value("${LIMIT_DEPT}")
	private String limitDept;//受限制部门【为空则全部部门 都受影响】
	
	@Value("${DEFAULT_USERID}")
	private Integer defaultManagerId;//默认顶级部门领导人【为空则默认lhh】 ,必须为数字或空
	
	
	@Resource
	private DepartmentService departmentService;//部门管理
	
	@Resource
	private AccountService accountService;//用户管理
	
	@Resource
	private MessageService messageService;//消息管理
	
	/**
	 * 初始化
	 */
	private void Init()
	{
		if(defaultManagerId==null)defaultManagerId = 0;
		if(customerLimitMax == null || customerLimitMax<=0) customerLimitMax = 90;
		if(customerLimitMin == null || customerLimitMin<=0) customerLimitMin = 30;
		if(customerLimitMax<customerLimitMin)
		{
			customerLimitMax = customerLimitMax + customerLimitMin;
			customerLimitMin = customerLimitMax - customerLimitMin;
			customerLimitMax = customerLimitMax - customerLimitMin;
		}
	}
	
	public void execute()
	{
		
		Init();
		
		log.info("*********************【客户限制监测 ...start】**********************");
		try {
			List<AccountHaveCustomer> list = new ArrayList<AccountHaveCustomer>();
			//为空则全部部门 都受影响
			if(limitDept.isEmpty()) {
				log.info("*【监测】全部部门...start\n");
				list = accountService.getAccountHaveCustomer(null);
			}else{
				
				String deptIds = getDeptIds();
				log.info("*【监测】部分部门...部门Id集合：{}",deptIds);
				list = accountService.getAccountHaveCustomer(deptIds);
			}
				if(!list.isEmpty())
				{
					for (AccountHaveCustomer item: list) {
						Integer count = item.getCustoemrCount();
						if(count==null || count<customerLimitMin)
						{
							//客户量低于下限
							log.info("*【监测】客户量低于下限,用户名称：{}",item.getName());
							sendMessage(item,0,list);
						}else 
						{
							if(count>customerLimitMax)
							{
								//客户量超过上限
								log.info("*【监测】客户量超过上限,用户名称：{}",item.getName());
								sendMessage(item,1,list);
							}
						}
					}
				}
				else{
					log.info("*【监测】客户限制:Null");
				}
			log.info("*********************【客户限制监测 ...end】**********************");
		} catch (Exception e) {
			log.error("*【监测】客户异常:{}",e.getMessage());
		}
	}
	/**
	 * 递归获取部门id
	 * @param pid  
	 * @param list
	 * @return
	 */
	private String getChilds(Integer pid,List<Department> list){
		List<Department> l1 = list.stream().filter(x->x.getPid()==pid).collect(Collectors.toList());
		if(list.isEmpty()) return null;
		String ids = "";
		for (Department department : l1) {
			ids += department.getId()+",";
			ids += getChilds(department.getId(),list);
		}
		return ids;
	}
	
	/**
	 * 获取受限部门id【包含父级，及父级下的所有子集】
	 * @return
	 */
	private String getDeptIds()
	{
		String depts = limitDept+",";
		List<Department> list = departmentService.getAll();
		if(list.isEmpty()) return null;
		List<Department> l1 = list.stream().filter(x->limitDept.contains(String.valueOf(x.getId()))).collect(Collectors.toList());
		if(!l1.isEmpty())
		{
			for (Department department : l1) {
				depts += getChilds(department.getId(),list);	
			}
		}
		depts = depts.replace(",,", ",");
		if(depts.lastIndexOf(",")==(depts.length()-1))
			depts = depts.substring(0, depts.lastIndexOf(","));
		return depts;
	}
	/**
	 * 发送信息
	 * @param model
	 * @param i
	 * @param list
	 */
	private void sendMessage(AccountHaveCustomer model,int i,List<AccountHaveCustomer> list)
	{
		if(model==null) return ;
		//发送消息给自己
		int[] ids = new int[1];
		ids[0] = model.getId();
		//发送消息给部门领导
		int[] pids = new int[1];
		
		String content = "";
		if(i==1)//上限
		{
			////发送消息 给自己
			log.info("*【监测】客户量超过上限...{发送消息 给自己}用户Id："+ids[0]);
			content = String.format("<h2>客户数量提醒</h2>  您的客户数量【%s】  系统要求客户数量上限【%s】 <br/> 您的客户太多了，请删减。", model.getCustoemrCount(),customerLimitMax);
			messageService.sendMessage("系统提醒-超过客户上限",content , -100, ids, 0);
			
			////发送消息 给上级
			pids[0] = getManagerId(model,list);
			log.info("*【监测】客户量超过上限...{发送消息 给上级}用户Id："+pids[0]);
			content = String.format("<h2>客户数量提醒</h2>  【%S】的客户数量【%s】  系统要求客户数量上限【%s】 <br/> Ta的客户太多了，请删减。",model.getName(), model.getCustoemrCount(),customerLimitMax);
			messageService.sendMessage("系统提醒-超过客户上限-"+model.getName(),content , -100, pids, 0);
		}else {//下限
			
			////发送消息 给自己
			log.info("*【监测】客户量低于下限...{发送消息 给自己}用户Id："+ids[0]);
			content = String.format("<h2>客户数量提醒</h2>  您的客户数量【%s】  系统要求客户数量下限【%s】 <br/> 您的客户太少了，请增加。", model.getCustoemrCount(),customerLimitMin);
			messageService.sendMessage("系统提醒-低于客户下限", content , -100, ids, 0);
			
			////发送消息 给上级
			pids[0] = getManagerId(model,list);
			log.info("*【监测】客户量低于下限...{发送消息 给上级}用户Id："+pids[0]);
			content = String.format("<h2>客户数量提醒</h2>  【%S】的客户数量【%s】  系统要求客户数量下限【%s】 <br/> Ta的客户太少了，请增加。",model.getName(), model.getCustoemrCount(),customerLimitMin);
			messageService.sendMessage("系统提醒-低于客户下限-"+model.getName(),content , -100, pids, 0);
		}
	}
	
	/**
	 * 获取当前用户的部门领导人id
	 * @param model
	 * @param list
	 * @return
	 */
	private Integer getManagerId(AccountHaveCustomer model,List<AccountHaveCustomer> list)
	{
		if(model.getIsDeptManager()==0)
		{
			return model.getManagerId()==null?getDeptManager(model.getDeptPid()):model.getManagerId();
		}else{
			Optional<AccountHaveCustomer> ahc = list.stream().filter(x->x.getDeptId()==model.getDeptPid() || x.getIsDeptManager()==1).findFirst();
			if(ahc==null)
			{
				return defaultManagerId;
			}else{
				AccountHaveCustomer temp = ahc.get();
				if(temp.getManagerId()==null)
				{
					return getDeptManager(temp.getDeptPid());
				}else 
				{
					return temp.getManagerId();
				}
			}
		}
	}
	
	/**
	 * 根据部门id，递归获取领导人信息
	 * @param deptId
	 * @return
	 */
	public Integer getDeptManager(Integer deptPid) {
		List<DeptAndManagerDTO> depts = departmentService.getDeptAndManagerList();
		if(depts.isEmpty() || deptPid == null) return defaultManagerId;
		Optional<DeptAndManagerDTO> dept = depts.stream().filter(x->x.getDeptId()==deptPid).findFirst();
		if(!dept.isPresent())return defaultManagerId;
		DeptAndManagerDTO model = dept.get();
		Integer managerId = 0;
		if(model.getManagerId()==null)
		{
			if(model.getDeptPId()==0) return 0;
			managerId =  getDeptManager(model.getDeptPId());
		}
		else
		{
			managerId = model.getManagerId();
		}
		return managerId;
	 }

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	} 
}
