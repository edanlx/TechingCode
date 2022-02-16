package com.example.demo.lesson.spring.createbean.supplier;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class FactoryMethodExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setInstanceSupplier(new Supplier<Object>() {
            @Override
            public Object get() {
                return new CreateBeanProxyUser();
            }
        });
        context.registerBeanDefinition(CreateBeanProToTypeUser.class.getSimpleName(), beanDefinition);
        context.refresh();
        // 输出targetUser对象
        System.out.println(context.getBean(CreateBeanProToTypeUser.class.getSimpleName()));
    }
}
