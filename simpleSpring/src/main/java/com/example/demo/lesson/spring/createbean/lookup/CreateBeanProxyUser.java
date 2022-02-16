package com.example.demo.lesson.spring.createbean.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;

public class CreateBeanProxyUser {

    @Autowired
    CreateBeanProToType2User createBeanProToType2User;

    @Lookup("createBeanProToTypeUser")
    public CreateBeanProToTypeUser getUser() {
        return null;
    }

}
