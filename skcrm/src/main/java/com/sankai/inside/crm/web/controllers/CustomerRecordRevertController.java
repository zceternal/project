package com.sankai.inside.crm.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sankai.inside.crm.entity.CustomerRecordRevert;
import com.sankai.inside.crm.entity.CustomerShareDTO;
import com.sankai.inside.crm.entity.CustomerTransDTO;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.service.CustomerRecordRevertService;
import com.sankai.inside.crm.web.model.ValidAdd;

@Controller
@RequestMapping("CustomerRecordRevert")
public class CustomerRecordRevertController {


	@Autowired
	private CustomerRecordRevertService customerRecordRevertService;

	
	
//	@RequiresAuthentication
//	@RequiresPermissions(value="account_add")
	@RequestMapping(path="reply",method=RequestMethod.GET)
	public String reply(){
		return "customer/reply";
	}
	
//	@RequiresAuthentication
//	@RequiresPermissions(value="account_add")
	@RequestMapping(path="reply",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResult reply(@Validated({ValidAdd.class}) CustomerRecordRevert form,BindingResult result, Model model) throws IllegalAccessException, Exception, NoSuchMethodException{
		if(result.hasErrors()) {  
			return new ServiceResult(result.getAllErrors().get(0).getDefaultMessage());
	    }

		return customerRecordRevertService.add(form);
	}
	
	
}
