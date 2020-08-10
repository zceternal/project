package com.sankai.inside.crm.web.controllers;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
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

	@Resource
	private ISysDictService sysDictService;// 数据字典
	@Autowired
	private ITaskService taskService;
	@Resource
	private AccountService accountService;// 用户服务
	@Resource
	private ContactService contactService;// 联系人

	// 文件上传
	@RequestMapping(path = "uploadFilds", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFilds(@RequestParam(value = "uploadFile") MultipartFile files, HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (files != null) {
			// 获取保存的路径，
			String realPath = request.getSession().getServletContext().getRealPath("/upload/card");
			if (files.isEmpty()) {
				// 未选择文件
				resMap.put("status", "empty");
			} else {
				// 文件原名称
				UUID uuid = UUID.randomUUID();
				String fileName = uuid.toString().toUpperCase();
				String extension = files.getOriginalFilename()
						.substring(files.getOriginalFilename().lastIndexOf('.'));
				String allName = fileName + extension;
				try {
					// 这里使用Apache的FileUtils方法来进行保存
					FileUtils.copyInputStreamToFile(files.getInputStream(), new File(realPath, allName));
					resMap.put("status", "ok");
					resMap.put("path", "../upload/card/" + allName);
				} catch (IOException e) {
					System.out.println("文件上传失败");
					resMap.put("status", "no");
					e.printStackTrace();
				}
			}

		}
		return resMap;
	}


	// 新增反馈
	@RequiresAuthentication
	@RequiresPermissions(value = "task_index")
	@RequestMapping(path = "back", method = RequestMethod.POST)
	public String back(TaskBackSave taskBack, HttpServletRequest request) {

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
				return "0000";
			}

			// 获取保存的路径，
			String realPath = request.getSession().getServletContext().getRealPath("/upload/card");
			for (MultipartFile file : uploadFile) {
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
		return "0000";
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
		search.setStatus("-1");
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

		return taskService.add(form);
	}

}
