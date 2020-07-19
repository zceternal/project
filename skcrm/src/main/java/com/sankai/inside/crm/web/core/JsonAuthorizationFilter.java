package com.sankai.inside.crm.web.core;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class JsonAuthorizationFilter extends FormAuthenticationFilter  {

	
	/**
	 * 所有请求都会经过的方法。
	 */
	@Override
	protected boolean onAccessDenied(final ServletRequest request, final ServletResponse response) throws Exception {

		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		HttpServletResponse httpResponse = WebUtils.toHttp(response);
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				// 可以访问登录页面
				return true;
			}
		} else {
			if (!"XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))) { // 不是ajax请求
				// 保存当前请求并重定向到登录界面
				httpRequest.setAttribute("sessionTimeout", "timeout");
				saveRequestAndRedirectToLogin(httpRequest, httpResponse);
			} else {
				// 保存当前请求并重定向到登录界面
				httpRequest.setAttribute("sessionTimeout", "timeout");
				response.getWriter().append("{\"isNotLogin\":true}");
			   
			}
			return false;
		}
	}

}
