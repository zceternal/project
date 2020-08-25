package com.sankai.inside.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.sankai.inside.crm.dao.*;
import com.sankai.inside.crm.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sankai.inside.crm.core.utils.DateUtil;
import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.service.ContactService;
import com.sankai.inside.crm.service.CustomerStatusTimeService;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.web.core.TraceType;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.ContactForm;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Resource
	private ICustomerDAO customerDAO;
	@Resource
	private ICustomerTransferDAO customerTransferDAO;
	@Autowired
	private IAccountDAO accountDAO;
	@Resource
	private ICustomerShareDAO customerShareDAO;
	@Resource
	private ICustomerLogDAO customerLogDAO;//客户日志
	@Resource
	private ContactService contactService;//联系人
	@Resource
	private IContactDAO contactDAO;
	@Resource
	private CustomerStatusTimeService customerStatusTimeService;//客户状态停留时间
	@Resource
	private ICustomerRecordDAO customerRecordDAO;// 跟踪记录
	@Autowired
	private ITaskDAO taskDAO;// 任务

	@Transactional
	@Override
	public ServiceResultBool saveCommunication(CommunicationForm model) {

		String cusSource = model.getCusSource();
		if (!StringUtils.isEmpty(cusSource)) {
			String[] cs = cusSource.split("_");
			model.setCusSource(cs[0]);
			model.setCusSourceType(cs[1]);
		}

		Date date = new Date();
		// 修改客户信息
		Customer customer = new Customer();
		BeanUtils.copyProperties(model, customer);
		customer.setProvince(null);
		customer.setCity(null);
		customer.setCountry(null);
		customer.setCreateId(null);
		customerDAO.updateByPrimaryKeySelective(customer);

		// 新增或修改人际关系图
		if (!StringUtils.isEmpty(model.getChannelPartner()) || !StringUtils.isEmpty(model.getTrustPerson())
				|| !StringUtils.isEmpty(model.getDecisionPerson()) || !StringUtils.isEmpty(model.getManagePerson())
				|| !StringUtils.isEmpty(model.getHandlePerson()) || !StringUtils.isEmpty(model.getProfessionalPerson())) {
			if (customer.getRelationsId() > 0) {// 修改
				SysCustomerRelations relation = new SysCustomerRelations();
				BeanUtils.copyProperties(model, relation);
				relation.setId(model.getRelationsId());
				customerDAO.updateCustomerRelations(relation);
			}else{ // 新增
				SysCustomerRelations relation = new SysCustomerRelations();
				BeanUtils.copyProperties(model, relation);
				relation.setCustomerId(model.getId());
				customerDAO.insertCustomerRelations(relation);
			}
		}

		// 新增跟踪日志(type=6 [默认销售记录]，source=1 [默认PC])
		if (!StringUtils.isEmpty(model.getReportRemark())) {
			CustomerRecord customerRecord = new CustomerRecord();
			customerRecord.setRemark(model.getReportRemark());
			customerRecord.setCommunicationWay(model.getCommunicationWay());
			customerRecord.setCommunicationTime(model.getCommunicationTime());
			customerRecord.setType(6);
			customerRecord.setSource(1);
			customerRecord.setAccountId(model.getUserId());
			customerRecord.setCustomerId(model.getId());
			customerRecord.setCreateTime(date);
			customerRecordDAO.insertRecord(customerRecord);
		}

		// 新增任务(任务名称=“新增沟通记录任务”)
		if (!StringUtils.isEmpty(model.getNextPlan()) || !StringUtils.isEmpty(model.getPlanStandard())) {
			Task task = new Task();
			task.setName("新增沟通记录任务");
			task.setCustomerType("167");// 现有客户
			task.setCustomerId(model.getId());
			task.setNextPlan(model.getNextPlan());
			task.setPlanStandard(model.getPlanStandard());
			task.setPlanExecutorUser(model.getPlanExecutorUser());
			task.setPlanExecutorContact(model.getPlanExecutorContact());
			task.setExecuteWay(model.getExecuteWay());
			task.setQuadrant(model.getQuadrant());
			task.setBackTime(model.getBackTime());
			task.setBackWay(model.getBackWay());
			task.setAssignTime(date);
			task.setAssignPerson(String.valueOf(model.getUserId()));
			task.setCreateId(model.getUserId());
			task.setCreateName(model.getUserName());
			task.setCreateTime(date);
			task.setSource(1);
			taskDAO.insert(task);
		}

		return new ServiceResultBool();
	}

	@Override
	public CustomerTransDTO findCustomerInfoById(Integer id) throws Exception {
		return this.customerDAO.findCustomerInfoById(id);
	}

	@Override
	public List<CustomerShareTransDTO> findAllows(Integer customerId) throws Exception {
		return this.customerDAO.findAllows(customerId);
	}

	@Override
	public CustomerShareTransDTO findFirstAllow(Integer customerId) throws Exception {
		// TODO Auto-generated method stub
		return this.customerDAO.findFirstAllow(customerId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateStatus(Integer customerId, Integer status) {
		UpdateCustomerStatusDTO dto = new UpdateCustomerStatusDTO();
		dto.setCustomerId(customerId);
		dto.setStatus(status);
		opeCustomerLog(customerId, UserState.getLoginId()+"", 5);	
		//修改状态的天数，新增最新状态
		customerStatusTimeUA(customerId,status);
		
		return this.customerDAO.updateStatus(dto) > 0;
	}
	

	public List<Contact> findContactInfoByCustoemrId(Integer customerId) throws Exception {
		return this.customerDAO.findContactInfoByCustoemrId(customerId);
	}
	
	@Override
	public ServiceResult<Page<CustomerList>> getList(CustomerListSearch val, int page, int pageSize) {
			if (Objects.equals(val.getOrderField(), "buyServiceName")) {
			val.setOrderField("buyService");
		}else if (Objects.equals(val.getOrderField(), "followStateName")) {
			val.setOrderField("followState");
		}else if (Objects.equals(val.getOrderField(), "cusSourceName")) {
			val.setOrderField("cusSource");
		}
		PageHelper.startPage(page, pageSize, true);
		Page<CustomerList> list = (Page<CustomerList>) customerDAO.selectList(val);
		for (CustomerList customer : list) {
			//联系人转换
			if (Objects.equals(customer.getCusSourceType(), "1")) {
				ServiceResult<Contact> contactServiceResult = contactService.selectById(Integer.valueOf(customer.getCusSource()));
				if (contactServiceResult.isSuccess()) {
					customer.setCusSource(contactServiceResult.getData().getName());
				}
			}

			//日期格式化
			if (!Objects.isNull(customer.getFinalTime())) {
				SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
				String myDate = dateFormater.format(customer.getFinalTime());
				customer.setTraceName(TraceType.CalcValue(myDate).getName());
			}

			//最近一次推进记录
//			CustomerRecord lastReport = customerRecordDAO.getLastReport(customer.getCustomerId());
//			if (!Objects.isNull(lastReport)) {
//				customer.setNextReport(lastReport.getRemark());
//			}
			//下一步计划（任务）
			Task lastTask = taskDAO.getLastTask(customer.getCustomerId());
			if (!Objects.isNull(lastTask)) {
				String planExecutorAll = "";
				if (!Objects.isNull(lastTask.getPlanExecutorContact())) {
					List<String> ids = Arrays.asList(lastTask.getPlanExecutorContact().split(","));
					planExecutorAll = contactDAO.getNames(ids);
				}
				if (!Objects.isNull(lastTask.getPlanExecutorUser())) {
					String names = accountDAO.getNames(lastTask.getPlanExecutorUser());
					planExecutorAll  = StringUtils.isEmpty(planExecutorAll)?names:names+","+planExecutorAll;
				}
				customer.setNextPlan(lastTask.getNextPlan());
				customer.setPlanExecutorAll(planExecutorAll);
				// 任务状态
				int status = lastTask.getStatus();
				//0 正常	<=7 超期7天	 7>and<=14 超期14天	14>超期28天	搁置
				if (Objects.isNull(status)) {
					customer.setStatus("搁置");
				} else if (status < 0) {
					customer.setStatus("搁置");
				}else if (status == 0) {
					customer.setStatus("正常");
				}else if (status >0 && status < 14) {
					customer.setStatus("超期7天");
				}else if (status >=14 && status < 28) {
					customer.setStatus("超期14天");
				}else if (status >=28) {
					customer.setStatus("超期28天");
				}
			}else{
				customer.setStatus(null);
			}
		}
		return new ServiceResult<Page<CustomerList>>(list);
	}
	@Override
	public ServiceResult<List<CustomerList>> getListForExcel(CustomerListSearch val) {
		
		List<CustomerList> list = customerDAO.selectListForExcel(val);
		for (CustomerList tmp : list) {
			if (tmp.getFinalTime() == null)
				continue;
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			String myDate = dateFormater.format(tmp.getFinalTime());
			tmp.setTraceName(TraceType.CalcValue(myDate).getName());
		}
		return new ServiceResult<List<CustomerList>>(list);
	}
	@Override
	public List<Contact> findContactShared(Integer customerId) throws Exception {
		ContactShareRequestDTO dto = new ContactShareRequestDTO();
		dto.setCustomerId(customerId);
		dto.setLoginId(UserState.getLoginId());
		return this.customerDAO.findContactShared(dto);
	}

	@Override
	public boolean checkIsOwn(Integer customerId) throws Exception {
		CustomerOwnCheckDTO dto = new CustomerOwnCheckDTO();
		dto.setCustomerId(customerId);
		dto.setLoginId(UserState.getLoginId());
		Integer i = this.customerDAO.checkIsOwn(dto);
		return i > 0;
	}

	@Override
	public ServiceResultBool addCustomer(Customer model){
		String cusSource = model.getCusSource();
		if (!StringUtils.isEmpty(cusSource)) {
			String[] cs = cusSource.split("_");
			model.setCusSource(cs[0]);
			model.setCusSourceType(cs[1]);
		}

		if (customerDAO.existsByName(model.getName()))
			return new ServiceResultBool("客户名称已存在");

		if (customerDAO.existsByShortName(model.getShortName()))
			return new ServiceResultBool("客户简称已存在");

		if(model.getCreateId()!=null&&model.getCreateId()>0){}
		else{model.setCreateId(UserState.getLoginId());}
		if(model.getCreateTime()==null)model.setCreateTime(new Date());
		model.setState(0);
		model.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		model.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		if(!StringUtils.isEmpty(model.getUrl()) && !model.getUrl().startsWith("http")){
			model.setUrl("http://" + model.getUrl());
		}
		// 1新增
		 customerDAO.insertCustomer(model);
		// 新增id
		int newId = model.getId();
		if (newId > 0) {
			// 新增人际关系
			SysCustomerRelations customerRelations = new SysCustomerRelations();
			BeanUtils.copyProperties(model, customerRelations);
			customerRelations.setCustomerId(newId);
			customerRelations.setId(null);
			customerDAO.insertCustomerRelations(customerRelations);

			// 2新增客户日志
			opeCustomerLog(newId,UserState.getLoginId()+"",0);
			// 3新增客户状态时间表
			opeCustomerStatus(model);
			// 4新增客户共享表

			int allowAccountId = model.getCreateId();

			Integer maxOrder = customerDAO.selectMaxOrder(allowAccountId);
			if (maxOrder == null)
				maxOrder = 0;
			maxOrder += 1;

			CustomerShare modelShare = new CustomerShare();
			modelShare.setCustomerId(newId);
			modelShare.setAccountId(allowAccountId);
			modelShare.setAllowAccountId(allowAccountId);
			modelShare.setCreateTime(new Date());
			modelShare.setOrder(maxOrder);
			modelShare.setIsOwn(1);
			modelShare.setState(0);
			modelShare.setIsShare(1);

			customerDAO.insertCustomerShare(modelShare);
			
			if ((!model.getContactName().equals(null)&&!model.getContactName().equals(""))&&(!model.getContactPhone().equals(null)&&!model.getContactPhone().equals(""))) {
				// 5新增联系人
				ContactForm contactModel=new ContactForm();
				contactModel.setCreateTime(model.getCreateTime());
				contactModel.setCustomerId(newId);
				contactModel.setName(model.getContactName());
				contactModel.setPhone(model.getContactPhone());
				//contactModel.setSex(model.getSex());
				contactModel.setSex(2);//0女；1男；2未知
				contactModel.setCreateId(model.getCreateId());
				contactModel.setRole(34);//默认普通人
				ServiceResultBool res= contactService.addImport(contactModel);
				if (!res.isSuccess()) {
					return new ServiceResultBool("保存成功！"+res.getMsg());
				}
			}
			
		}
		return new ServiceResultBool();
	}
	
	/**
	 * 导入客户数据   李肖
	 * */
	@Override
	public ServiceResult addCustomerImport(Customer model) {
	try {
		if (customerDAO.existsByName(model.getName()))
			return new ServiceResult("客户名称已存在"+model.getName());

		if (customerDAO.existsByShortName(model.getShortName()))
			return new ServiceResult("客户简称已存在"+model.getShortName());

		// TODO Auto-generated method stub
		model.setCreateId(UserState.getLoginId());
		
		//model.setCreateTime(model.getCreateTime());
		model.setState(0);
		model.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		model.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		if(!StringUtils.isEmpty(model.getUrl()) && !model.getUrl().startsWith("http")){
			model.setUrl("http://" + model.getUrl());
		}
		 customerDAO.insertCustomer(model);
		//新增客户日志
		opeCustomerLog(model.getId(),"",0);
		// 新增id
		int newId = model.getId();
		// 新增客户共享表
		if (newId > 0) {

			int allowAccountId = model.getCreateId();

			Integer maxOrder = customerDAO.selectMaxOrder(allowAccountId);
			if (maxOrder == null)
				maxOrder = 0;
			maxOrder += 1;

			CustomerShare modelShare = new CustomerShare();
			
			modelShare.setCustomerId(newId);
			modelShare.setAccountId(allowAccountId);
			modelShare.setAllowAccountId(allowAccountId);
			modelShare.setCreateTime(new Date());
			modelShare.setOrder(maxOrder);
			modelShare.setIsOwn(1);
			modelShare.setState(0);
			modelShare.setIsShare(1);

			customerDAO.insertCustomerShare(modelShare);
			return new ServiceResult(newId);
		}
	} catch (Exception e) {
		// TODO: handle exception
		return new ServiceResult(e+"错误客户:"+model.getName());
	}
	return null;
		
	}

	public ServiceResult<Customer> getCustomer(int id) {
		Customer customer = customerDAO.selectCustomerById(id);
		if (customer == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");

		return new ServiceResult<Customer>(customer);
	}

	@Override
	public ServiceResultBool addCustomerShare(CustomerShare model) {
		// TODO Auto-generated method stub
		/*
		 * model.setCreateId(1); model.setCreateTime(new Date());
		 * model.setState(0); customerDAO.insertCustomer(model);
		 */
		return new ServiceResultBool();
	}

	@Override
	public Integer getMaxOrder(int allowAccountId) {
		return customerDAO.selectMaxOrder(allowAccountId);
	}

	@Override
	public ServiceResult<Customer> getModelById(int id) {
		Customer model = customerDAO.selectCustomerById(id);
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Contact modelcontact = contactDAO.selectTopContact(id);
		if (modelcontact!=null) {
			model.setContactId(modelcontact.getId());
			model.setContactName(modelcontact.getName());
			model.setContactPhone(modelcontact.getPhone());
		}
		if (model == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");
		return new ServiceResult<Customer>(model);
	}

	@Override
	public ServiceResultBool saveCustomer(Customer model) {
		String cusSource = model.getCusSource();
		if (!StringUtils.isEmpty(cusSource)) {
			String[] cs = cusSource.split("_");
			model.setCusSource(cs[0]);
			model.setCusSourceType(cs[1]);
		}
		ServiceResult<Customer> oldModel = getCustomer(model.getId());
		Customer oldCustomer = oldModel.getData();
		if (oldCustomer!=null&&!model.getName().equals(oldCustomer.getName())) {
			if (customerDAO.existsByName(model.getName()))
				return new ServiceResultBool("客户名称已存在");
		}
		if (oldCustomer!=null&&!model.getShortName().equals(oldCustomer.getShortName())) {
			if (customerDAO.existsByShortName(model.getShortName()))
				return new ServiceResultBool("客户简称已存在");
		}
		if(!StringUtils.isEmpty(model.getUrl()) && !model.getUrl().startsWith("http")){
			model.setUrl("http://" + model.getUrl());
		}
		model.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		model.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		customerDAO.updateCustomer(model);

		// 新增人际关系
		SysCustomerRelations customerRelations = new SysCustomerRelations();
		BeanUtils.copyProperties(model, customerRelations);
		customerRelations.setCustomerId(model.getId());
		customerRelations.setId(model.getRelationsId());
		customerDAO.updateCustomerRelations(customerRelations);

		//修改状态的天数，新增最新状态
		customerStatusTimeUA(model.getId(),model.getStatus());
		if ((!model.getContactName().equals(null)&&!model.getContactName().equals(""))&&(!model.getContactPhone().equals(null)&&!model.getContactPhone().equals(""))) {
			//联系人
			ContactForm contactModel=new ContactForm();
			contactModel.setCreateTime(model.getCreateTime());
			contactModel.setName(model.getContactName());
			contactModel.setPhone(model.getContactPhone());
			contactModel.setCustomerId(model.getId());
			contactModel.setId(model.getContactId());
			ServiceResultBool res= contactService.update(contactModel);
			if (!res.isSuccess()) {
				return new ServiceResultBool("保存成功,联系人"+res.getMsg());
			}
		}
		
		return new ServiceResultBool();
	}

	@Override
	public ServiceResultBool remove(int id) {
		// 是否是销售负责人 【1是； 0或null 不是】
		Integer own = customerShareDAO.selectOwn(id);
		Customer model = customerDAO.selectByShareId(id);
		if (own == 1) {
			// 根据客户id 删除所有客户分享信息
			customerShareDAO.deleteShareByCustomerId(model.getId());
			// 根据客户id 删除客户信息
			customerDAO.deleteCustomer(model.getId());
			// 删除整个客户联系人信息
		} else {
			if (customerDAO.deleteCustomerShare(id) == 0)
				return new ServiceResultBool("删除失败，您需要删除的数据已不存在");
		}
		//新增客户日志
		opeCustomerLog(model.getId(),"",-1);
		return new ServiceResultBool();
	}

	@Override
	public ServiceResult<CustomerShare> getShareById(int shareId) {
		CustomerShare result = customerDAO.selectShareById(shareId);
		if (result == null)
			return new ServiceResult<>("数据已不存在，请刷新列表");
		return new ServiceResult<CustomerShare>(result);
	}

	@Override
	public void editShareOrder(int allowAccountId, int order) {
		customerDAO.updateShareOrder(allowAccountId, order);
	}

	@Override
	public void upOrder(int shareId) {
		ServiceResult<CustomerShare> result = getShareById(shareId);
		if (result == null)
			return;
		CustomerShare model = result.getData();
		int maxOrder = getMaxOrder(model.getAllowAccountId());// 最大order
		int order = model.getOrder();// 当前数据的order
		editShareOrder(model.getAllowAccountId(), order);// 后面的排序 -1 操作
		customerDAO.updateOrder(shareId, maxOrder);// 修改实体的 order
	}

	@Override
	public ServiceResultBool existsByName(String name) {

		if (customerDAO.existsByName(name))
			return new ServiceResultBool("客户名称已存在");
		return new ServiceResultBool();
	}

	@Override
	public ServiceResultBool existsByShortName(String shortName) {

		if (customerDAO.existsByShortName(shortName))
			return new ServiceResultBool("客户简称已存在");
		return new ServiceResultBool();
	}

	@Override
	public ServiceResultBool addCustomerTransfer(CustomerTransfer model) {
		model.setCreateTime(new Date());
		model.setAccountId(UserState.getLoginId());
		customerTransferDAO.insertTransfer(model);
		return new ServiceResultBool();
	}

	@Override
	public CustomerShare getShareBycidAndAllowId(int customerId, int allowAccountId) {
		return customerDAO.selectBycidAndAllowId(customerId, allowAccountId);
	}

	@Override
	public ServiceResultBool saveShare(CustomerShare model) {
		if (model.getAccountId() <= 0)
			model.setAccountId(model.getAllowAccountId()); // model.setAccountId(UserState.getLoginId());
		model.setCreateTime(new Date());
		if (customerDAO.updateShare(model) > 0)
			return new ServiceResultBool();
		return new ServiceResultBool("数据已不存在，请刷新列表");

	}

	@Override
	public Integer saveOrderState(int customerId, int allowAccountId) {
		return customerDAO.updateOrderState(customerId, allowAccountId);
	}

	@Override
	public Integer getIsShare(Integer customerId, Integer accountId) {
		if(customerId==null||accountId==null)return 0;
		return customerShareDAO.selectIsShare(customerId, accountId);
	}
	/**
	 * 新增客户日志记录
	 * @param customerId
	 * @param accountIds
	 * @param type
	 */
	private void opeCustomerLog(Integer customerId,String accountIds,Integer type)
	{
		//新增客户日志
		CustomerLog log = new CustomerLog();
		log.setCustomerId(customerId);
		log.setAccountIds(accountIds);
		log.setCreateId(UserState.getLoginId());
		log.setCreateTime(new Date());
		log.setType(type);
			
		customerLogDAO.insert(log);
	}
	/**
	 * 新增客户状态时间表
	 * @param customer
	 */
	private void opeCustomerStatus(Customer customer)
	{
		CustomerStatueTimeDto model = new CustomerStatueTimeDto();
		model.setCustomerId(customer.getId());
		model.setOperateTime(customer.getCreateTime());
		model.setSpendDay(0);
		model.setStatus(customer.getStatus());
		customerStatusTimeService.add(model);
	}
	/**
	 * 修改状态的天数，新增最新状态
	 * @param customerId
	 * @param status
	 */
	private void customerStatusTimeUA(Integer customerId, Integer status){
		List<CustomerStatueTimeDto> list = customerStatusTimeService.getList(customerId);
		if(CollectionUtils.isEmpty(list)) return;
		CustomerStatueTimeDto old = list.get(0);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(old!=null){
			if(old.getStatus()==status) return ;//标示没有修改客户状态
			//修改
			long difDays = 0;
			Date d1 = DateUtil.getDate(sdf.format(old.getOperateTime()));
			Date d2 = DateUtil.getDate(sdf.format(date));
			if(old.getOperateTime()!=null)difDays = DateUtil.compareDate4Day(d1,d2);
			old.setSpendDay(Integer.valueOf(String.valueOf(difDays)));
			customerStatusTimeService.modifyDay(old);
		}
		//新增
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setCreateTime(date);
		customer.setStatus(status);
		opeCustomerStatus(customer);
	}
	/**
	 * 当前用户的 客户总量
	 * @param accountId
	 * @return
	 */
	public Integer getTotalCustomerByAccountId()
	{
		return customerDAO.selectTotalCustomerByAccountId(UserState.getLoginId());
	}
	
	/**
	 * 当前用户的 联系人总量
	 * @param accountId
	 * @return
	 */
	public Integer getTotalContactByAccountId(){
		return customerDAO.selectTotalContactByAccountId(UserState.getLoginId());
	}
	
	/**
	 * N天未跟踪
	 * 注：如果start或end 有一个为null 则为未跟踪客户
	 * @param accountId
	 * @param start 起始天数【大于等于】
	 * @param end 结束天数【小于】
	 * @return
	 */
	public Integer getTotalTraceBy(Integer start,Integer end){
		return customerDAO.selectTotalTraceBy(UserState.getLoginId(), start, end);
	}

	public HomeCount getHomeCount(){
		
		HomeCount hc = new HomeCount();
		hc.setTotalCustomerCount(getTotalCustomerByAccountId());
		hc.setTotalContactCount(getTotalContactByAccountId());
//		hc.setTotalSevenTraceCount(getTotalTraceBy(7,10));
//		hc.setTotalTenTraceCount(getTotalTraceBy(10,15));
//		hc.setTotalFifteenTraceCount(getTotalTraceBy(15,30));
//		hc.setTotalThirtyTraceCount(getTotalTraceBy(30,60));
//		hc.setTotalSixtyTraceCount(getTotalTraceBy(60,365));
//		hc.setTotalNotTraceCount(getTotalTraceBy(null,null));\
		
		hc.setCustomerStatusList(customerDAO.selectStatusNo(UserState.getLoginId()));
		
		if(hc.getTotalCustomerCount()==null)hc.setTotalCustomerCount(0);
		if(hc.getTotalContactCount()==null)hc.setTotalContactCount(0);
//		if(hc.getTotalSevenTraceCount()==null)hc.setTotalSevenTraceCount(0);
//		if(hc.getTotalTenTraceCount()==null)hc.setTotalTenTraceCount(0);
//		if(hc.getTotalFifteenTraceCount()==null)hc.setTotalFifteenTraceCount(0);
//		if(hc.getTotalThirtyTraceCount()==null)hc.setTotalThirtyTraceCount(0);
//		if(hc.getTotalSixtyTraceCount()==null)hc.setTotalSixtyTraceCount(0);
//		if(hc.getTotalNotTraceCount()==null)hc.setTotalNotTraceCount(0);
		if(hc.getCustomerStatusList()==null)hc.setCustomerStatusList(new ArrayList<CustomerStatus>());
		return hc;
		
	}

	@Override
	public Integer getCustomerId(String customerName) {
		return customerDAO.selectCustomerId(customerName);
	}

	@Override
	public List<CustomerAutocomplate> getListAutocomplate(String value,String type) {
		AutocomplateEntity model=new AutocomplateEntity();
		model.setValue(value);
		model.setType(type);
		List<CustomerAutocomplate> list = (List<CustomerAutocomplate>) customerDAO.selectListForAutoComplate(model);
		
		return list;
	}

	/**
	 * 投入公海功能
	 * lix
	 * 20160621
	 * */
	@Override
	public ServiceResultBool injectHighSeas(Integer cusId) {
		// 客户投入公海
		if (customerDAO.injectHighSeas(cusId)<=0) {
			return new ServiceResultBool(false,"投入公海失败");
		}
		// 客户相关联系人投入公海
		contactService.injectHighSeasByCusId(cusId,1);
		
		/*//投入公海，判断是否是第一负责人【此功能已在一面判断】
		 * try {
			CustomerShareTransDTO model = findFirstAllow(cusId);
			Integer loginId = UserState.getLoginId();
			if(model!=null&&model.getAllowAccountId()==loginId)
			{
				opeCustomerLog(cusId, loginId+"", 4);	
			}else{
				return new ServiceResultBool(false,"共享客户不能投入公海，请您联系第一负责人！");
			}
			return new ServiceResultBool(true,"投入公海成功");
		} catch (Exception e) {
			return new ServiceResultBool(false,e.getMessage());
		}*/
		opeCustomerLog(cusId, UserState.getLoginId()+"", 4);	
		return new ServiceResultBool(true,"投入公海成功");
	}

	@Override
	public ServiceResultBool injectHighSeasBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		if(this.customerDAO.injectHighSeasBatch(ids)>0){
			for(Integer id:ids){
				// 客户相关联系人投入公海
				contactService.injectHighSeasByCusId(id,1);
				//插入客户之日信息
				opeCustomerLog(id, UserState.getLoginId()+"", 4);
			}
			return new ServiceResultBool(true,"投入公海成功");
		}
		return new ServiceResultBool(false,"投入公海失败");
		
	}

	@Override
	public SysCustomerRelations getCustomerRelations(Integer cusId) {
		SysCustomerRelations customerRelations = customerDAO.getCustomerRelations(cusId);
		if (!Objects.isNull(customerRelations)) {
			if (!Objects.isNull(customerRelations.getChannelPartner())) {
				List<String> ids = Arrays.asList(customerRelations.getChannelPartner().split(","));
				customerRelations.setChannelPartnerName(contactDAO.getNames(ids));
			}
			if (!Objects.isNull(customerRelations.getDecisionPerson())) {
				List<String> ids = Arrays.asList(customerRelations.getDecisionPerson().split(","));
				customerRelations.setDecisionPersonName(contactDAO.getNames(ids));
			}
			if (!Objects.isNull(customerRelations.getHandlePerson())) {
				List<String> ids = Arrays.asList(customerRelations.getHandlePerson().split(","));
				customerRelations.setHandlePersonName(contactDAO.getNames(ids));
			}
			if (!Objects.isNull(customerRelations.getManagePerson())) {
				List<String> ids = Arrays.asList(customerRelations.getManagePerson().split(","));
				customerRelations.setManagePersonName(contactDAO.getNames(ids));
			}
			if (!Objects.isNull(customerRelations.getProfessionalPerson())) {
				List<String> ids = Arrays.asList(customerRelations.getProfessionalPerson().split(","));
				customerRelations.setProfessionalPersonName(contactDAO.getNames(ids));
			}
			if (!Objects.isNull(customerRelations.getTrustPerson())) {
				List<String> ids = Arrays.asList(customerRelations.getTrustPerson().split(","));
				customerRelations.setTrustPersonName(contactDAO.getNames(ids));
			}
		}
		return customerRelations;
	}

	@Override
	public ServiceResultBool saveCustomerSim(Customer model) {
		if(customerDAO.updateCustomerSim(model)>0)return new ServiceResultBool();
		return new ServiceResultBool("修改失败");
	}

}
