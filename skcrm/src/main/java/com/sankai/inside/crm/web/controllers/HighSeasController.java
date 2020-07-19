package com.sankai.inside.crm.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.AccountOfDept;
import com.sankai.inside.crm.entity.Contact;
import com.sankai.inside.crm.entity.ContactSearch;
import com.sankai.inside.crm.entity.ContactSearchByCusId;
import com.sankai.inside.crm.entity.CustomerList;
import com.sankai.inside.crm.entity.CustomerListSearch;
import com.sankai.inside.crm.entity.CustomerLog;
import com.sankai.inside.crm.entity.CustomerLogList;
import com.sankai.inside.crm.entity.CustomerRecord;
import com.sankai.inside.crm.entity.CustomerRecordDTO;
import com.sankai.inside.crm.entity.CustomerShare;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.CustomerShareTransDTO;
import com.sankai.inside.crm.entity.CustomerTransDTO;
import com.sankai.inside.crm.entity.CustomerTransfer;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.entity.SysDict;
import com.sankai.inside.crm.entity.SysDictListByRecordTypeDTO;
import com.sankai.inside.crm.service.AccountGroupService;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.ContactService;
import com.sankai.inside.crm.service.CustomerLogService;
import com.sankai.inside.crm.service.CustomerRemindService;
import com.sankai.inside.crm.service.IAddressService;
import com.sankai.inside.crm.service.ICustomerRecordService;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.service.ICustomerShareService;
import com.sankai.inside.crm.service.IHighSeasService;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.FormPage;

@Controller
@RequestMapping("highseas")
public class HighSeasController {
	// 日志管理
	public final static Logger log = LoggerFactory.getLogger(HighSeasController.class);
	private final String key = "CustomerListSearch";
	@Resource
	private IHighSeasService highSeasServiceImpl;
	@Resource
	private ICustomerService customerServiceImpl;
	@Resource
	private ISysDictService sysDictServiceImpl;
	@Resource
	private ICustomerRecordService customerRecordServiceImpl;
	@Resource
	private ISysDictService sysDictService;// 数据字典
	// @Resource
	// private DepartmentService departmentService;//部门服务
	@Resource
	private AccountService accountService;// 用户服务

	@Resource
	private IAddressService addressServiceImpl;

	@Resource
	private CustomerLogService customerLogService;// 客户日志管理

	@Resource
	private ICustomerShareService customerShareService;// 客户共享服务

	@Resource
	private CustomerRemindService customerRemindService;// 客户提醒

	@Autowired
	private ContactService contactService;// 联系人
	
	@Autowired
	private AccountGroupService groupService;//群组管理

	@RequiresAuthentication
	@RequiresPermissions(value = "highseas_list")
	@RequestMapping(path = "index", method = RequestMethod.GET)
	public String index(Model model, @RequestParam(defaultValue = "false", name = "cache") boolean isCache,
			HttpSession session,HttpServletRequest request) throws Exception {

		Integer loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人
		

		List<SysDict> xsztList = sysDictService.findAllByPid(36);// 客户状态
		List<SysDict> khlxList = sysDictService.findAllByPid(37);// 客户类型
		List<SysDict> khcglList = sysDictService.findAllByPid(83);// 客户成功率

		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id获取部门的所有成员
		String accIds = "";
		List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
		if (accList != null) {
			AccountOfDept first = accList.get(0);
			if (first.isMySelf() && first.getIsDeptManager() == 0) {// 部门领导
				accIds = String.valueOf(first.getId());
				accListNew.add(first);
			} else {
				for (AccountOfDept item : accList) {
					accIds += item.getId() + ",";
				}
				if (accIds != "")
					accIds = accIds.substring(0, accIds.length() - 1);
				accListNew = accList;
			}
		}
		String myself =  request.getParameter("myself");
		CustomerListSearch search = null;
		if (isCache && session != null) {
			search = (CustomerListSearch) session.getAttribute(key);
		}
		if (search == null) {
			String tranceType = request.getParameter("tracetype");//首页点击 跟踪状态
			
			Integer account = -1;
			if(StringUtils.isEmpty(tranceType))tranceType  = "-1";
			else account = loginId;
			if(myself!=null&&myself.equals("1"))account = loginId;//首页点击 客户总数
			search = new CustomerListSearch();
			search.setStatus("-1");
			search.setCustomerType("-1");
			search.setsalesSuccessRate("-1");
			search.setOrderField("order");
			search.setOrderType("desc");
			search.setTraceType(tranceType);
			search.setAccountId(account);
		}

		if (search.getOrderField() != null && search.getOrderField().contains("traceName"))
			search.setOrderField("finalTime");
		if (search.getOrderField() != null && search.getOrderField().contains("shortNameOrder"))
			search.setOrderField("shortName");

		search.setLoginId(UserState.getLoginId());
		String excludeAccountIds = groupService.getAccountIdStr(loginId);//这些用户的客户，不允许看到
		search.setExcludeAccountIds(excludeAccountIds);
		
		if(!StringUtils.isEmpty(request.getParameter("allowAccountName"))){
			search.setAllowAccountName(request.getParameter("allowAccountName"));
		}
		
		// 销售负责人
		List<Integer> accountIdList = new ArrayList<Integer>();
		// 客户列表
		ServiceResult<Page<CustomerList>> result = highSeasServiceImpl.getList(search, search.getPage(),
				search.getPageSize());

		Account account = accountService.getAccountInfo(loginId);// 当前用户信息

		model.addAttribute("loginName", (account == null ? "" : account.getName()));
		model.addAttribute("search", search);
		model.addAttribute("loginId", loginId);
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("xszt", xsztList);
		model.addAttribute("myself", myself);//首页点击 客户总数
		model.addAttribute("khlx", khlxList);
		model.addAttribute("khcgl", khcglList);
		
		model.addAttribute("accList", accListNew);
		model.addAttribute("isDeptLeader", isDeptLeader);
		model.addAttribute("isShowTop", false);// 是否显示置顶列

		return "highseas/index";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "highseas_list")
	@RequestMapping(path = "index", method = RequestMethod.POST)
	public String index(CustomerListSearch search, Model model, HttpSession session) throws Exception {

		session.setAttribute(key, search);
		int loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人

		List<SysDict> xsztList = sysDictService.findAllByPid(36);// 客户状态
		List<SysDict> khlxList = sysDictService.findAllByPid(37);// 客户类型
		List<SysDict> ptbbList = sysDictService.findAllByPid(83);// 平台版本
		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id获取部门的所有成员

		List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
		String accIds = "";
		if (accList != null) {
			AccountOfDept first = accList.get(0);
			if (first.isMySelf() && first.getIsDeptManager() == 0) {
				accIds = String.valueOf(first.getId());
				accListNew.add(first);
			} else {
				for (AccountOfDept item : accList) {
					accIds += item.getId() + ",";
				}
				if (accIds != "")
					accIds = accIds.substring(0, accIds.length() - 1);
				accListNew = accList;
			}
		}

		if (search.getOrderField().contains("traceName"))
			search.setOrderField("finalTime");
		if (search.getOrderField() != null && search.getOrderField().contains("shortNameOrder"))
			search.setOrderField("shortName");

		if (search.getOrderType() == null)
			search.setOrderType("");
		search.setLoginId(UserState.getLoginId());
		String excludeAccountIds = groupService.getAccountIdStr(loginId);//这些用户的客户，不允许看到
		search.setExcludeAccountIds(excludeAccountIds);

		// 销售负责人
		List<Integer> accountIdList = new ArrayList<Integer>();
		// 客户列表
		ServiceResult<Page<CustomerList>> result = highSeasServiceImpl.getList(search, search.getPage(),
				search.getPageSize());

		Account account = accountService.getAccountInfo(loginId);// 当前用户信息

		model.addAttribute("loginName", (account == null ? "" : account.getName()));
		model.addAttribute("search", search);
		model.addAttribute("loginId", loginId);
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("xszt", xsztList);
		model.addAttribute("khlx", khlxList);
		model.addAttribute("ptbb", ptbbList);
		
		model.addAttribute("accList", accList);
		model.addAttribute("isShowTop", (search.getAccountId() != -1));// 是否显示置顶列
		return "highseas/_list";

	}


	







/*
	 置顶 
	@RequestMapping(path = "up.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool up(@RequestParam("shareId") int shareId) {

		highSeasServiceImpl.upOrder(shareId);

		return new ServiceResultBool();
	}*/



	@RequiresAuthentication
	@RequiresPermissions(value = "highseas_transfer")
	@ResponseBody
	@RequestMapping(value = "transfer", method = RequestMethod.POST)
	public ServiceResultBool transfer(String cusId) {
		ServiceResultBool result = new ServiceResultBool();
		//List<CustomerShareDTO> list1 = new ArrayList<CustomerShareDTO>();

			Integer customerId = Integer.valueOf(cusId);
			
			Integer exitResult= highSeasServiceImpl.exitCustomer(customerId);
			if (exitResult<=0) {
				return new ServiceResultBool("当前客户已被抢占");
			}
			
			CustomerTransfer transgerModel = new CustomerTransfer();
			transgerModel.setCustomerId(customerId);
			Integer allowAccId = UserState.getLoginId();
			transgerModel.setAllowAccountId(allowAccId);
			// 新增客户转移记录
			ServiceResultBool tranResult = highSeasServiceImpl.addCustomerTransfer(transgerModel);
			// 获取原始分享信息
			CustomerShare shareModel = highSeasServiceImpl.getShareBycidAndAllowId(customerId, UserState.getLoginId());//UserState.getLoginId()sql语句中的条件已经去掉了
			// 获取最大MaxOrder
			Integer maxOrder = highSeasServiceImpl.getMaxOrder(allowAccId);
			maxOrder = (maxOrder == null ? 1 : maxOrder + 1);
			if (shareModel != null) {

				CustomerShare newModel = new CustomerShare();
				newModel.setId(shareModel.getId());
				newModel.setAllowAccountId(allowAccId);
				newModel.setOrder(maxOrder);
				newModel.setCustomerId(customerId);
				newModel.setIsFrom(3);
				// 客户转移 将客户信息的第一负责人改为抢占人
				highSeasServiceImpl.saveShare(newModel);
				// 抢占客户的所有分享者 全部删除 
				highSeasServiceImpl.saveOrderState(customerId, allowAccId);
				//修改客户 状态
				highSeasServiceImpl.updateCustomerState(customerId);
				//修改联系人信息-
				highSeasServiceImpl.updateContact(customerId);
				//联系人状态从公海修改为正常
				//contactService.injectHighSeasByCusId(customerId,0);
			}
			String allowAccountId=allowAccId.toString();
			opeCustomerLog(customerId, allowAccountId, 3);

		try {
			//this.customerShareService.insertCustomerShare(list1);
			result.setSuccess(true);
			result.setMsg("抢占成功");

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("系统异常，请联系管理员");
			e.printStackTrace();
			return result;
		}

		return result;
	}


	@RequestMapping(value = "higheasshow", method = RequestMethod.GET)
	public ModelAndView higheasshow(CustomerRecord vo, Model model, FormPage page) throws Exception {
		ModelAndView mav = new ModelAndView();

		Integer customerId = vo.getCustomerId();
		Integer accountId = vo.getAccountId();
		Integer selfAccountId = UserState.getLoginId();// 当前登陆ID

		boolean isDeptLeader = accountService.isLeaderById(selfAccountId);// 是否是部门领导人
		Integer shareResult = customerServiceImpl.getIsShare(customerId, selfAccountId);
		boolean isShare = (shareResult == null ? 0 : shareResult) == 1;
		if (!isDeptLeader) {
			/*
			 * // 根据客户Id和被分享的人查看，是否被分享 CustomerRecordIsShare cusRecShare = new
			 * CustomerRecordIsShare(); cusRecShare.setCustomerId(customerId);
			 * cusRecShare.setAllowAccountId(selfAccountId);
			 * CustomerRecordShareDTO getIsShare =
			 * customerRecordServiceImpl.getIsShare(cusRecShare);
			 */
			if (isDeptLeader || accountId == null) {
				accountId = null;
			} else {
				accountId = selfAccountId;
			}
		}
		/* try { */
		// 根据ID查询客户信息
		CustomerTransDTO customerInfo = this.customerServiceImpl.findCustomerInfoById(customerId);
		log.error("customerInfo=====" + customerInfo);
		// 根据客户ID查询客户的共享信息
		List<CustomerShareTransDTO> customerShares = this.customerServiceImpl.findAllows(customerId);
		log.error("customerShares=====" + customerShares);
		// 查找销售第一负责人
		CustomerShareTransDTO firstCustomerShare = this.customerServiceImpl.findFirstAllow(customerId);
		log.error("firstCustomerShare=====" + firstCustomerShare);

		// cgq-查询共享的客户联系人
		/*
		 * List<Contact> contacts = null; if
		 * (this.customerServiceImpl.checkIsOwn(customerId)) {//
		 * 如果登录人是第一负责人，则列出所有的联系人 contacts =
		 * this.customerServiceImpl.findContactInfoByCustoemrId(customerId); }
		 * else {// 否则列出共享的联系人 contacts =
		 * this.customerServiceImpl.findContactShared(customerId); }
		 */

		// yxb 修改的问题 ---查询共享的客户联系人
		// 不是领导人查询自己创建的联系人，是否执行
		boolean isSelect = true;
		ContactSearch search = new ContactSearch();
		search.setAccountId(UserState.getLoginId());// 当前登录人
		search.setContactRole("0");
		search.setOrderField(null);
		search.setOrderType("");
		search.setIsqq(false);
		search.setIsemail(false);
		search.setIsphone(false);
		search.setIswechat(false);
		search.setCustomerType("-1");
		search.setContent("");
		int loginId = UserState.getLoginId();
		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id
		List<AccountOfDept> accListNew = new ArrayList<AccountOfDept>();
		Integer deptLeader = 0;
		// 加载列表显示
		List<String> accountIdList = new ArrayList<String>();
		if (accList != null) {
			AccountOfDept first = accList.get(0);
			deptLeader = first.getIsDeptManager();
			// 判断是否是领导人
			if (first.isMySelf() &&  deptLeader== 0) {
				// 判断是否是第一负责人
				if (this.customerServiceImpl.checkIsOwn(customerId)) {
					for (AccountOfDept accountOfDept : accList) {
						accountIdList.add(accountOfDept.getId() + "");
					}
				} else {
					isSelect = false;
					accListNew.add(first);
					accountIdList.add(first.getId() + "");
				}
			} else {
				for (AccountOfDept accountOfDept : accList) {
					accountIdList.add(accountOfDept.getId() + "");
				}
				accListNew = accList;
			}
		}
		search.setPrincipal(accountIdList);
		List<Contact> contacts = new ArrayList<Contact>();
		if (isSelect) {
			search.setCustomerId(customerId);
			contacts = contactService.selectContactByLoginId(search);
		} else {
			ContactSearchByCusId cusList = new ContactSearchByCusId();
			cusList.setCusterId(customerId);
			cusList.setAccountId(loginId);
			contacts = contactService.getConByCusId(cusList);
		}

		log.error("contacts=====" + contacts);
		// 查询客户日志
		List<CustomerLogList> customerLog = customerLogService.getListState(customerId);
		log.error("customerLog=====" + customerLog);
		// 列出所有的客户状态
		List<SysDict> allStatus = this.sysDictServiceImpl.findAllByPid(36);

		// 列出当前登录人的记录类别
		List<SysDict> allRecordTypes = this.sysDictServiceImpl.findAllByPid(5);
		ServiceResult<Account> accountResult = accountService.getEasyAccount(UserState.getLoginId());
		String recordType = accountResult.getData().getRecordType();
		List<SysDictListByRecordTypeDTO> dictmodelList = recordType == null ? null
				: sysDictServiceImpl.findByRecordType(recordType);

		// 分页列出销售跟踪记录
		ServiceResult<Page<CustomerRecordDTO>> result = this.customerRecordServiceImpl.findAllByCustomerIdGet(deptLeader,customerId,
				(isShare ? accountId : (selfAccountId == 0 ? null : selfAccountId)), null, page.getPage(), 6);
		// 所有的发布者
		List<CustomerRecordDTO> allPublishers = this.customerRecordServiceImpl.findAllpublishers(customerId,null);
		log.error("allPublishers=====" + allPublishers);
		mav.addObject("customerInfo", customerInfo);
		mav.addObject("customerShares", customerShares);
		mav.addObject("firstCustomerShare", firstCustomerShare);
		mav.addObject("contacts", contacts);
		mav.addObject("customerLog", customerLog);// 客户日志
		mav.addObject("allStatus", allStatus);
		mav.addObject("selfAccountId", selfAccountId);
		mav.addObject("allRecordTypes", allRecordTypes);
		
		mav.addObject("getRecordType", dictmodelList);

		mav.addObject("customerId", customerId);
		mav.addObject("allPublishers", allPublishers);
		mav.addObject("isDeptLeader", isDeptLeader);

		mav.addObject("isRecordShare", (isShare || isDeptLeader));// 跟踪记录是否共享

		mav.addObject("allRecords", result.getData());
		mav.addObject("pager", new PageInfo<>(result.getData()));
		/*
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * log.error(e.toString()); e.printStackTrace(); }
		 */
		mav.setViewName("highseas/higheasshow");
		return mav;
	}

	@RequestMapping(value = "higheasshow", method = RequestMethod.POST)
	public ModelAndView reLoadShow(CustomerRecord vo, Model model, FormPage page) {
		ModelAndView mav = new ModelAndView();
		Integer selfAccountId = UserState.getLoginId();// 当前登陆ID

		Integer customerId = vo.getCustomerId();
		Integer accountId = vo.getAccountId();
		/*
		 * // 根据客户Id和被分享的人查看，是否被分享 CustomerRecordIsShare cusRecShare = new
		 * CustomerRecordIsShare(); cusRecShare.setCustomerId(customerId);
		 * cusRecShare.setAllowAccountId(selfAccountId); CustomerRecordShareDTO
		 * getIsShare = customerRecordServiceImpl.getIsShare(cusRecShare);
		 */

		boolean isDeptLeader = accountService.isLeaderById(selfAccountId);// 是否是部门领导人
		Integer shareResult = customerServiceImpl.getIsShare(customerId, selfAccountId);
		boolean isShare = (shareResult == null ? 0 : shareResult) == 1;
		if (!isDeptLeader) {
			if (isDeptLeader || accountId == null) {
				accountId = null;
			} else if(!isDeptLeader && accountId!=null) {
				
			}else {
				accountId = selfAccountId;
			}

		}

		// 查找销售第一负责人
		CustomerShareTransDTO firstCustomerShare = null;
		try {
			firstCustomerShare = this.customerServiceImpl.findFirstAllow(customerId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// 分页列出销售跟踪记录
			
			ServiceResult<Page<CustomerRecordDTO>> result = this.customerRecordServiceImpl.findAllByCustomerId(isDeptLeader?1:0,
					customerId, ((isShare || selfAccountId <= 0 || isDeptLeader) ? accountId : selfAccountId), vo.getType(),
					page.getPage(), 6);

			mav.addObject("allRecords", result.getData());
			mav.addObject("pager", new PageInfo<>(result.getData()));
			mav.addObject("selfAccountId", selfAccountId);
			mav.addObject("firstCustomerShare", firstCustomerShare);
			mav.addObject("isDeptLeader", isDeptLeader);
			mav.addObject("isRecordShare", (isShare || isDeptLeader));// 跟踪记录是否共享

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		mav.setViewName("customer/show_list");
		return mav;
	}
	
	

	/**
	 * 新增客户日志记录
	 * 
	 * @param customerId
	 * @param accountIds
	 * @param type
	 */
	private void opeCustomerLog(Integer customerId, String accountIds, Integer type) {
		// 新增客户日志
		CustomerLog log = new CustomerLog();
		log.setCustomerId(customerId);
		log.setAccountIds(accountIds);
		log.setCreateId(UserState.getLoginId());
		log.setCreateTime(new Date());
		log.setType(type);

		customerLogService.add(log);
	}
	
	
	




}
