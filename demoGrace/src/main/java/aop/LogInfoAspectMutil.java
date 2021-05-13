package aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(100)
@Slf4j
public class LogInfoAspectMutil {
    /**
     * https://www.cnblogs.com/zhangxufeng/p/9160869.html
     * https://www.cnblogs.com/LemonFive/p/10983875.html
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void AspectController(){}

    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void AspectController2(){}

    /**
    * 先执行、先退出
    * @author seal 876651109@qq.com
    * @date 2020/6/3 2:34 PM
    */
    @Around("AspectController() || AspectController2()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("环绕前");
        Object result = pjp.proceed();
        log.debug("环绕后");
        return result;
    }
    /**
     * @description  在连接点执行之前执行的通知
     */
    @Before("AspectController()")
    public void doBefore(){
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("AspectController()")
    public void doAfter(){
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("AspectController()")
    public void doAfterReturning(){
    }

    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("AspectController()")
    public void doAfterThrowingGame(){
    }
}
