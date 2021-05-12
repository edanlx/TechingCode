package com.example.demo.util.example.spring;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.beans.ImmutableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.lang.Nullable;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpringExample {
    private String testName;

    private static ApplicationContext ac;

    public static void main(String[] args) {
        // reflectionExample();
        cglibExample();
    }

    @Nullable
    private static void reflectionExample() {
        // 各种反射姿势，可以躲过代码检查
        Method reflectionExample = ReflectionUtils.findMethod(SpringExample.class, "reflectionExample");
        System.out.println(reflectionExample);
        Field testName = ReflectionUtils.findField(SpringExample.class, "testName");
        ReflectionUtils.makeAccessible(testName);
        System.out.println(testName);
        Nullable annotation = AnnotationUtils.findAnnotation(reflectionExample, Nullable.class);
        System.out.println(annotation);
    }

    private static void cglibExample() {
        // 注意cglib是对字节码操作，代理模式就不在这里介绍了，spring aop非常好用了，不过这个是spring带的cglib实际上不是spring的东西

        // 创建不可变bean，简直太好用了，避免缓存被别人瞎改
        SpringExample bean = new SpringExample();
        bean.setTestName("hello");
        SpringExample immutableBean = (SpringExample) ImmutableBean.create(bean);
        // 下面这步会直接报错
        // immutableBean.setTestName("123");

        // 对象复制，目前最快的复制,第一个source,第二个atrget,如果要复制list需要自行循环
        BeanCopier copier = BeanCopier.create(SpringExample.class, SpringExample.class, false);
        SpringExample sourceBean = new SpringExample();
        SpringExample targetBean = new SpringExample();
        sourceBean.setTestName("123");
        targetBean.setTestName("223");
        copier.copy(sourceBean, targetBean, null);
        System.out.println(targetBean);
        // 注意第一步可以static缓存起来，BulkBean虽然可以处理复杂逻辑，但是个人认为复杂逻辑就老实写代码实现，用这个反而累赘

        // 使用转换器实现属性合并，也是相当给力
        BeanCopier copier2 = BeanCopier.create(SpringExample.class, SpringExample.class, true);
        SpringExample sourceBean2 = new SpringExample();
        SpringExample targetBean2 = new SpringExample();
        targetBean2.setTestName("223");
        copier2.copy(sourceBean2, targetBean2, (source, aClass, target) -> ObjectUtils.defaultIfNull(source, target));
        System.out.println(sourceBean2);
        System.out.println(targetBean2);

        // 对象转map，可以重新封装，也可以直接用
        Map<String, Object> map = new HashMap<>();
        map.putAll(BeanMap.create(targetBean));
        Map<String, Object> beanMap = BeanMap.create(targetBean);
        System.out.println(map);
        System.out.println(beanMap);

        // map转对象
        SpringExample springExampleFinal = new SpringExample();
        BeanMap.create(springExampleFinal).putAll(map);
        System.out.println(springExampleFinal);
    }

    private static void springExample() {
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取cookie
        Cookie cookie = WebUtils.getCookie(request, "hello");
        // 转义url
        UriUtils.decode("", StandardCharsets.UTF_8);
        UriUtils.encode("", StandardCharsets.UTF_8);
        // 记录时间戳
        StopWatch sw = new StopWatch("startTest");
        sw.start("step 1");
        sw.stop();
        sw.start("step 2");
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    private void beanExample(){
        // 获取bean
        SpringExample bean = ac.getBean(SpringExample.class);
        // 根据继承或实现获取bean
        Map<String, SpringExample> beansOfType = ac.getBeansOfType(SpringExample.class);
        // 获取当前代理对象，service层常用
        AopContext.currentProxy();
    }

    private void otherExample(){
        // 其下有各种转义，用处有限
        System.out.println(StringEscapeUtils.class);
        // 资源加载工具类，但是不如springBoot注解好用
        System.out.println(ResourceUtils.class);
        // 读取properties，马马虎虎的东西，java自带的也不差
        System.out.println(LocalizedResourceHelper.class);
        // apache的IO包可太好用了,以及很多其它和apache重复的就不介绍了
        System.out.println(FileCopyUtils.class);
    }
}
