package com.example.demo.lesson.spring.proxy.annon.bean.autoproxycreator;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;

public class BeanNameAutoProxyCreatorExample {
    @Bean
    public BeanNameAutoProxyCreator userProxy() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("us*");
        beanNameAutoProxyCreator.setInterceptorNames("aroundAdviceExample");
        return beanNameAutoProxyCreator;
    }
}
