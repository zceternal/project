package com.sankai.inside.crm.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ch.qos.logback.core.subst.Token.Type;


public class SpringBeanUtil implements ApplicationContextAware {
	/**
	 * appCtx:上下文
	 */
	private static ApplicationContext appCtx;

	/**
	  * 实现ApplicationContextAware接口的回调方法，设置上下文环境  
	  * @param applicationContext
	  * @throws BeansException
	  */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appCtx = applicationContext;
	}
	/**
	 * 获取对象 
	 * @param beanName bean的名字
	 * @return 返回一个bean对象
	 */
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}
	
	/**
	 * 获取对象 
	 * @param <T>
	 * @param requiredType 
	 * @param beanName bean的类型
	 * @return 返回一个bean对象
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return appCtx.getBean(requiredType);
	}
	
	/**
	 * 是否存在
	 * @param beanName
	 * @return
	 */
	public static boolean existsBean(String beanName){
		return appCtx.containsBean(beanName);
	}
}
