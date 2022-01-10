package com.example.demo.lesson.grace.springloader;


import com.example.demo.annotations.TrimNotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@Order(1)
public class SpringLoaderExampleTest implements ISpringLoaderExampleTest<String> {

    public static class SpringLoaderExampleTestMember {

    }

    @TrimNotNull
    String str1;
    String str2;

    @Valid
    public static void test1(String firstName) {

    }
}
