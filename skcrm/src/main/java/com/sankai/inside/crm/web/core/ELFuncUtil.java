package com.sankai.inside.crm.web.core;

import com.sankai.inside.crm.core.utils.SpringBeanUtil;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.entity.Department;
import com.sankai.inside.crm.entity.Dict;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.service.ISysDictService;

public class ELFuncUtil {
	
	
	static{
		accountService = SpringBeanUtil.getBean(AccountService.class);
		departmentService = SpringBeanUtil.getBean(DepartmentService.class);
		sysDictService = SpringBeanUtil.getBean(ISysDictService.class);
	}
	private static AccountService accountService;
	private static DepartmentService departmentService;
	private static ISysDictService sysDictService;
	
	public static String sex(Integer val){
		switch (val) {
		case 1:
			return "男";
		case 0:
			return "女";
		default:
			return "未知";
		}
	}
	
    public static String getAccountName(Integer id){
    	if(id==null || id == 0) return "<lable style='color:red'>无</lable>";
    	Account result = accountService.getNameAndStateById(id);
    	if(result == null) return "<lable style='color:red'>无</lable>";
    	return result.getName() + (result.getState() == -1 ? "（<lable style='color:red'>已删除</lable>）":"");
    }
    
    public static String getDictName(Integer id){
    	if(id==null || id == 0) return "<lable style='color:red'>无</lable>";
    	Dict result = sysDictService.getDictById(id);
    	if(result == null) return "<lable style='color:red'>无</lable>";
    	return result.getName() + (result.getState() == -1 ? "（<lable style='color:red'>已删除</lable>）":"");
    }
    
    public static String getDeptName(Integer id){
    	if(id==null || id == 0) return "<lable style='color:red'>无</lable>";
    	Department result = departmentService.getDeptById(id);
    	if(result == null) return "<lable style='color:red'>无</lable>";
    	return result.getName() + (result.getState() == -1 ? "（<lable style='color:red'>已删除</lable>）":"");
    }
}
