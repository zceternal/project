package com.sankai.inside.crm.web.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.sankai.inside.crm.core.utils.MD5Util;

@Component
@Aspect
public class PreventAspect {
private static final Logger log = LoggerFactory.getLogger(PreventAspect.class);
	
    @Autowired
    private CacheManager cacheManager;

	@Around(value = "@annotation(prevent)")
	public Object doBasicProfiling(ProceedingJoinPoint pjp, Prevent prevent) throws Throwable {
		//获取被调用的方法
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method targetMethod = signature.getMethod();
		
		//获取被调用方法签名中，防止并发标识的参数
		Annotation[][] annotationz = targetMethod.getParameterAnnotations();
		String redisMark = "";
		Object[] args = pjp.getArgs();
		for (int i = 0; i < annotationz.length; i++) {
			Annotation[] annotations = annotationz[i];
			for (int y = 0; y < annotations.length; y++) {
				Annotation annotation = annotations[y];
				if (annotation instanceof PreventMark) {
					redisMark += "_" + args[i];
				}
			}
		}

		String suffix = pjp.getTarget().getClass().getSimpleName() + "_" + targetMethod.getName();

		
		String key = suffix + MD5Util.getMD5String(redisMark);//标识被调用方法的请求key

		Cache cache = cacheManager.getCache("prevent-cache-pool");
		
		Object val = cache.get(key);
		
		if(val == null){

			Object object = pjp.proceed();
			cache.put(key, true);
			return object;
		}
		log.info("操作过于频繁,Mark={}",suffix+redisMark);
		return "操作过于频繁，请稍后再试";  //返回信息，可以统一封装一个对象中。例如dubbo接口返回对象Result中。TODO：WEB目前没有统一返回的业务对象。使用方自定义吧。
	}
}
