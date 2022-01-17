package com.example.demo.lesson.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("user")
public class UserEntity implements FactoryBean<UserFacoryBean> {

    @Resource
    private UserBean userBean;

    @Value("${names:1}")
    private String name;
    @Override
    public UserFacoryBean getObject()  {
        System.out.println("实例化UserFacoryBean");
        return new UserFacoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return UserFacoryBean.class;
    }
}
