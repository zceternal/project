package com.sankai.inside.crm.web.controllers;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.entity.*;
import com.sankai.inside.crm.service.*;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.*;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
			model.addAttribute("taskxz", sysDictService.findAllByPid2(15));// 任务性质
			model.addAttribute("taskxx", sysDictService.findAllByPid(13));// 任务象限

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "task/add";
	}


}
