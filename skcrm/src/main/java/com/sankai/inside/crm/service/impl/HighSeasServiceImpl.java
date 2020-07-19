package com.sankai.inside.crm.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sankai.inside.crm.core.utils.Pinyin4jUtil;
import com.sankai.inside.crm.dao.IAccountDAO;
import com.sankai.inside.crm.dao.IContactDAO;
import com.sankai.inside.crm.dao.ICustomerDAO;
import com.sankai.inside.crm.dao.ICustomerLogDAO;
import com.sankai.inside.crm.dao.ICustomerShareDAO;
import com.sankai.inside.crm.dao.ICustomerTransferDAO;
import com.sankai.inside.crm.dao.IHighSeasDAO;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.AutocomplateEntity;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.ContactShareRequestDTO;
import com.sankai.inside.crm.entity.Customer;
import com.sankai.inside.crm.entity.CustomerAutocomplate;
import com.sankai.inside.crm.entity.CustomerList;
import com.sankai.inside.crm.entity.CustomerListSearch;
import com.sankai.inside.crm.entity.CustomerLog;
import com.sankai.inside.crm.entity.CustomerOwnCheckDTO;
import com.sankai.inside.crm.entity.CustomerShare;
import com.sankai.inside.crm.entity.CustomerShareTransDTO;
import com.sankai.inside.crm.entity.CustomerTransDTO;
import com.sankai.inside.crm.entity.CustomerTransfer;
import com.sankai.inside.crm.entity.HomeCount;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.UpdateCustomerStatusDTO;
import com.sankai.inside.crm.service.ContactService;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.service.IHighSeasService;
import com.sankai.inside.crm.web.core.TraceType;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.ContactForm;

@Service
public class HighSeasServiceImpl implements IHighSeasService {
	@Resource
	private IHighSeasDAO customerDAO;
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
		
	@Override
	public CustomerTransDTO findCustomerInfoById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.customerDAO.findCustomerInfoById(id);
	}

	@Override
	public List<CustomerShareTransDTO> findAllows(Integer customerId) throws Exception {
		// TODO Auto-generated method stub
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
		return this.customerDAO.updateStatus(dto) > 0;
	}

	public List<Contact> findContactInfoByCustoemrId(Integer customerId) throws Exception {
		// TODO Auto-generated method stub
		return this.customerDAO.findContactInfoByCustoemrId(customerId);
	}

	@Override
	public ServiceResult<Page<CustomerList>> getList(CustomerListSearch val, int page, int pageSize) {
		val.setLoginId(UserState.getLoginId());//默认当前登录人
		if(UserState.isAdmin())//公海抢占权限，管理员不受限制
		{
			val.setExcludeAccountIds(String.valueOf(val.getLoginId()));
		}
		List<Account> accountList=new ArrayList<Account>();
		if(!StringUtils.isEmpty(val.getAllowAccountName())){
			Account account=new Account();
			account.setName(val.getAllowAccountName());
			accountList=accountDAO.selectaAllowByName(account);
			List<Integer> accountIds=new ArrayList<Integer>();
			if(!CollectionUtils.isEmpty(accountList)){
				for(Account e:accountList){
					accountIds.add(e.getId());
				}
				val.setAccountIds(accountIds);
			}
		}
		PageHelper.startPage(page, pageSize, true);
		Page<CustomerList> list = (Page<CustomerList>) customerDAO.selectList(val);
		for (CustomerList tmp : list) {
			if (tmp.getFinalTime() == null)
				continue;
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			String myDate = dateFormater.format(tmp.getFinalTime());
			tmp.setTraceName(TraceType.CalcValue(myDate).getName());
		}
		return new ServiceResult<Page<CustomerList>>(list);
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

		if (customerDAO.existsByName(model.getName()))
			return new ServiceResultBool("客户名称已存在");

		if (customerDAO.existsByShortName(model.getShortName()))
			return new ServiceResultBool("客户简称已存在");

		// TODO Auto-generated method stub
		model.setCreateId(UserState.getLoginId());
		model.setCreateTime(new Date());
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
			
			if ((!model.getContactName().equals(null)&&!model.getContactName().equals(""))&&(!model.getContactPhone().equals(null)&&!model.getContactPhone().equals(""))) {
				//新增联系人
				ContactForm contactModel=new ContactForm();
				contactModel.setCreateTime(model.getCreateTime());
				contactModel.setCustomerId(newId);
				contactModel.setName(model.getContactName());
				contactModel.setPhone(model.getContactPhone());
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
		ServiceResult<Customer> oldModel = getCustomer(model.getId());
		Customer oldCustomer = oldModel.getData();
		if (!model.getName().equals(oldCustomer.getName())) {
			if (customerDAO.existsByName(model.getName()))
				return new ServiceResultBool("客户名称已存在");
		}
		if (!model.getShortName().equals(oldCustomer.getShortName())) {
			if (customerDAO.existsByShortName(model.getShortName()))
				return new ServiceResultBool("客户简称已存在");
		}
		if(!StringUtils.isEmpty(model.getUrl()) && !model.getUrl().startsWith("http")){
			model.setUrl("http://" + model.getUrl());
		}
		model.setNameSimplePy(Pinyin4jUtil.getPinYinHeadChar(model.getName()));
		model.setNamePy(Pinyin4jUtil.getPinYin(model.getName()));
		customerDAO.updateCustomer(model);
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
				return new ServiceResultBool("保存成功！联系人"+res.getMsg());
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
		hc.setTotalSevenTraceCount(getTotalTraceBy(7,10));
		hc.setTotalTenTraceCount(getTotalTraceBy(10,15));
		hc.setTotalFifteenTraceCount(getTotalTraceBy(15,30));
		hc.setTotalThirtyTraceCount(getTotalTraceBy(30,60));
		hc.setTotalSixtyTraceCount(getTotalTraceBy(60,365));
		hc.setTotalNotTraceCount(getTotalTraceBy(null,null));
		
		if(hc.getTotalCustomerCount()==null)hc.setTotalCustomerCount(0);
		if(hc.getTotalContactCount()==null)hc.setTotalContactCount(0);
		if(hc.getTotalSevenTraceCount()==null)hc.setTotalSevenTraceCount(0);
		if(hc.getTotalTenTraceCount()==null)hc.setTotalTenTraceCount(0);
		if(hc.getTotalFifteenTraceCount()==null)hc.setTotalFifteenTraceCount(0);
		if(hc.getTotalThirtyTraceCount()==null)hc.setTotalThirtyTraceCount(0);
		if(hc.getTotalSixtyTraceCount()==null)hc.setTotalSixtyTraceCount(0);
		if(hc.getTotalNotTraceCount()==null)hc.setTotalNotTraceCount(0);
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

	@Override
	public Integer updateCustomerState(Integer id) {
		
		
		return customerDAO.updateCustomerState(id);
	}

	@Override
	public Integer exitCustomer(Integer id) {
		// TODO 自动生成的方法存根
	 return customerDAO.exitCustomer(id);
	}
	/**
	 * 公海修改联系人信息
	 * @param customerId
	 * @return
	 */
	public void updateContact(Integer customerId){
		//修改联系人的第一负责人 
		contactDAO.UpdateFirstMan(customerId, UserState.getLoginId());
		//根据客户id 批量删除共享者的信息
		contactDAO.DeleteBatchShare(customerId);
				
	}

}
