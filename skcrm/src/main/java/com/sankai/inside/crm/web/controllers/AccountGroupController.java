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
import com.sankai.inside.crm.entity.AccountGroupEntity;
import com.sankai.inside.crm.entity.AccountGroupFrom;
import com.sankai.inside.crm.entity.AccountGroupSearch;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Module;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.AccountGroupService;
import com.sankai.inside.crm.service.AccountService;
/*import com.sankai.inside.crm.web.core.SuperAccount;*/
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.AccountForm;
import com.sankai.inside.crm.web.model.AccountPwdForm;
import com.sankai.inside.crm.web.model.FormPage;
import com.sankai.inside.crm.web.model.ValidAdd;
import com.sankai.inside.crm.web.model.ValidEdit;

@Controller
@RequestMapping("group")
public class AccountGroupController {


	@Autowired
	private AccountGroupService groupService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(path="index",method=RequestMethod.GET)
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_index")
	public String index(Model model,FormPage page){
		
		AccountGroupSearch search = new AccountGroupSearch();
		List<AccountGroupEntity> result = groupService.getList(search);

		model.addAttribute("model", result);
		return "group/index";
		
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_index")
	@RequestMapping(path="index",method=RequestMethod.POST)
	public String index(HttpServletRequest request,Model model,FormPage page){
		AccountGroupSearch search = new AccountGroupSearch();
		search.setContent(request.getParameter("content"));
		List<AccountGroupEntity> result = groupService.getList(search);

		model.addAttribute("model", result);
		return "group/_list";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_delete")
	@RequestMapping(path="delete.ajax",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool delete(@RequestParam("id") int id){
		return groupService.deleteByPriKey(id);
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_add")
	@RequestMapping(path="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("users", initAccountList());
		return "group/add";
	}
	
	
	@SuppressWarnings("rawtypes")
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_add")
	@RequestMapping(path="add",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResult add(@Validated({ValidAdd.class}) AccountGroupFrom form,BindingResult result, Model model) throws Exception{
		
		if(result.hasErrors()) {  
			return new ServiceResult(result.getAllErrors().get(0).getDefaultMessage());
	    }

		return groupService.add(form);
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_edit")
	@RequestMapping(path="edit",method=RequestMethod.GET)
	public String edit(@RequestParam int id, Model model){
		ServiceResult<AccountGroupEntity> result = groupService.findByPriKey(id);
		model.addAttribute("users", initAccountList());
		if(!result.isSuccess()){ 
			model.addAttribute("msg", result.getMsg());
			return "error";
			}
		
		model.addAttribute("model",result.getData());
		return "group/edit";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="group_edit")
	@RequestMapping(path="edit",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool edit(@Validated({ValidEdit.class}) AccountGroupFrom form, BindingResult result, Model model) throws Exception{
		
		if(result.hasErrors()) {  
			return new ServiceResultBool(result.getAllErrors().get(0).getDefaultMessage());
	    }

		return groupService.modify(form);
	}
	
	@RequestMapping(path="groupNameCall.ajax",method=RequestMethod.POST)
	public void existsLoginNameCall(@RequestParam String fieldId, @RequestParam String fieldValue,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ServiceResultBool result = groupService.existsByGroupName(request.getParameter("fieldValue"));
		response.getWriter().write("[\"" + fieldId + "\"," + result.isSuccess() + "]" );
		
	}
	/**
	 * 获取用户列表
	 * @return
	 */
	private List<Account> initAccountList()
	{
		return accountService.findIdAndNameAll(UserState.getLoginId());
	}
}
