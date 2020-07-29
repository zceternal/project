package com.sankai.inside.crm.web.controllers;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.core.mail.SpringMailSender;
import com.sankai.inside.crm.core.utils.DateUtil;
import com.sankai.inside.crm.core.utils.DelHTMLUtil;
import com.sankai.inside.crm.entity.*;
import com.sankai.inside.crm.service.*;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.FormPage;
import com.sankai.inside.crm.web.model.ValidAdd;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("customer")
public class CustomerController {
	// 日志管理
	public final static Logger log = LoggerFactory.getLogger(CustomerController.class);
	private final String key = "CustomerListSearch";
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
	private ContactShareService contactShareService;// 联系人分享表

	@Resource
	private SpringMailSender send;// 发送邮件
	@Resource
	private CustomerStatusTimeService customerStatusTimeService;//客户状态停留时间

	@RequiresAuthentication
	@RequiresPermissions(value = "customer_list")
	@RequestMapping(path = "index", method = RequestMethod.GET)
	public String index(Model model, @RequestParam(defaultValue = "false", name = "cache") boolean isCache,
						HttpSession session, HttpServletRequest request) throws Exception {

		Integer loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人

		String status = request.getParameter("status");//客户状态
		List<SysDict> xsztList = sysDictService.findAllByPid(36);// 客户状态
		List<SysDict> khlxList = sysDictService.findAllByPid(37);// 客户类型
		List<SysDict> ptbbList = sysDictService.findAllByPid(83);// 平台版本

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
		String myself = request.getParameter("myself");
		CustomerListSearch search = null;
		if (isCache && session != null) {
			search = (CustomerListSearch) session.getAttribute(key);
		}
		if (search == null) {
			String tranceType = request.getParameter("tracetype");// 首页点击 跟踪状态

			Integer account = -1;
			if (StringUtils.isEmpty(tranceType))
				tranceType = "-1";
			else
				account = loginId;
			if (myself != null && myself.equals("1"))
				account = loginId;// 首页点击 客户总数
			search = new CustomerListSearch();
			if(StringUtils.isEmpty(status))search.setStatus("-1");
			else search.setStatus(status);
			search.setCustomerType("-1");
			search.setsalesSuccessRate("-1");
			search.setOrderField("order");
			search.setOrderType("desc");
			search.setTraceType(tranceType);
			search.setAccountId(account);
			search.setIsFrom(-1);
		}

		if (search.getOrderField() != null && search.getOrderField().contains("traceName"))
			search.setOrderField("finalTime");
		if (search.getOrderField() != null && search.getOrderField().contains("shortNameOrder"))
			search.setOrderField("shortName");

		search.setLoginId(UserState.getLoginId());

		// 销售负责人
		List<Integer> accountIdList = new ArrayList<Integer>();
		if (search.getAccountId() != -1) {
			search.setPrincipal(accountIdList);
			search.setPrincipalStr(search.getAccountId() + "");
		} else {
			if (isDeptLeader) {
				search.setPrincipal(null);
				search.setPrincipalStr(accIds);
			} else {
				accountIdList.add(loginId);
				search.setPrincipal(accountIdList);
				search.setPrincipalStr(String.valueOf(loginId));
			}
		}
		//客户详细页面点击返回按钮后的信息 - start
		initSearch(request,search,isDeptLeader);

		// 客户列表
		ServiceResult<Page<CustomerList>> result = customerServiceImpl.getList(search, search.getPage(),
				search.getPageSize());

		Account account = accountService.getAccountInfo(loginId);// 当前用户信息

		model.addAttribute("loginName", (account == null ? "" : account.getName()));
		model.addAttribute("search", search);
		model.addAttribute("loginId", loginId);
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("xszt", xsztList);
		model.addAttribute("myself", myself);// 首页点击 客户总数
		model.addAttribute("khlx", khlxList);
		model.addAttribute("ptbb", ptbbList);

		model.addAttribute("accList", accListNew);
		model.addAttribute("isDeptLeader", isDeptLeader);
		model.addAttribute("isShowTop", false);// 是否显示置顶列

		return "customer/index";
	}

	public void initSearch(HttpServletRequest request,CustomerListSearch search,boolean isDeptLeader)
	{
		String indexAccountId = request.getParameter("indexAccountId");
		if(StringUtils.isEmpty(indexAccountId)) return;
		//客户详细页面点击返回按钮后的信息 - start
		String indexPage = request.getParameter("indexPage");
		String indexPageSize = request.getParameter("indexPageSize");
		//String indexTraceType = request.getParameter("indexTraceType");
		String indexStatus = request.getParameter("indexStatus");
		String indexCustomerType = request.getParameter("indexCustomerType");
		String indexSalesSuccessRate = request.getParameter("indexSalesSuccessRate");
		String indexOrderType = request.getParameter("indexOrderType");
		String indexOrderField = request.getParameter("indexOrderField");
		String indexContent = request.getParameter("indexContent");
		String indexIsFrom = request.getParameter("indexIsFrom");
		//客户详细页面点击返回按钮后的信息 - end
		if(!StringUtils.isEmpty(indexPage))search.setPage(Integer.valueOf(indexPage));
		if(!StringUtils.isEmpty(indexPageSize))search.setPageSize(Integer.valueOf(indexPageSize));
		//if(!StringUtils.isEmpty(indexTraceType))search.setTraceType(indexTraceType);
		if(!StringUtils.isEmpty(indexStatus))search.setStatus(indexStatus);
		if(!StringUtils.isEmpty(indexCustomerType))search.setCustomerType(indexCustomerType);
		if(!StringUtils.isEmpty(indexSalesSuccessRate))search.setsalesSuccessRate(indexSalesSuccessRate);
		int accId = Integer.valueOf(indexAccountId);
		if(accId>0&&isDeptLeader){
			search.setAccountId(accId);
			search.setPrincipalStr(indexAccountId);
		}
		if(!StringUtils.isEmpty(indexOrderType))search.setOrderType(indexOrderType);
		if(!StringUtils.isEmpty(indexOrderField))search.setOrderField(indexOrderField);
		if(!StringUtils.isEmpty(indexContent))search.setContent(indexContent);
		if(!StringUtils.isEmpty(indexIsFrom))search.setIsFrom(Integer.valueOf(indexIsFrom));
		//return search;
	}
	@RequiresAuthentication
	@RequiresPermissions(value = "customer_list")
	@RequestMapping(path = "index", method = RequestMethod.POST)
	public String index(CustomerListSearch search, Model model, HttpSession session) throws Exception {

		session.setAttribute(key, search);
		int loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人

		List<SysDict> xsztList = sysDictService.findAllByPid(36);// 客户状态
		List<SysDict> khlxList = sysDictService.findAllByPid(37);// 客户类型
		List<SysDict> khcglList = sysDictService.findAllByPid(83);// 客户成功率
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

		// 销售负责人
		List<Integer> accountIdList = new ArrayList<Integer>();
		if (search.getAccountId() != -1) {
			search.setPrincipal(accountIdList);
			search.setPrincipalStr(search.getAccountId() + "");
		} else {
			if (isDeptLeader) {
				search.setPrincipal(null);
				search.setPrincipalStr(accIds);
			} else {
				accountIdList.add(loginId);
				search.setPrincipal(accountIdList);
				search.setPrincipalStr(String.valueOf(loginId));
			}
		}
		// 客户列表
		ServiceResult<Page<CustomerList>> result = customerServiceImpl.getList(search, search.getPage(),
				search.getPageSize());

		Account account = accountService.getAccountInfo(loginId);// 当前用户信息

		model.addAttribute("loginName", (account == null ? "" : account.getName()));
		model.addAttribute("search", search);
		model.addAttribute("loginId", loginId);
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("xszt", xsztList);
		model.addAttribute("khlx", khlxList);
		model.addAttribute("khcgl", khcglList);

		model.addAttribute("accList", accList);
		model.addAttribute("isShowTop", (search.getAccountId() != -1));// 是否显示置顶列
		return "customer/_list";

	}

	/*
	 * 客户的自动填充
	 */
	@RequestMapping(path = "loadCus.ajax", method = RequestMethod.POST)
	@ResponseBody
	public String[] loadCus(String value, String type) {
		try {
			log.info("=======>客户的自动填充:value={}",value);
			List<CustomerAutocomplate> list = customerServiceImpl.getListAutocomplate(value, type);

			String[] arr = new String[list.size()];

			if (type.equals("1")) {

				for (int i = 0; i < list.size(); i++) {
					arr[i] = list.get(i).getName() + "   |   " + list.get(i).getCreateName();
				}
			}
			if (type.equals("2")) {
				for (int i = 0; i < list.size(); i++) {
					arr[i] = list.get(i).getShortName() + "   |   " + list.get(i).getCreateName();
				}
			}

			return arr;
		} catch (Exception e) {
			log.debug("=======>客户的自动填充-异常：{}",e);
		}
		return null;
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,CustomerRecord vo, Model model, FormPage page) throws Exception {
		ModelAndView mav = new ModelAndView();

		Integer customerId = vo.getCustomerId();
		Integer accountId = vo.getAccountId();
		Integer selfAccountId = UserState.getLoginId();// 当前登陆ID

		boolean isDeptLeader = accountService.isLeaderById(selfAccountId);// 是否是部门领导人
		Integer shareResult = customerServiceImpl.getIsShare(customerId, selfAccountId);
		boolean isShare = (shareResult == null ? 0 : shareResult) == 1;
		if (!isDeptLeader) {
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
			if (first.isMySelf() && deptLeader == 0) {
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
		if(!CollectionUtils.isEmpty(allStatus)){
			List<CustomerStatueTimeDto> list = customerStatusTimeService.getList(customerId);
			if(!CollectionUtils.isEmpty(list)){
				for (SysDict item : allStatus) {
					CustomerStatueTimeDto first = list.get(0);//最新的一条数据要与当前日期比较，计算花费天数
					if(first.getStatus()==item.getId()){
						long diff = DateUtil.compareDate4Day(first.getOperateTime(), new Date());
						item.setSpendDay(residue(diff,item.getId(),item.getValue()));
					}
					for (int i = 1; i < list.size(); i++) {
						CustomerStatueTimeDto cst = list.get(i);
						if(cst.getStatus()==item.getId()){
							item.setSpendDay(cst.getSpendDay());
						}
					}
				}
			}
		}

		// 列出当前登录人的记录类别
		List<SysDict> allRecordTypes = this.sysDictServiceImpl.findAllByPid(5);
		ServiceResult<Account> accountResult = accountService.getEasyAccount(UserState.getLoginId());
		String recordType = accountResult.getData().getRecordType();
		List<SysDictListByRecordTypeDTO> dictmodelList = recordType == null ? null
				: sysDictServiceImpl.findByRecordType(recordType);

		// 分页列出销售跟踪记录
		/*ServiceResult<Page<CustomerRecordDTO>> result = this.customerRecordServiceImpl.findAllByCustomerIdGet(
				deptLeader, customerId, (isShare ? accountId : (selfAccountId == 0 ? null : selfAccountId)), null,
				page.getPage(), 6);*/

		ServiceResult<Page<CustomerRecordDTO>> result = this.customerRecordServiceImpl.findAllByCustomerId(
				isDeptLeader ? 1 : 0, customerId,
				((isShare || selfAccountId <= 0 || isDeptLeader) ? accountId : selfAccountId), vo.getType(),
				page.getPage(), 6);

		// 所有的发布者
		List<CustomerRecordDTO> allPublishers = this.customerRecordServiceImpl.findAllpublishers(customerId,0);
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

		mav.addObject("isFirst", vo.getIsFirst());// 是否是第一负责人
		/*
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * log.error(e.toString()); e.printStackTrace(); }
		 */
		// index首页信息 - start
		mav.addObject("indexPage", request.getParameter("page"));
		mav.addObject("indexPageSize", request.getParameter("pageSize"));
		//mav.addObject("indexTraceType", request.getParameter("traceType"));
		mav.addObject("indexStatus", request.getParameter("status"));
		mav.addObject("indexCustomerType", request.getParameter("customerType"));
		mav.addObject("indexSalesSuccessRate", request.getParameter("salesSuccessRate"));
		mav.addObject("indexAccountId", request.getParameter("accountId"));
		mav.addObject("indexOrderType", request.getParameter("orderType"));
		mav.addObject("indexOrderField", request.getParameter("orderField"));
		mav.addObject("indexContent", request.getParameter("content"));
		mav.addObject("indexIsFrom", request.getParameter("isFrom"));
		// index首页信息 - end
		mav.setViewName("customer/show");
		return mav;
	}

	private int residue(long diff,int status,String value)
	{
		Integer val = 0;
		if(StringUtils.isEmpty(value)){
			switch (status) {
				case 38:
					val = 365;
					break;
				case 39:
					val = 90;
					break;
				case 40:
					val = 30;
					break;
				default:
					val=30;
					break;
			}
		}
		else{
			val = Integer.valueOf(value);
		}
		return val-Integer.valueOf(String.valueOf(diff));
	}
	@RequestMapping(value = "show", method = RequestMethod.POST)
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
			} else if (!isDeptLeader && accountId != null) {

			} else {
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

			ServiceResult<Page<CustomerRecordDTO>> result = this.customerRecordServiceImpl.findAllByCustomerId(
					isDeptLeader ? 1 : 0, customerId,
					((isShare || selfAccountId <= 0 || isDeptLeader) ? accountId : selfAccountId), vo.getType(),
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

	// 新增
	@RequiresAuthentication
	@RequiresPermissions(value = "customer_add")
	@RequestMapping(path = "add", method = RequestMethod.GET)
	public String add(Model model) {

		try {

			List<SysDict> cpfwList = sysDictService.findAllByPid(9);// 产品及服务
			List<SysDict> cqxzList = sysDictService.findAllByPid(10);// 客户出钱性质
			List<SysDict> xsztList = sysDictService.findAllByPid(36);// 客户状态
			List<SysDict> khlxList = sysDictService.findAllByPid(37);// 客户类型
			List<SysDict> khkyList = sysDictService.findAllByPid(67);// 客户来源-直销
			List<SysDict> ptbbList = sysDictService.findAllByPid(83);// 平台版本
			List<Contact> lxrList = contactService.getList("112");;// 客户来源-渠道
			List<SysDict> xstjztList = sysDictService.findAllByPid2(11);// 销售推进状态

			List<Address> list = this.addressServiceImpl.listAllProvs();// 地区
			model.addAttribute("listProvs", list);
			model.addAttribute("xszt", xsztList);
			model.addAttribute("khlx", khlxList);
			model.addAttribute("khly", khkyList);
			model.addAttribute("ptbb", ptbbList);
			model.addAttribute("cpfw", cpfwList);
			model.addAttribute("lxr", lxrList);
			model.addAttribute("cqxz", cqxzList);
			model.addAttribute("xstjzt", xstjztList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "customer/add";
	}



	@RequiresAuthentication
	@RequiresPermissions(value = "customer_add")
	@RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool add(@Validated({ ValidAdd.class }) Customer from, BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}
		ServiceResultBool resutl = customerServiceImpl.addCustomer(from);
		return resutl;
	}

	/* 置顶 */
	@RequestMapping(path = "up.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool up(@RequestParam("shareId") int shareId) {

		customerServiceImpl.upOrder(shareId);

		return new ServiceResultBool();
	}

	// 修改
	@RequiresAuthentication
	@RequiresPermissions(value = "customer_edit")
	@RequestMapping(path = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam int id, Model model) throws Exception {
		log.error("customer=====edit");
		ServiceResult<Customer> result = customerServiceImpl.getModelById(id);
		if (!result.isSuccess()) {
			model.addAttribute("msg", result.getMsg());
			return "error";
		}

		Customer customer = result.getData();

		List<SysDict> cpfwList = sysDictService.findAllByPid(9);// 产品及服务
		List<SysDict> xsztList = sysDictService.findAllByPid(36);// 客户状态
		List<SysDict> khlxList = sysDictService.findAllByPid(37);// 客户类型
		List<SysDict> khkyList = sysDictService.findAllByPid(67);// 客户来源
		List<SysDict> khcglList = sysDictService.findAllByPid(83);// 客户成功率

		List<Address> list = this.addressServiceImpl.listAllProvs();// 地区 省
		model.addAttribute("listProvs", list);
		List<Address> listCity = this.addressServiceImpl.listCityDicByProv(customer.getProvince());// 地区
		// 市
		model.addAttribute("listCity", listCity);
		List<Address> listCountry = this.addressServiceImpl.listCityDicByProv(customer.getCity());// 地区
		// 县
		model.addAttribute("listCountry", listCountry);

		model.addAttribute("model", customer);
		model.addAttribute("xszt", xsztList);
		model.addAttribute("khlx", khlxList);
		model.addAttribute("khly", khkyList);
		model.addAttribute("khcgl", khcglList);
		model.addAttribute("cpfw", cpfwList);

		List<String> types = new ArrayList<String>();
		if (customer.getType() != null && customer.getType().contains(",")) {
			String[] ts = customer.getType().split(",");
			types = Arrays.asList(ts);
		} else {
			types.add(customer.getType());
		}
		model.addAttribute("types", types);

		List<String> sources = new ArrayList<String>();
		if (customer.getSource() != null && customer.getSource().contains(",")) {
			String[] sors = customer.getSource().split(",");
			sources = Arrays.asList(sors);
		} else {
			sources.add(customer.getSource());
		}
		model.addAttribute("sources", sources);

		return "customer/edit";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "customer_edit")
	@RequestMapping(path = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool edit(@Validated({ ValidAdd.class }) Customer from, BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}
		ServiceResultBool resutl = customerServiceImpl.saveCustomer(from);
		return resutl;

	}

	@RequiresAuthentication
	@RequiresPermissions(value = "customer_remove")
	@RequestMapping(path = "delete.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool delete(@RequestParam("id") int id) {
		return customerServiceImpl.remove(id);
	}

	// 转移
	@RequiresAuthentication
	@RequiresPermissions(value = "customer_transfer")
	@RequestMapping(path = "transfer", method = RequestMethod.GET)
	public ModelAndView transfer(HttpServletRequest request,CustomerShareCusDTO dto, FormPage page) {
		ModelAndView mav = new ModelAndView();
		String[] idsArray = dto.getCusids().split(",");
		String[] shareIdsArray = dto.getShareids().split(",");
		String customerNames = request.getParameter("cusnames");
		mav.addObject("cusnames", customerNames);
		int i = 0;
		// 获取要共享的客户
		List<CustomerShareCusDTO> cusList = new ArrayList<CustomerShareCusDTO>();
		if (idsArray.length > 0) {
			for (String cusidStr : idsArray) {
				if (cusidStr != null) {
					Integer cusid = Integer.valueOf(cusidStr);
					Integer shareid = Integer.valueOf(shareIdsArray[i]);
					i++;
					try {
						CustomerShareCusDTO cus = this.customerShareService.findCusById(cusid);
						cus.setCustomerShareId(shareid);
						cusList.add(cus);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		// 列出所有部门
		try {
			List<DeptTable> deptAcctsList = this.customerShareService.getDeptTable();
			mav.addObject("deptAcctsList", deptAcctsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("cusList", cusList);

		mav.setViewName("customer/customer_transfer");
		return mav;
		/* return "customer/transfer"; */
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "customer_transfer")
	@ResponseBody
	@RequestMapping(value = "transfer", method = RequestMethod.POST)
	public ServiceResultBool transfer(CustomerShareDTO dto, String[] customerShareId, HttpServletRequest request) {
		// 转移规则：把原来 数据直接修改为要转移的人（之前插入一条新数据，修改原先的状态为删除）
		ServiceResultBool result = new ServiceResultBool();
		List<CustomerShareDTO> list1 = new ArrayList<CustomerShareDTO>();
		String allowAccountId = request.getParameter("allowAccountId");
		String[] customerIdArray = request.getParameterValues("customerId");
		String customerNames = request.getParameter("cusnames");
		if (allowAccountId == null) {
			result.setMsg("您要转移给谁");
			result.setSuccess(false);
			return result;
		} else if (customerIdArray == null || customerIdArray.length == 0) {
			result.setMsg("请选择要转移的客户");
			result.setSuccess(false);
			return result;
		}
		Integer customerId = 0;
		// 是否转移成功
		Boolean isTransferSuccess = false;
		int i = 0;
		Integer allowAccId = Integer.parseInt(allowAccountId);
		try {
			for (String cusId : customerIdArray) {

				customerId = Integer.valueOf(cusId);
				CustomerTransfer transgerModel = new CustomerTransfer();
				transgerModel.setCustomerId(customerId);
				transgerModel.setAllowAccountId(allowAccId);
				// 新增客户转移记录
				customerServiceImpl.addCustomerTransfer(transgerModel);
				// 获取最大MaxOrder
				Integer maxOrder = customerServiceImpl.getMaxOrder(allowAccId);
				maxOrder = (maxOrder == null ? 1 : maxOrder + 1);

				// 当前登陆人是 管理员
				if (UserState.getLoginId() <= 0) {
					try {
						CustomerShareDTO share = new CustomerShareDTO();
						share.setCustomerId(customerId);
						share.setId(Integer.valueOf(customerShareId[i]));
						share.setAllowAccountId(allowAccId);
						i++;
						list1.add(share);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// 获取原始分享信息
					CustomerShare shareModel = customerServiceImpl.getShareBycidAndAllowId(customerId,
							UserState.getLoginId());
					if (shareModel != null) {
						CustomerShare newModel = new CustomerShare();
						newModel.setId(shareModel.getId());
						newModel.setAllowAccountId(allowAccId);
						newModel.setOrder(maxOrder);
						newModel.setCustomerId(customerId);
						newModel.setIsFrom(1);
						// 客户转移 修改客户分享信息
						ServiceResultBool bool = customerServiceImpl.saveShare(newModel);
						isTransferSuccess = bool.isSuccess();
						// 修改客户分享的 删除状态
						// customerServiceImpl.saveOrderState(customerId,
						// allowAccId);
					}
				}
				opeCustomerLog(customerId, allowAccountId, 2);
			}

			// 当前登陆人是 管理员
			if (list1.size() > 0) {
				for (CustomerShareDTO itemDto : list1) {
					// 获取最大MaxOrder
					Integer maxOrder = customerServiceImpl.getMaxOrder(itemDto.getAllowAccountId());
					maxOrder = (maxOrder == null ? 1 : maxOrder + 1);

					CustomerShare newModel = new CustomerShare();
					newModel.setId(itemDto.getId());
					newModel.setAllowAccountId(allowAccId);
					newModel.setAccountId(allowAccId);
					newModel.setOrder(maxOrder);
					newModel.setCustomerId(itemDto.getCustomerId());
					newModel.setIsFrom(1);
					// 客户转移 修改客户分享信息
					ServiceResultBool bool = customerServiceImpl.saveShare(newModel);
					isTransferSuccess = bool.isSuccess();
				}
				// this.customerShareService.insertCustomerShare(list1);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("系统异常，请联系管理员");
			e.printStackTrace();
			return result;
		}
		// 修改客户的创建人为 转移后的人
		Customer model = new Customer();
		model.setId(customerId);
		model.setCreateId(UserState.getLoginId());
		customerServiceImpl.saveCustomerSim(model);

		// 客户转移，将客户关联的联系人转移给允许人
		ContactShare csDto = new ContactShare();
		csDto.setCustomerId(customerId);
		if(!StringUtils.isEmpty(allowAccountId))csDto.setAllowAccountId(Integer.valueOf(allowAccountId));
		contactShareService.updateTran(csDto);

		// 转移成功邮件通知
		Account account = accountService.getAccountInfo(Integer.parseInt(allowAccountId));// 当前用户信息
		String email = account.getEmail();
		String title = "转移客户";
		if(StringUtils.isEmpty(customerNames))customerNames = "";
		else customerNames = customerNames.substring(0, customerNames.length()-1);
		String content = String.format("%s 把客户【%s】转移给您，请查看CRM系统",UserState.getLoginName(),customerNames);
		try {
			send.sendMail(UserState.getLoginName(), email, title, content);
		} catch (UnsupportedEncodingException | MessagingException e) {
			result.setSuccess(true);
			result.setMsg("转移成功-邮件发送失败，请检查员工邮箱是否正确！");
			e.printStackTrace();
			return result;
		}
		if (isTransferSuccess) {
			result.setSuccess(true);
			result.setMsg("转移成功-邮件已发送");
		}
		return result;
	}

	// 共享
	@RequiresAuthentication
	@RequiresPermissions(value = "customer_share")
	@RequestMapping(path = "share", method = RequestMethod.GET)
	public String share() {

		return "customer/share";
	}

	// 修改客户状态
	@ResponseBody
	@RequestMapping(path = "updateStatus", method = RequestMethod.POST)
	public UpdateCustomerStatusResponseDTO updateStatus(UpdateCustomerStatusDTO dto) {
		Integer customerId = dto.getCustomerId();
		Integer status = dto.getStatus();
		UpdateCustomerStatusResponseDTO result = new UpdateCustomerStatusResponseDTO();
		try {
			if (this.customerServiceImpl.updateStatus(customerId, status)) {
				result.setSuccess(true);
				result.setMsg("修改成功");
			} else {
				result.setSuccess(true);
				result.setMsg("修改失败");
			}
		} catch (Exception e) {
			result.setSuccess(true);
			result.setMsg("修改异常");
			return result;
		}
		return result;
	}

	// 判断客户名称是否存在
	@RequiresAuthentication
	@RequiresPermissions(value = { "customer_add", "customer_edit" }, logical = Logical.OR)
	@RequestMapping(path = "nameCall.ajax", method = RequestMethod.POST)
	public void existsNameCall(@RequestParam String fieldId, @RequestParam String fieldValue,
							   HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServiceResultBool result = customerServiceImpl.existsByName(request.getParameter("fieldValue"));
		response.getWriter().write("[\"" + fieldId + "\"," + result.isSuccess() + "]");

	}

	// 判断客户简称是否存在
	@RequiresAuthentication
	@RequiresPermissions(value = { "customer_add", "customer_edit" }, logical = Logical.OR)
	@RequestMapping(path = "shortNameCall.ajax", method = RequestMethod.POST)
	public void existsShortNameCall(@RequestParam String fieldId, @RequestParam String fieldValue,
									HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServiceResultBool result = customerServiceImpl.existsByShortName(request.getParameter("fieldValue"));
		response.getWriter().write("[\"" + fieldId + "\"," + result.isSuccess() + "]");

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

	// 新增客户提醒
	/*
	 * @RequiresAuthentication
	 *
	 * @RequiresPermissions(value = "customer_add")
	 */
	@RequestMapping(path = "addRemind", method = RequestMethod.GET)
	public String addRemind(@RequestParam int id, Model model) {
		model.addAttribute("customerId", id);
		return "customer/addRemind";
	}

	/*
	 * @RequiresAuthentication
	 *
	 * @RequiresPermissions(value = "customer_add")
	 */
	@RequestMapping(path = "addRemind", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool addRemind(@Validated({ ValidAdd.class }) CustomerRemindAdd from, BindingResult result)
			throws Exception {

		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}
		ServiceResultBool res = customerRemindService.add(from);
		return res;
	}

	/**
	 * 投入公海
	 */
	@RequestMapping(path = "injectHighSeas", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool injectHighSeas(String id) throws Exception {
		Integer cusId = Integer.valueOf(id);
		return customerServiceImpl.injectHighSeas(cusId);
	}

	/**
	 * 批量投入公海
	 *
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "injectHighSeasBatch", method = RequestMethod.POST)
	public ServiceResultBool injectHighSeasBatch(String ids) {
		String[] idArray = ids.split(",");
		List<Integer> idsInt = new ArrayList<Integer>();
		for (int x = 0; x < idArray.length; x++) {
			Integer id = Integer.valueOf(idArray[x]);
			idsInt.add(id);
		}
		return this.customerServiceImpl.injectHighSeasBatch(idsInt);
	}

	// 新增客户提醒
	/*
	 * @RequiresAuthentication
	 *
	 * @RequiresPermissions(value = "customer_add")
	 */
	@RequestMapping(path = "saveRemind", method = RequestMethod.GET)
	public String saveRemind(@RequestParam int id, Model model) {
		CustomerRemindDTO result = customerRemindService.getById(id);
		String remindTime = "";
		if (result != null && result.getRemindTime() != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			remindTime = simpleDateFormat.format(result.getRemindTime());
		}
		model.addAttribute("remindTime", remindTime);
		model.addAttribute("model", result);

		return "customer/saveRemind";
	}

	/*
	 * @RequiresAuthentication
	 *
	 * @RequiresPermissions(value = "customer_add")
	 */
	@RequestMapping(path = "saveRemind", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool saveRemind(@Validated({ ValidAdd.class }) CustomerRemindEdit from, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}
		ServiceResultBool res = customerRemindService.edit(from);
		return res;
	}

	@RequestMapping(path="getCustomer",method={RequestMethod.GET,RequestMethod.POST})
	public String getCustomerLog(CustomerRecord vo, Model model, FormPage page,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();

		Integer customerId = vo.getCustomerId();
		Integer accountId = vo.getAccountId();
		Integer selfAccountId = UserState.getLoginId();// 当前登陆ID

		boolean isDeptLeader = accountService.isLeaderById(selfAccountId);// 是否是部门领导人
		Integer shareResult = customerServiceImpl.getIsShare(customerId, selfAccountId);
		boolean isShare = (shareResult == null ? 0 : shareResult) == 1;
		if (!isDeptLeader) {
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
		// 查找销售第一负责人
		CustomerShareTransDTO firstCustomerShare = this.customerServiceImpl.findFirstAllow(customerId);
		log.error("firstCustomerShare=====" + firstCustomerShare);

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
			if (first.isMySelf() && deptLeader == 0) {
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

		// 列出所有的客户状态
		List<SysDict> allStatus = this.sysDictServiceImpl.findAllByPid(36);

		Date startTime=null;
		Date endTime=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		if(!StringUtils.isEmpty(vo.getBetweenTime())&&"1".equals(vo.getBetweenTime())){
			endTime=c.getTime();
			c.add(c.MONTH, -1);
			startTime=c.getTime();
		}else if(!StringUtils.isEmpty(vo.getBetweenTime())&&"2".equals(vo.getBetweenTime())){
			endTime=c.getTime();
			c.add(c.MONTH, -3);
			startTime=c.getTime();
		}
		if(!StringUtils.isEmpty(vo.getStartTime())){
			startTime=sdf.parse(vo.getStartTime());
		}
		if(!StringUtils.isEmpty(vo.getEndTime())){
			endTime=sdf.parse(vo.getEndTime());
			c.setTime(endTime);
			c.add(c.DATE, 1);
			endTime=c.getTime();
		}
		// 分页列出销售跟踪记录
		ServiceResult<Page<CustomerRecordDTO>> result = this.customerRecordServiceImpl.findAllByCustomerIdGet(
				isDeptLeader ? 1 : 0, customerId,
				((isShare || selfAccountId <= 0 || isDeptLeader) ? accountId : selfAccountId), vo.getType(),
				page.getPage(), 10,startTime,endTime);

		Page<CustomerRecordDTO> data=new Page<CustomerRecordDTO>();
		for(CustomerRecordDTO r:result.getData()){
			r.setRemark(DelHTMLUtil.delHTMLTag(r.getRemark()));
			data.add(r);
		}
		data.setPageNum(result.getData().getPageNum());
		data.setPageSize(result.getData().getPageSize());
		data.setTotal(result.getData().getTotal());
		result.setData(data);
		request.setAttribute("allRecords", result.getData());
		request.setAttribute("customerInfo",customerInfo);
		request.setAttribute("firstCustomerShare",firstCustomerShare);
		request.setAttribute("allStatus",allStatus);
		request.setAttribute("pager", new PageInfo<>(result.getData()));
		request.setAttribute("customerRecord",vo);
		if(!StringUtils.isEmpty(vo.getBackType())){
			return "customer/reply_list_show";
		}
		return "customer/reply_list";
	}

}
