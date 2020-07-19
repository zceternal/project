package com.sankai.inside.crm.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Module;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.service.ISysDictService;
import com.sankai.inside.crm.service.ModuleService;
/*import com.sankai.inside.crm.web.core.SuperAccount;*/
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.AccountForm;
import com.sankai.inside.crm.web.model.AccountPwdForm;
import com.sankai.inside.crm.web.model.FormPage;
import com.sankai.inside.crm.web.model.ValidAdd;
import com.sankai.inside.crm.web.model.ValidEdit;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ISysDictService dictService;// 字典

	@RequestMapping(path = "index", method = RequestMethod.GET)
	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_mamage")
	public String index(Model model, FormPage page) {

		ServiceResult<Page<Account>> result = accountService.getAccountsByPage("", page.getPage(), page.getPageSize(),
				1);

		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));
		return "account/index";

	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_mamage")
	@RequestMapping(path = "index", method = RequestMethod.POST)
	public String index(HttpServletRequest request, Model model, FormPage page) {
		int getState = Integer.valueOf(request.getParameter("state"));
		ServiceResult<Page<Account>> result = accountService.getAccountsByPage(request.getParameter("content"),
				page.getPage(), page.getPageSize(), getState);

		model.addAttribute("model", result.getData());
		model.addAttribute("pager", new PageInfo<>(result.getData()));

		return "account/account_list";

	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_delete")
	@RequestMapping(path = "delete.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool delete(@RequestParam("id") int id,@RequestParam("state") int state) {
		return accountService.remove(id,state);
	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_recover")
	@RequestMapping(path = "recover.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool recover(@RequestParam("id") int id) {
		return accountService.recoverAccountState(id);
	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_add")
	@RequestMapping(path = "add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("dictJllx", dictService.getDictByType(5));// 记录类型字典
		return "account/add";
	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_add")
	@RequestMapping(path = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult add(@Validated({ ValidAdd.class }) AccountForm form, @RequestParam int type,
			BindingResult result, Model model) throws IllegalAccessException, Exception, NoSuchMethodException {

		if (result.hasErrors()) {
			return new ServiceResult(result.getAllErrors().get(0).getDefaultMessage());
		}

		return accountService.add(form, type);
	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_edit")
	@RequestMapping(path = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam int id, Model model) {

		ServiceResult<Account> result = accountService.getEasyAccount(id);
		if (!result.isSuccess()) {
			model.addAttribute("msg", result.getMsg());
			return "error";
		}
		Department dept = departmentService.getDeptById(result.getData().getDeptId());
		model.addAttribute("deptName", dept == null ? "" : dept.getName());
		model.addAttribute("model", result.getData());
		model.addAttribute("dictJllx", dictService.getDictByType(5));// 记录类型字典
		return "account/edit";
	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_edit")
	@RequestMapping(path = "edit", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool edit(@Validated({ ValidEdit.class }) AccountForm form, BindingResult result, Model model)
			throws IllegalAccessException, Exception, NoSuchMethodException {

		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}

		return accountService.save(form);
	}

	@RequestMapping(path = "loginNameCall.ajax", method = RequestMethod.POST)
	public void existsLoginNameCall(@RequestParam String fieldId, @RequestParam String fieldValue,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServiceResultBool result = accountService.existsByLoginName(request.getParameter("fieldValue"));
		response.getWriter().write("[\"" + fieldId + "\"," + result.isSuccess() + "]");

	}

	@RequestMapping(path = "numberCall.ajax", method = RequestMethod.POST)
	public void existsNumberCall(@RequestParam String fieldId, @RequestParam String fieldValue,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServiceResultBool result = accountService.existsByNumber(request.getParameter("fieldValue"));
		response.getWriter().write("[\"" + fieldId + "\"," + result.isSuccess() + "]");

	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_info")
	@RequestMapping(path = "accountInfo", method = RequestMethod.GET)
	public String accountInfo(Model model) {
		int id = UserState.getLoginId();
		ServiceResult<Account> result = accountService.getEasyAccount(id);
		if (!result.isSuccess()) {
			model.addAttribute("msg", result.getMsg());
			return "error";
		}
		model.addAttribute("model", result.getData());
		return "account/accountInfo";
	}

	////@RequiresAuthentication
	////@RequiresPermissions(value = "account_info")
	@RequestMapping(path = "accountInfo", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool accountInfo(@Validated({ ValidEdit.class }) AccountForm form, BindingResult result,
			Model model) throws IllegalAccessException, Exception, NoSuchMethodException {

		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}

		return accountService.saveAccountInfo(form);
	}

	//@RequiresAuthentication
	//@RequiresPermissions(value = "account_edit_pwd")
	@RequestMapping(path = "editPwd", method = RequestMethod.GET)
	public String editPwd(Model model) {

		return "account/editPwd";
	}

	//@RequiresAuthentication
	//@RequiresPermissions(value = "account_edit_pwd")
	@RequestMapping(path = "editPwd", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool editPwd(@Validated({ ValidEdit.class }) AccountPwdForm form, BindingResult result,
			Model model) throws IllegalAccessException, Exception, NoSuchMethodException {

		if (result.hasErrors()) {
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
		}
		/* if(UserName.equals(SuperAccount.getLogin_name())) */
		return accountService.saveAccountPwd(form);
	}

	//@RequiresAuthentication
	//@RequiresPermissions(value = "account_reset_password")
	@RequestMapping(path = "resetPwd.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool resetPwd(@RequestParam int id)
			throws IllegalAccessException, Exception, NoSuchMethodException {

		return accountService.resetPwd(id);
	}

	/*
	 * 分配权限
	 */
	//@RequiresAuthentication
	//@RequiresPermissions(value = "account_auth")
	@RequestMapping(path = "auth", method = RequestMethod.GET)
	public String auth(@RequestParam int id, Model model) throws IOException {

		Module[] result = null;

		int loginId = UserState.getLoginId();

		if (loginId <= 0) {
			result = moduleService.GetModules();
		} else {
			List<String> data = accountService.getAuthById(loginId);
			result = accountService.filterAuth(data);
		}

		model.addAttribute("model", accountService.getModuleByAccount(id, result));

		return "account/set_authority";
	}

	//@RequiresAuthentication
	//@RequiresPermissions(value = "account_auth")
	@RequestMapping(path = "auth", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool auth(@RequestParam int accountId, HttpServletRequest request) {

		String[] authKey = request.getParameterValues("authKey");
		authKey = authKey == null ? new String[0] : authKey;
		this.accountService.saveAuth(Arrays.asList(authKey), accountId);

		return new ServiceResultBool();

	}

	/*
	 * // 上传名片
	 * 
	 * @RequestMapping(path = "uploadCard", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Object uploadCard(@RequestParam(value =
	 * "uploadVistCard") MultipartFile uploadVistCard, HttpServletRequest
	 * request, HttpServletResponse response) { Map<String, Object> resMap = new
	 * HashMap<String, Object>(); if (uploadVistCard != null) { // 获取保存的路径，
	 * String realPath =
	 * request.getSession().getServletContext().getRealPath("/upload/card"); if
	 * (uploadVistCard.isEmpty()) { // 未选择文件 resMap.put("status", "empty"); }
	 * else { // 文件原名称 UUID uuid = UUID.randomUUID(); String fileName =
	 * uuid.toString().toUpperCase(); String extension =
	 * uploadVistCard.getOriginalFilename()
	 * .substring(uploadVistCard.getOriginalFilename().lastIndexOf('.')); String
	 * allName = fileName + extension; try { // 这里使用Apache的FileUtils方法来进行保存
	 * FileUtils.copyInputStreamToFile(uploadVistCard.getInputStream(), new
	 * File(realPath, allName)); resMap.put("status", "ok"); resMap.put("path",
	 * "../upload/card/" + allName); } catch (IOException e) {
	 * System.out.println("文件上传失败"); resMap.put("status", "no");
	 * e.printStackTrace(); } }
	 * 
	 * } return resMap; }
	 */

}
