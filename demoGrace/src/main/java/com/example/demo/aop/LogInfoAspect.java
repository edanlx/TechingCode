package com.example.demo.aop;

import com.example.demo.annotations.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Order(1000)
@Slf4j
public class LogInfoAspect {
    /**
     * https://www.cnblogs.com/zhangxufeng/p/9160869.html
     * https://www.cnblogs.com/LemonFive/p/10983875.html
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(* com.example.demo..*.*(..))")
    public void AspectController() {
    }

    @Pointcut("execution(* com.example.demo..*.*(..))")
    public void AspectController2() {
    }

    /**
     * 先执行、先退出
     *
     * @author seal 876651109@qq.com
     * @date 2020/6/3 2:34 PM
     */
    @Around("AspectController() || AspectController2()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("环绕前");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        pjp.getTarget();
        LogAnnotation logAnnotationClass = pjp.getTarget().getClass().getAnnotation(LogAnnotation.class);
        LogAnnotation logAnnotationMethod = methodSignature.getMethod().getAnnotation(LogAnnotation.class);
        if (logAnnotationClass == null && logAnnotationMethod == null) {
            return pjp.proceed();
        }
        LogAnnotation logAnnotation = ObjectUtils.defaultIfNull(logAnnotationMethod, logAnnotationClass);
        StopWatch sw = new StopWatch();
        String className = pjp.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        if (logAnnotation.parameter()) {
            log.info("{}:{}:parameter:{}", className, methodName, pjp.getArgs());
        }
        sw.start();
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            if (logAnnotation.exception()) {
                log.warn(e.getMessage(), e);
                log.info("{}:{}:parameter:{}", className, methodName, pjp.getArgs());
            }
            throw e;
        }
        if (logAnnotation.result()) {
            log.info("{}:{}:result:{}", className, methodName, result);
        }
        sw.stop();
        if (logAnnotation.totalConsume()) {
            log.info("{}:{}:totalConsume:{}s", className, methodName, sw.getTotalTimeSeconds());
        }
        log.debug("环绕后");
        return result;
    }

    /**
     * @description 在连接点执行之前执行的通知
     */
    @Before("AspectController()")
    public void doBefore() {
    }

    /**
     * @description 在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("AspectController()")
    public void doAfter() {
    }

    /**
     * @description 在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("AspectController()")
    public void doAfterReturning() {
    }

    /**
     * @description 在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("AspectController()")
    public void doAfterThrowingGame() {
    }
}
