package com.sankai.inside.crm.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sankai.inside.crm.service.AccountService;

@Controller
public class LogoutController {

	
	@Autowired
	private AccountService accountService;
	
	
	@RequestMapping(path="logout")
	public String index(){
		accountService.logout();
		return "redirect:/login";
	}
	
}
