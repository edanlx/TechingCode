package com.example.demo.lesson.spring.proxy.annon.bean.defaultadvisor;

import com.example.demo.lesson.spring.proxy.annon.bean.AroundAdviceExample;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;

public class DefaultAdvisorExample {
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("test");
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(pointcut);
        defaultPointcutAdvisor.setAdvice(new AroundAdviceExample());
        return defaultPointcutAdvisor;
    }


    @Bean
    /**
     * 也可以换成@Import(DefaultAdvisorAutoProxyCreator)
     */
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
}
