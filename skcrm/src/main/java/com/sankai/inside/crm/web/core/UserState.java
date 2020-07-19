package com.sankai.inside.crm.web.core;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.sankai.inside.crm.core.utils.SpringBeanUtil;
import com.sankai.inside.crm.entity.Account;
import com.sankai.inside.crm.service.AccountService;
import com.sankai.inside.crm.service.DepartmentService;
import com.sankai.inside.crm.service.ISysDictService;

public final class UserState {
	
	private static AccountService accountService;
	static{
		accountService = SpringBeanUtil.getBean(AccountService.class);
	}
	/**
	 * 获取当前登录Id
	 * @return
	 */
	public static int getLoginId(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipals() == null) return -1;
		String result = (String) subject.getPrincipals().getPrimaryPrincipal();
	    return Integer.parseInt(result);
	}
	/**
	 * 获取当前登录名称
	 * @return
	 */
	public static String getLoginName(){
		Integer id = getLoginId();
		Account result = accountService.getNameAndStateById(id);
		if(result==null) return "无";
		return result.getName();
	}
	/**
	 * 当前登录人是否是管理员
	 * @return
	 */
	public static Boolean isAdmin(){
		Integer id = getLoginId();
		return id <= 0;
	}
}
