package com.sankai.inside.crm.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.web.core.Prevent;
import com.sankai.inside.crm.web.core.PreventMark;
import com.sankai.inside.crm.web.model.LoginForm;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;
	
	
	@RequestMapping(path="login",method=RequestMethod.GET)
	public String index(){
		return "login";
	}
	
	@RequestMapping(path="login",method=RequestMethod.POST)
	public String index(LoginForm form, Model model){
		
		ServiceResult<Account> result = accountService.login(form.getUsername(), form.getPassword());
		
		if(result.isSuccess()){
			return "redirect:index";
		}
		model.addAttribute("error", result.getMsg());
		return "login";
	}
	
}
