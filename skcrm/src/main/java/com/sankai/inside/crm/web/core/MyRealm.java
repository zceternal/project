package com.sankai.inside.crm.web.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sankai.inside.crm.core.utils.SpringBeanUtil;
import com.sankai.inside.crm.service.AccountService;

public class MyRealm extends AuthorizingRealm {
	
	public static Logger logger = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private  AccountService accountSevice ;
	private List<String> list =new ArrayList<>();
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String id = (String) principals.getPrimaryPrincipal();
		 
		try {
			list = accountSevice.getAuthById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AuthorizationInfo authorizationInfo = new AuthorizationInfo() {

			@Override
			public Collection<String> getStringPermissions() {

				return list;
			}

			@Override
			public Collection<String> getRoles() {
				List<String> list = new ArrayList<>();
				list.add("user");
				return list;
			}

			@Override
			public Collection<Permission> getObjectPermissions() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if (token.getUsername() != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getUsername(),
					new String(token.getPassword()), this.getName());
			this.setSession("accountId", token.getUsername());
			return authcInfo;
		}
		return null;
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			// session.setTimeout(1000*3600*24);
			// 设置从最后一次点击开始计算的空闲时间为1小时
			session.setTimeout(1000 * 3600 * 24);
			logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
