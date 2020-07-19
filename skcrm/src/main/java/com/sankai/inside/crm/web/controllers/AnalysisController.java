package com.sankai.inside.crm.web.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sankai.inside.crm.entity.Analysis;
import com.sankai.inside.crm.entity.AnalysisSearch;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.service.AnalysisService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.FormPage;

@Controller
@RequestMapping("analysis")
public class AnalysisController {
	
	@Autowired
	private AnalysisService analysisService;
	
	@Autowired
	private DepartmentService deptService;
	
	
	
	@RequestMapping(path="index",method=RequestMethod.GET)
	@RequiresAuthentication
	@RequiresPermissions(value="analysis_index")
	public String index(Model model,FormPage page) throws IOException{
		
		 Calendar c = Calendar.getInstance();
	     c.add(Calendar.MONTH, -1);
		 model.addAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
		 model.addAttribute("endTime", new SimpleDateFormat("yyyy-MM.dd").format(new Date()));
		return "analysis/analysis_index";
	}
	
	@RequiresAuthentication
	@RequiresPermissions(value="analysis_index")
	@ResponseBody
	@RequestMapping(path="index",method=RequestMethod.POST)
	public Object index(String startTime,String endTime,String accountIds){

		List<String> str=new ArrayList<>();
		for (int i = 0; i < accountIds.split(",").length; i++) {
			str.add(accountIds.split(",")[i]);
		}
		AnalysisSearch search=new AnalysisSearch();
		search.setEndTime(endTime);
		search.setStartTime(startTime);
		search.setAccountIds(str);
		search.setStatus(-3);
		List<Analysis> list=analysisService.list(search);
	
		JSONObject result=new JSONObject();
		
		JSONArray data=new JSONArray();
		
		for(Analysis vo:list){
			JSONObject obj=new JSONObject();
			String s = optStr(vo.getStatuName());
			obj.put("name", s + "(" + vo.getCount() + ")");
			obj.put("value", vo.getCount());
			data.add(obj);
		}
		result.put("data", data);
		result.put("title", "代岳强、高宏刚");
		
		return result;
	}
	private static String optStr(String val)
	{
		String s = val;
		if(val.indexOf("(")>0||val.indexOf("（")>0){
			int a = val.indexOf("(");
			int b = val.indexOf("（");
			if(a>b)
				s = val.substring(0,a);				
			else
				s = val.substring(0,b);
		}
		return s;
	}
	@RequiresAuthentication
	@RequiresPermissions(value="analysis_index")
	@ResponseBody
	@RequestMapping(path="indexCount",method=RequestMethod.POST)
	public Object indexCount(String startTime,String endTime,String accountIds){

		List<String> str=new ArrayList<>();
		for (int i = 0; i < accountIds.split(",").length; i++) {
			str.add(accountIds.split(",")[i]);
		}
		AnalysisSearch search=new AnalysisSearch();
		search.setEndTime(endTime);
		search.setStartTime(startTime);
		search.setAccountIds(str);
		search.setStatus(-3);
		List<Analysis> list=analysisService.count(search);
	
		JSONObject result=new JSONObject();
		
		JSONArray data=new JSONArray();
		int i=0;
		for(Analysis vo:list){
			i+=vo.getCount();
			System.out.println("值："+i);
			JSONObject obj=new JSONObject();
			obj.put("name", vo.getDays());
			obj.put("value", i);
			obj.put("status", vo.getStatuName());
			data.add(obj);
			
		}
		result.put("data", data);
		result.put("title", "代岳强、高宏刚");
		
		return result;
	}
	
	
	@RequiresAuthentication
	@RequiresPermissions(value="analysis_index")
	@RequestMapping(value = "/ajaxManageTreeView", method = RequestMethod.POST)
	public @ResponseBody List<Department> ajaxManageTree(){
		
		List<Department> tree = deptService.selectUserAllTree();
		Department root = new Department();
		
		Integer loginId = UserState.getLoginId();
		if(loginId<=0)//管理员可有跟节点
		{
			root.setName("部门管理");
			root.setOpen(false);
			root.setChecked(false);
			root.setPid(-1);
			root.setId(0);
			tree.add(0,root);
		}
		
		
		
	    return tree;
	}
}
