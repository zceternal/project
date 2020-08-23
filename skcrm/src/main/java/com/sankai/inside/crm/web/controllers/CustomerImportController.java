package com.sankai.inside.crm.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sankai.inside.crm.entity.CustomerList;
import com.sankai.inside.crm.entity.CustomerListSearch;
import com.sankai.inside.crm.entity.ImportExcelEntity;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.CustomerImportService;
import com.sankai.inside.crm.service.ICustomerService;
import com.sankai.inside.crm.web.core.ExcelTool;

@Controller
@RequestMapping("customerImport")
@SuppressWarnings("rawtypes")
public class CustomerImportController {

	@Resource
	private ExcelTool importExcelTool;
	@Autowired
	private CustomerImportService customerImport;
	@Resource
	private ICustomerService customerServiceImpl;
	
	@RequiresAuthentication
	@RequestMapping(path="upload_excel",method=RequestMethod.GET)
	public String uploadExcel()throws IOException {
		
		return "customerImport/upload_excel";
	}
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_add")
	@RequestMapping(path="upload_excel",method=RequestMethod.POST)
	@ResponseBody
public ServiceResultBool uploadExcel(HttpServletRequest request,@RequestParam("physicalPath") String physicalPath) throws IllegalAccessException, Exception, NoSuchMethodException{
		ServiceResult list=ExcelTool.getAllByExcel(physicalPath);
		if (list.isSuccess()==false) {
			return new ServiceResultBool(false,list.getMsg());
		}
		ServiceResultBool result=customerImport.CusImport((List<ImportExcelEntity>)list.getData());
		if (result.isSuccess()==false) {
			return new ServiceResultBool(false,result.getMsg());
		}
		return new ServiceResultBool(true,result.getMsg());
	}
	
	@RequestMapping(path="export_excel",method=RequestMethod.GET)
	@ResponseBody
public ServiceResultBool exportExcel(HttpServletRequest request) throws IllegalAccessException, Exception, NoSuchMethodException{
				String traceType = request.getParameter("traceType");
				String accountId = request.getParameter("accountId");
				
		CustomerListSearch search = new CustomerListSearch();
		search.setTraceType(traceType);
		search.setAccountId((accountId==null?-100:Integer.valueOf(accountId)));
		search.setPrincipalStr(accountId);
		
		search.setStatus(-1);
		search.setCustomerType("-1");
		search.setsalesSuccessRate("-1");
		search.setOrderField("order");
		search.setOrderType("desc");
				
		ServiceResult<List<CustomerList>> result = customerServiceImpl.getListForExcel(search);

		String a =	ExcelTool.ExportExcel("D://aa//cus.xls", result.getData());
		return new ServiceResultBool();
	}
	
	
	
}
