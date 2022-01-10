package com.example.demo.lesson.grace.springloader;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PropertyPlaceholderHelper;

import javax.validation.Valid;
import java.util.Properties;
import java.util.Set;

/**
 * description
 *
 * @author seal 876651109@qq.com
 * @date 2022/1/9 11:36 下午
 */
@Slf4j
public class SpringLoaderExample {
    public static void main(String[] args) {
//        getForClass();
//        matchResource();
//        pathMatch();
//        getType();
        matchProperty();
    }

    @SneakyThrows
    public static void getForClass() {
        // 和cglib一样基于ASM技术的
        // spring获取类的元数据
        SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = simpleMetadataReaderFactory.getMetadataReader("com.example.demo.lesson.grace.springloader.SpringLoaderExampleTest");
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取类名
        System.out.println(classMetadata.getClassName());
        // 获取子类
        System.out.println(classMetadata.getMemberClassNames()[0]);
        // 获取外部类
        MetadataReader metadataReader2 = simpleMetadataReaderFactory.getMetadataReader("com.example.demo.lesson.grace.springloader.SpringLoaderExampleTest.SpringLoaderExampleTestMember");
        System.out.println(metadataReader2.getClassMetadata().getEnclosingClassName());
        // 获取注解
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        for (String annotationType : annotationMetadata.getAnnotationTypes()) {
            System.out.println(annotationType);
        }
        // 判断注解的注解
        System.out.println(annotationMetadata.hasMetaAnnotation(Component.class.getName()));
        // 判断是否有注解
        System.out.println(annotationMetadata.hasAnnotation(Service.class.getName()));
        // 获取有该注解的方法
        Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(Valid.class.getName());
        System.out.println();
    }

    @SneakyThrows
    public static void matchResource() {
        // spring下匹配文件
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // 获取resource下文件
        Resource[] resources = resourcePatternResolver.getResources("com/example/demo/lesson/grace/springloader/*.class");
        System.out.println(resources);
        // 获取jar包文件
        Resource[] resources2 = resourcePatternResolver.getResources("classpath*:org/springframework/core/io/*.class");
        System.out.println(resources2);
    }

    public static void pathMatch() {
        // springMVC路径判断
        AntPathMatcher matcherDot = new AntPathMatcher(".");
        String pathDot = "com.example.demo.lesson.grace.springloader.SpringLoaderExampleTest";
        // true
        System.out.println("step1");
        System.out.println(matcherDot.match("com.example.demo.lesson.grace.springloader.*", pathDot));
        // false
        System.out.println("step2");
        System.out.println(matcherDot.match("com.example.demo.lesson.grace.springloader2.*", pathDot));
        // true
        System.out.println("step3");
        System.out.println(matcherDot.match("com.example.demo.lesson.grace.springloader.S?????LoaderExampleTest", pathDot));

        AntPathMatcher matcherSep = new AntPathMatcher("/");
        String pathSep = "com/example/demo/lesson/grace/springloader/SpringLoaderExampleTest";
        // true
        System.out.println("step4");
        System.out.println(matcherSep.match("com/example/demo/lesson/grace/**", pathSep));
        System.out.println("step5");
        // true
        System.out.println(matcherSep.match("com/example/demo/lesson/grace/springloader/{sp}", pathSep));

    }

    public static void getType() {
        Class<?> aClass = GenericTypeResolver.resolveTypeArgument(SpringLoaderExampleTest.class, ISpringLoaderExampleTest.class);
        // class java.lang.String
        System.out.println(aClass);
    }

    public static void matchProperty() {
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String text = "user=${user},name=${name}";
        Properties props = new Properties();
        props.setProperty("user", "siri");
        props.setProperty("name", "apple");
        // user=siri,name=apple
        System.out.println(helper.replacePlaceholders(text, props));
    }
}
