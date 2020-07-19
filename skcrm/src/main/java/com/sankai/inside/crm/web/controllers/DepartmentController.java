package com.sankai.inside.crm.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
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
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Module;
import com.sankai.inside.crm.entity.ServiceResult;
import com.sankai.inside.crm.entity.ServiceResultBool;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.service.ModuleService;
import com.sankai.inside.crm.web.core.UserState;
import com.sankai.inside.crm.web.model.DepartmentForm;
import com.sankai.inside.crm.web.model.FormPage;
import com.sankai.inside.crm.web.model.ValidAdd;
import com.sankai.inside.crm.web.model.ValidEdit;

@Controller
@RequestMapping("department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private AccountService accountService;
	
	
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_mamage")
	@RequestMapping(path="index",method=RequestMethod.GET)
	public String index(Model model,FormPage page) throws IOException{
		
		Integer loginId = UserState.getLoginId();
		Integer deptId = 0;
		if(loginId>0)
			deptId = accountService.getDeptIdById(loginId);// 根据当前登录Id获取部门Id
		ServiceResult<Page<Department>> result=departmentService.getDepartmentsByPage(deptId.toString(), page.getPage(),page.getPageSize());
		model.addAttribute("model",result.getData());
		model.addAttribute("pid",deptId);
		model.addAttribute("pager",new PageInfo<>(result.getData()));

		return "department/index";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_mamage")
	@RequestMapping(path="/index",method=RequestMethod.POST)
	public String index(@RequestParam("id") String id, HttpServletRequest request,Model model,FormPage page){
		if(id==null||id==""){
			id="0";
		}
		ServiceResult<Page<Department>> result=departmentService.getDepartmentsByPage(id, page.getPage(), page.getPageSize());
		model.addAttribute("model",result.getData());
		model.addAttribute("pager",new PageInfo<>(result.getData()));
		return "department/depart_list";
		
	}
	
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_delete")
	@RequestMapping(path="delete.ajax",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool delete(@RequestParam("id") int id){
		return departmentService.remove(id);
	}
	
	
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_add")
	@RequestMapping(path="add",method=RequestMethod.GET)
	public String add(@RequestParam("pid") String pid,Model model)throws IOException {
		String name=departmentService.returnName(pid);
		
		int loginId = UserState.getLoginId();
		Module[] result = accountService.getModuleById(loginId);
		
		model.addAttribute("name",name);
		model.addAttribute("model",result);
		return "department/add";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_add")
	@RequestMapping(path="add",method=RequestMethod.POST)
	@ResponseBody
public ServiceResult add(HttpServletRequest request,@RequestParam("pid") int pid,@Validated({ValidAdd.class}) DepartmentForm form, BindingResult result, Model model) throws IllegalAccessException, Exception, NoSuchMethodException{

		if(result.hasErrors()) {  
			return new ServiceResult(result.getAllErrors().get(0).getDefaultMessage());
	    }
		String[] authKey = request.getParameterValues("authKey");
		authKey = authKey == null ? new String[0]:authKey;
		return departmentService.add(form,Arrays.asList(authKey));
	}
	
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_edit")
	@RequestMapping(path="edit",method=RequestMethod.GET)
	public String edit(@RequestParam int id, Model model){
		
		ServiceResult<Department> result = departmentService.getDepartment(id);
		if(!result.isSuccess()){ 
			model.addAttribute("msg", result.getMsg());
			return "error";
			}
		model.addAttribute("model",result.getData());
		return "department/edit";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_edit")
	@RequestMapping(path="edit",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResult edit(@Validated({ValidEdit.class}) DepartmentForm form, BindingResult result, Model model) throws IllegalAccessException, Exception, NoSuchMethodException{
		
		
		if(result.hasErrors()) {  
			return new ServiceResult(result.getAllErrors().get(0).getDefaultMessage());
	    }

		return departmentService.save(form);
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value={"dept_mamage","account_add","account_edit"},logical=Logical.OR)
	@RequestMapping(value = "/ajaxManageTree", method = RequestMethod.POST)
	public @ResponseBody List<Department> ajaxManageTree() throws Exception{
		
		List<Department> tree = departmentService.selectAllTree();
		Department root = new Department();
		
		Integer loginId = UserState.getLoginId();
		if(loginId<=0)//管理员可有跟节点
		{
			root.setName("部门管理");
			root.setOpen(true);
			root.setChecked(true);
			root.setPid(-1);
			root.setId(0);
			tree.add(0,root);
		}
	    return tree;
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_edit")
	@RequestMapping(value = "up.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult<Integer> up(int id,int pid){
		return departmentService.updateOrder(id, pid,1);	
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_edit")
	@RequestMapping(value = "down.ajax", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult<Integer> down(int id,int pid){
		return departmentService.updateOrder(id, pid,2);
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_auth")
	@RequestMapping(path="auth",method=RequestMethod.GET)
	public String auth(@RequestParam int id,Model model) throws IOException{
		
		Module[] result = null;
		
		int loginId = UserState.getLoginId();
		
		if(loginId <= 0){
			result = moduleService.GetModules();
		}else{
			List<String> data = accountService.getAuthById(loginId);
			result = accountService.filterAuth(data);
		}
		
		model.addAttribute("model", departmentService.getModuleByDept(id,result));
		
		return  "department/set_authority";
	}
	
	//@RequiresAuthentication
	//@RequiresPermissions(value="dept_auth")
	@RequestMapping(path="auth",method=RequestMethod.POST)
	@ResponseBody
	public ServiceResultBool auth(@RequestParam int deptId,HttpServletRequest request){
		
		String[] authKey = request.getParameterValues("authKey");
		authKey = authKey == null ? new String[0]:authKey;
		this.departmentService.saveAuth(Arrays.asList(authKey),deptId);
		
		return new ServiceResultBool();
		
	}

}
