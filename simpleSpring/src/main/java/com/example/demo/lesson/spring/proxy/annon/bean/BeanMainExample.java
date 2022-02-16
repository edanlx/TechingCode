package com.example.demo.lesson.spring.proxy.annon.bean;

import com.example.demo.lesson.spring.proxy.annon.User;
import com.example.demo.lesson.spring.proxy.annon.bean.autoproxycreator.BeanNameAutoProxyCreatorExample;
import com.example.demo.lesson.spring.proxy.annon.bean.defaultadvisor.DefaultAdvisorExample;
import com.example.demo.lesson.spring.proxy.annon.bean.proxyfactory.ProxyFactoryBeanExample;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanMainExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(User.class);
//        proxyFactoryBeanExample(context);
//        beanNameAutoProxyCreatorExample(context);
        defaultAdvisorExampleExample(context);
    }

    /**
     * 代理一个对象
     *
     * @param context
     */
    public static void proxyFactoryBeanExample(AnnotationConfigApplicationContext context) {
        context.registerBean(ProxyFactoryBeanExample.class);
        context.refresh();
        // 取@bean的方法名
        context.getBean("userProxy", User.class).test();
    }

    /**
     * 同时代理多个对象
     *
     * @param context
     */
    public static void beanNameAutoProxyCreatorExample(AnnotationConfigApplicationContext context) {
        context.registerBean(BeanNameAutoProxyCreatorExample.class);
        context.registerBean(AroundAdviceExample.class);
        context.refresh();
        context.getBean("user", User.class).test();
    }

    /**
     * 按照方法名代理
     *
     * @param context
     */
    public static void defaultAdvisorExampleExample(AnnotationConfigApplicationContext context) {
        context.registerBean(DefaultAdvisorExample.class);
        context.registerBean(AroundAdviceExample.class);
        context.refresh();
        context.getBean("user", User.class).test();
    }
}
