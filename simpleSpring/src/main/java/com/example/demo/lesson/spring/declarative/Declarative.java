package com.example.demo.lesson.spring.declarative;

import com.example.demo.lesson.spring.simplestart.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring声明式编程
 */
public class Declarative {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 设置classloader
        // context.setClassLoader(ClassUtils.getDefaultClassLoader());
// context引入xml方式
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
//        xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");

        // 手动扫描
//        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context); scanner.scan("com.example.demo.lesson.spring.declarative");
    }

}
