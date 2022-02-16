package com.example.demo.lesson.spring.simplestart;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserEntity implements FactoryBean<UserFacoryBean> {
    @Resource
    private UserBean userBean;
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
