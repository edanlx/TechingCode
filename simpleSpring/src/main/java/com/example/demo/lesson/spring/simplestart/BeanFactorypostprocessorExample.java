package com.example.demo.lesson.spring.simplestart;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanFactorypostprocessorExample implements BeanFactoryPostProcessor {

    public BeanFactorypostprocessorExample(){
        System.out.println("BeanfactorypostprocessorExample");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("this is beanFactory");
    }
}
