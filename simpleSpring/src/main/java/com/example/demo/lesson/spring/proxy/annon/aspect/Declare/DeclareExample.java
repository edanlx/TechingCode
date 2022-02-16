package com.example.demo.lesson.spring.proxy.annon.aspect.Declare;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
// EnableAspectJAutoProxy可以写在config类上
@EnableAspectJAutoProxy
public class DeclareExample {
    @DeclareParents(value = "com.example.demo.lesson.spring.proxy.annon.User", defaultImpl = UserServiceImpl.class)
    private UserService userService;
}
