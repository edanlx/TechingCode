package com.example.demo.bean;

import com.example.demo.entity.ValidatedRequestVO;
import lombok.extern.slf4j.Slf4j;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 传入方法后的实现逻辑
 *
 * @author seal 876651109@qq.com
 * @date 7/12/2020 7:20 PM
 */
@Slf4j
public class Reflections {
    private Reflections() {
    }

    public static <F, T> String fnToFieldName(IFn<F, T> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);
        String getter = serializedLambda.getImplMethodName();
        String fieldName = "";
        if (getter.startsWith("get")) {
            fieldName = Introspector.decapitalize(getter.replace("get", ""));
        }
        return fieldName;
    }

    public static <F, T> String fnToFnName(IFn<F, T> fn) {
        return getSerializedLambda(fn).getImplMethodName();
    }

    public static <F, T> Method fnToMethod(IFn<F, T> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);
        Method method = null;
        try {
            method = getClazz(serializedLambda).getDeclaredMethod(serializedLambda.getImplMethodName());
        } catch (NoSuchMethodException e) {
            log.error("", e);
        }
        return method;
    }

    public static <F, T> Field fnToField(IFn<F, T> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);
        String fieldName = "";
        String getter = serializedLambda.getImplMethodName();
        if (getter.startsWith("get")) {
            fieldName = Introspector.decapitalize(getter.replace("get", ""));
        }
        Class clazz = getClazz(serializedLambda);
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            log.error("", e);
        }
        return field;
    }

    public static <F, T> String fnToFieldNameVoid(IFnVoid<F, T> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);
        String getter = serializedLambda.getImplMethodName();
        String fieldName = "";
        if (getter.startsWith("set")) {
            fieldName = Introspector.decapitalize(getter.replace("set", ""));
        }
        return fieldName;
    }

    private static SerializedLambda getSerializedLambda(Object fn) {
        SerializedLambda serializedLambda = null;
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            serializedLambda = (SerializedLambda) method.invoke(fn);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("", e);
        }
        return serializedLambda;
    }

    private static Class getClazz(SerializedLambda serializedLambda) {
        Class clazz = null;
        try {
            clazz = Class.forName(serializedLambda.getImplClass().replace("/", "."));
        } catch (ClassNotFoundException e) {
            log.error("", e);
        }
        return clazz;
    }

    public static void main(String[] args) {
        String fieldName = Reflections.fnToFieldName(ValidatedRequestVO::getStr);
        System.out.println("字段名：" + fieldName);
        String fnName = Reflections.fnToFnName(ValidatedRequestVO::getStr);
        System.out.println("方法名：" + fnName);
        String fieldName2 = Reflections.fnToFieldNameVoid(ValidatedRequestVO::setStr);
        System.out.println("字段名：" + fieldName2);
        Method method = Reflections.fnToMethod(ValidatedRequestVO::getStartDate);
        System.out.println("注解：" + Reflections.fnToField(ValidatedRequestVO::getStartDate).getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class).value());
    }
}
