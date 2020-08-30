package com.example.demo.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Field;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
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

    public static String fnToFieldName(IFn fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
            String getter = serializedLambda.getImplMethodName();
            String fieldName = "";
            if (getter.startsWith("get")) {
                fieldName = Introspector.decapitalize(getter.replace("get", ""));
            } else {
                fieldName = Introspector.decapitalize(getter.replace("set", ""));
            }
            return fieldName;
        } catch (ReflectiveOperationException e) {
            log.warn(String.format("%s:%s",
                    Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage()), e);
        }
        return "";
    }

    public static String fnToFnName(IFn fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
            return serializedLambda.getImplMethodName();
        } catch (ReflectiveOperationException e) {
            log.warn(String.format("%s:%s",
                    Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage()), e);
        }
        return "";
    }

    public static String fnToMongoName(IFn fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
            String getter = serializedLambda.getImplMethodName();
            String fieldName = "";
            if (getter.startsWith("get")) {
                fieldName = Introspector.decapitalize(getter.replace("get", ""));
            } else {
                fieldName = Introspector.decapitalize(getter.replace("set", ""));
            }
            Field field = Class.forName(serializedLambda.getImplClass().replace("/", ".")).getDeclaredField(fieldName).getAnnotation(Field.class);
            return field == null ? fieldName : field.value();
        } catch (ReflectiveOperationException e) {
            log.warn(String.format("%s:%s",
                    Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage()), e);
        }
        return "";
    }
}
