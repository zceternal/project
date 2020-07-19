package com.sankai.inside.crm.web.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class ControllerLogAspect {
	private static final Logger log = LoggerFactory.getLogger(ControllerLogAspect.class);
/*
	//切入点 WEB工程中所有controller类
    private static final String POINTCUT = "execution(* com.sankai.inside.crm.web.controllers.*Controller.*(..))";
    
    *//**
     * 方法执行前,打出入参
     * @param point
     *//*
    @SuppressWarnings("unchecked")
    @Before(POINTCUT)
    public void logArg(JoinPoint point) {

    	//log.info("[Controller]:" + pjp.getSignature().getDeclaringTypeName());
		String methodName = point.getSignature().getName();
		//log.info("[Method]:" + methodName);
		
		Object[] objs = point.getArgs();
		
		Method[] methods = point.getTarget().getClass().getMethods();
		Method targetMethod = null;
		for(Method m : methods) {
			if(m.getName().equals(methodName)) {
				targetMethod = m;
				break;
			}
		}
		
		//log.info("[PathVariable]:");
		StringBuffer params = new StringBuffer("[PathVariable]:");
		
		Annotation[][] annotationz = targetMethod.getParameterAnnotations();
		for(int i = 0;i<annotationz.length;i++) {
			Annotation[] annotations = annotationz[i];
			if(annotations.length>0) {
				Annotation annotation = annotations[0];
				if(annotation.annotationType().equals(PathVariable.class)) {
					PathVariable pv = (PathVariable)annotation;
					//log.info("	" + pv.value() + "---" + objs[i]);
					params.append(pv.value()).append(":").append(objs[i]).append(",");
				}
			}
		}
		
		//log.info("[RequestParam]：");
		params.append(" [RequestParam]：");
		
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Map<String, String[]> map = request.getParameterMap();
    	for(Map.Entry<String, String[]> entry : map.entrySet()) {
    		//log.info("	" + entry.getKey() + "---" + Arrays.asList(entry.getValue()));
    		params.append(entry.getKey()).append("=").append(Arrays.asList(entry.getValue())).append(",");
    	}
		log.info(point.getSignature().toShortString() + "入参:"+params.substring(0, params.length()-1).toString());

    }
    
    *//**
     * 方法执行后，打出出参
     * @param point
     * @param returnObj
     *//*
    @AfterReturning(value = POINTCUT, returning = "returnObj")
    public void logArgAndReturn(JoinPoint point, Object returnObj) {
        log.info(point.getSignature().toShortString() + "出参:{}", returnObj == null ? "" : returnObj.toString());
    }
    
    public String paramsFormat(Object[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null)
                    stringBuilder.append(args[i].toString());
                if (i < args.length - 1)
                    stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
	
    *//**
     * 输出接口耗时
     * @param pjp
     * @return
     * @throws Throwable
     *//*
	@Around(POINTCUT)  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{ 
		long l1 = System.currentTimeMillis();
		Object ret = pjp.proceed();
		long l2 = System.currentTimeMillis();
		log.info("本次请求共耗时：" + (l2-l1) + "ms");
		return ret;
	}*/
}
