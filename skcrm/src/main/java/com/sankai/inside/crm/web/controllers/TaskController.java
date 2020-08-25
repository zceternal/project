package com.sankai.inside.crm.web.controllers;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.core.mail.SpringMailSender;
import com.sankai.inside.crm.entity.*;
import com.sankai.inside.crm.service.*;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("task")
public class TaskController {

	public final static Logger log = LoggerFactory.getLogger(CustomerController.class);
	private final String key = "CustomerListSearch";
	@Resource
	private ISysDictService sysDictService;// 数据字典
	@Autowired
	private ITaskService taskService;
	@Resource
	private AccountService accountService;// 用户服务
	@Resource
	private ContactService contactService;// 联系人
	@Resource
	private ICustomerShareService customerShareService;// 客户分享
	@Resource
	private ICustomerService customerServiceImpl;

	// 分享任务
	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "taskShare", method = RequestMethod.GET)
	public String taskShare(String[] ids, Model model) {
		List<Task> list = new ArrayList<Task>();
		if (ids.length > 0) {
			for (String item : ids) {
				int getId = Integer.parseInt(item);
				Task task = taskService.getOne(getId);
				list.add(task);
			}
		} else {
			return "error";
		}
		model.addAttribute("model", list);// 要分享的任务
		model.addAttribute("isOwn", UserState.getLoginId());// 分享人中不能有自己
		// 列出所有部门的列表
		try {
			List<DeptTable> deptAcctsList = this.customerShareService.getDeptTable();
			model.addAttribute("deptAcctsList", deptAcctsList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "task/task_share";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "taskShare", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool taskShare(TaskShareForm dto, BindingResult bindingResult, Model model) {
		ServiceResultBool result = new ServiceResultBool();
		int logidId = UserState.getLoginId();
		List<TaskShare> taskShareList = new ArrayList<TaskShare>();
		List<String> allowAccountIdArray = dto.getAllowAccountId();

		//获取任务Id
		List<String> taskIdArray = dto.getTaskId();
		if (allowAccountIdArray == null || allowAccountIdArray.size() == 0) {
			result.setMsg("请选择要共享的用户");
			result.setSuccess(false);
			return result;
		}
		if (taskIdArray == null || taskIdArray.size() == 0) {
			result.setMsg("请选择被共享的任务");
			result.setSuccess(false);
			return result;
		}

		for (String taskId : taskIdArray) {
			for (String accountId : allowAccountIdArray) {
				Integer allowAccountId = Integer.valueOf(accountId);
				if (taskService.checkExists(Integer.valueOf(taskId), allowAccountId) > 0) {
					continue;
				}

				TaskShare taskShare = new TaskShare();
				taskShare.setAllowAccountId(allowAccountId);
				taskShare.setCreateName(UserState.getLoginName());
				taskShare.setCreateTime(new Date());
				taskShare.setAccountId(logidId);// 登录人id
				taskShare.setDelFlag(0);
				taskShare.setTaskId(Integer.valueOf(taskId));
				taskShareList.add(taskShare);//自己不能分享给自己
			}
		}

		taskService.insertContactShare(taskShareList);
		result.setSuccess(true);
		result.setMsg("共享成功");
		return result;
	}

	// 新增反馈
	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "back", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool back(TaskBackSave taskBack, HttpServletRequest request) {
		ServiceResultBool result = new ServiceResultBool();
		Date date = new Date();
		TaskFeedback taskFeedback = new TaskFeedback();
		taskFeedback.setContent(taskBack.getContent());
		taskFeedback.setCreateName(UserState.getLoginName());
		taskFeedback.setCreateTime(date);
		taskFeedback.setSummary(taskBack.getSummary());
		taskFeedback.setTaskId(taskBack.getTaskId());

		ServiceResultBool serviceResultBool = taskService.addTaskBack(taskFeedback);
		if (serviceResultBool.isSuccess()) {
			String taskbackId = serviceResultBool.getMsg();
			MultipartFile[] uploadFile = taskBack.getUploadFile();
			if (Objects.isNull(uploadFile)) {
				return result;
			}

			// 获取保存的路径，
			String realPath = request.getSession().getServletContext().getRealPath("/upload/card");
			for (MultipartFile file : uploadFile) {
				if (StringUtils.isEmpty(file.getOriginalFilename())) {
					continue;
				}
				String filePath = updateFile(realPath, file);

				TaskFeedbackFile taskFeedbackFile = new TaskFeedbackFile();
				taskFeedbackFile.setCreateName(UserState.getLoginName());
				taskFeedbackFile.setCreateTime(date);
				taskFeedbackFile.setDelFlag(0);
				taskFeedbackFile.setFileName(file.getOriginalFilename());
				taskFeedbackFile.setFilePath(filePath);
				taskFeedbackFile.setTaskFeedbackId(Integer.valueOf(taskbackId));
				taskService.addTaskBackFile(taskFeedbackFile);
			}
		}
		result.setSuccess(true);
		result.setMsg("共享成功");
		return result;
	}

	private String updateFile(String realPath,MultipartFile files){
		String path = "";
		// 文件原名称
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString().toUpperCase();
		String extension = files.getOriginalFilename()
				.substring(files.getOriginalFilename().lastIndexOf('.'));
		String allName = fileName + extension;
		try {
			// 这里使用Apache的FileUtils方法来进行保存
			FileUtils.copyInputStreamToFile(files.getInputStream(), new File(realPath, allName));
			path = "../upload/card/" + allName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// 新增反馈
	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "back", method = RequestMethod.GET)
	public String back(Model model,int taskId) {
		Task task = taskService.getOne(taskId);
		model.addAttribute("task", task);

		return "task/back";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session, HttpServletRequest request) throws Exception{

		// 获取当前登录人id
		Integer loginId = UserState.getLoginId();

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

		TaskListSearch search = new TaskListSearch();
		search.setAccountId(-1);
		search.setQuadrant("-1");
		search.setStatus("-2");
		search.setSource("-1");
		ServiceResult<Page<Task>> result = taskService.getList(search, search.getPage(),search.getPageSize());

		model.addAttribute("search", search);
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("accList", accListNew);
		model.addAttribute("taskxx", sysDictService.findAllByPid(13));// 任务象限
		model.addAttribute("taskzt", sysDictService.findAllByPid(14));// 任务状态

		return "task/index";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "index", method = RequestMethod.POST)
	public String index(TaskListSearch search, Model model, HttpSession session) throws Exception {
		// 获取当前登录人id
		Integer loginId = UserState.getLoginId();

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
		ServiceResult<Page<Task>> result = taskService.getList(search, search.getPage(),search.getPageSize());

		model.addAttribute("search", search);
		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		model.addAttribute("accList", accListNew);
		model.addAttribute("taskxx", sysDictService.findAllByPid(13));// 任务象限
		model.addAttribute("taskzt", sysDictService.findAllByPid(14));// 任务状态

		return "task/_list";
	}

	// 新增
	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "add", method = RequestMethod.GET)
	public String add(Model model) {

		try {
			// 获取当前登录人id
			Integer loginId = UserState.getLoginId();
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
			// 客户信任人
			List<Map<String, String>> xxrList = contactService.getListForRole(113, loginId);
			// 渠道伙伴
			List<Map<String, String>> qdhbList = contactService.getListForRole(112, loginId);

			model.addAttribute("taskxz", sysDictService.findAllByPid2(15));// 任务性质
			model.addAttribute("taskxx", sysDictService.findAllByPid(13));// 任务象限
			model.addAttribute("accList", accListNew);
			model.addAttribute("xxrList", xxrList);
			model.addAttribute("qdhbList", qdhbList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/add";
	}

	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool add(@Validated({ ValidAdd.class }) Task form, BindingResult result, Model model)
			throws Exception {
		if (StringUtils.isEmpty(form.getPlanExecutorUser()) && StringUtils.isEmpty(form.getPlanExecutorContact())) {
            return new ServiceResultBool(false,"请选择计划执行人！");
		}
		if (StringUtils.isEmpty(form.getQuadrant())) {
			return new ServiceResultBool(false,"请选择任务象限！");
		}
		return taskService.add(form);
	}


	@RequiresAuthentication
	@RequiresPermissions(value = "customer_list")
	@RequestMapping(path = "select_customer_list", method = RequestMethod.GET)
	public String selectCustomerList(Model model, @RequestParam(defaultValue = "false", name = "cache") boolean isCache,
						HttpSession session, HttpServletRequest request) throws Exception {

		Integer loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人

		Integer status = -2;//客户状态

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
			if (StringUtils.isEmpty(status)) {
				search.setStatus(-2);// -1 搁置；-2 全选
			} else {
				search.setStatus(status);
			}
			search.setCustomerType("-1");
			search.setsalesSuccessRate("-1");
			search.setOrderField("order");
			search.setOrderType("desc");
			search.setTraceType(tranceType);
			search.setAccountId(account);
			search.setIsFrom(-1);
			search.setCusSource("-1");
			search.setBuyService("-1");
			search.setFollowState("-1");
			search.setNextPlanState("-1");
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
		model.addAttribute("PAGER", new PageInfo<>(result.getData()));
		model.addAttribute("myself", myself);// 首页点击 客户总数

		model.addAttribute("cpfw", sysDictService.findAllByPid(9));// 产品及服务
		model.addAttribute("khly", sysDictService.findAllByPid(67));// 客户来源-直销
		model.addAttribute("lxr", contactService.getList("112"));;// 客户来源-渠道
		model.addAttribute("xstjzt", sysDictService.findAllByPid2(11));// 销售推进状态

		model.addAttribute("accList", accListNew);
		model.addAttribute("isDeptLeader", isDeptLeader);
		model.addAttribute("isShowTop", false);// 是否显示置顶列

		return "task/select_customer_index";
	}

	public void initSearch(HttpServletRequest request,CustomerListSearch search,boolean isDeptLeader){
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
		if(!StringUtils.isEmpty(indexStatus))search.setStatus(Integer.valueOf(indexStatus));
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
	@RequestMapping(path = "select_customer_list", method = RequestMethod.POST)
	public String selectCustomerList(CustomerListSearch search, Model model, HttpSession session) throws Exception {

		session.setAttribute(key, search);
		int loginId = UserState.getLoginId();
		boolean isDeptLeader = accountService.isLeaderById(loginId);// 是否是部门领导人
		List<AccountOfDept> accList = accountService.getAccOfDeptByAccId(loginId);// 根据当前用户id获取部门的所有成员
		String accIds = "";
		if (accList != null) {
			AccountOfDept first = accList.get(0);
			if (first.isMySelf() && first.getIsDeptManager() == 0) {
				accIds = String.valueOf(first.getId());
			} else {
				for (AccountOfDept item : accList) {
					accIds += item.getId() + ",";
				}
				if (accIds != "")
					accIds = accIds.substring(0, accIds.length() - 1);
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
		model.addAttribute("PAGER", new PageInfo<>(result.getData()));
		model.addAttribute("cpfw", sysDictService.findAllByPid(9));// 产品及服务
		model.addAttribute("khly", sysDictService.findAllByPid(67));// 客户来源-直销
		model.addAttribute("lxr", contactService.getList("112"));;// 客户来源-渠道
		model.addAttribute("xstjzt", sysDictService.findAllByPid2(11));// 销售推进状态

		model.addAttribute("accList", accList);
		model.addAttribute("isShowTop", (search.getAccountId() != -1));// 是否显示置顶列
		return "task/select_customer_list";

	}

}
