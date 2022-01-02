package com.example.demo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author seal
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 运行时长
     */
    boolean totalConsume() default true;
    /**
     * 请求参数
     */
    boolean parameter() default false;
    /**
     * 返回值
     */
    boolean result() default false;
    /**
     * 异常时打印请求参数
     */
    boolean exception() default true;
}
