package com.example.demo.lesson.spring.proxy.advice;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class AdvisorMainExample {
    public static void main(String[] args) {
        AdviceUser target = new AdviceUser();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(new PointcutAdvisor() {
            @Override
            public Pointcut getPointcut() {
                return new StaticMethodMatcherPointcut() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return "test".equals(method.getName());
                    }
                };
            }

            @Override
            public Advice getAdvice() {
                return new BeforeAdviceExample();
            }

            @Override
            public boolean isPerInstance() {
                return false;
            }
        });
        AdviceUser userService = (AdviceUser) proxyFactory.getProxy();
        userService.test();
        userService.test2();
    }
}
