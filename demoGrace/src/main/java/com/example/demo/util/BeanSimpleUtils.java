package com.example.demo.util;

import com.example.demo.lesson.grace.serialize.KryoUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.beans.ImmutableBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BeanSimpleUtils {
    private final static Gson GSON_OBJECT = new Gson();

    public static <T> T beanToImmutable(T bean) {
        T result = (T) ImmutableBean.create(bean);
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                ReflectionUtils.makeAccessible(declaredField);
                ReflectionUtils.setField(declaredField, result, ReflectionUtils.getField(declaredField, bean));
            }
        }
        return result;
    }

    public static <T> List<T> beanListToImmutable(List<T> bean) {
        ImmutableList.Builder<T> builder = ImmutableList.builder();
        bean.forEach(s -> builder.add(beanToImmutable(s)));
        return builder.build();
    }

    public static <T> Map<String, T> beanMapToImmutable(Map<String, T> bean) {
        ImmutableMap.Builder<String, T> builder = ImmutableMap.builder();
        bean.forEach((k, v) -> builder.put(k, beanToImmutable(v)));
        return builder.build();
    }

    public static <F, T> List<F> beanToImmutableTree(List<F> source, BiConsumer<F, List<F>> setChildListFn, Function<F, List<F>> getChildListFun, Function<F, T> idFn, Function<F, T> pidFn, Predicate<F> getRootCondition) {
        if (!CollectionUtils.isEmpty(source)) {
            return source;
        }
        List<F> tempTree = TreeUtils.listToTree(source, setChildListFn, idFn, pidFn, getRootCondition);
        if (!CollectionUtils.isEmpty(tempTree)) {
            return tempTree;
        }
        TreeUtils.loopTree(source, getChildListFun, null, s -> {
            if (!CollectionUtils.isEmpty(getChildListFun.apply(s))) {
                setChildListFn.accept(s, beanListToImmutable(getChildListFun.apply(s)));
            }
        });
        return beanListToImmutable(tempTree);
    }

    public static <T, U> U beanCopy(T source, Class<U> clazz) {
        try {
            U target = clazz.newInstance();
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copier.copy(source, target, null);
            return target;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, U> List<U> beanCopyList(List<T> source, Class<U> clazz) {
        return source.stream().map(s -> beanCopy(s, clazz)).collect(Collectors.toList());
    }

    @SneakyThrows
    public static <T, U> U beanMerge(T sourceBean, U targetBean) {
        BeanInfo beanInfo = Introspector.getBeanInfo(targetBean.getClass());
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            if (descriptor.getWriteMethod() != null) {
                Object defaultValue = descriptor.getReadMethod().invoke(sourceBean);
                if (defaultValue != null) {
                    descriptor.getWriteMethod().invoke(targetBean, defaultValue);
                }
            }
        }
        return targetBean;
    }

    public static Map<String, Object> beanToMap(Object targetBean) {
        return BeanMap.create(targetBean);
    }

    public static <T> T mapToBean(Map<String, Object> map, T targetBean) {
        BeanMap.create(targetBean).putAll(map);
        return targetBean;
    }

    /**
     * new TypeToken<SerializeTestObject>() {}.getType();
     *
     * <dependency>
     * <groupId>com.google.code.gson</groupId>
     * <artifactId>gson</artifactId>
     * <version>2.8.6</version>
     * </dependency>
     *
     * @param source
     * @param clazz
     * @author seal 876651109@qq.com
     * @date 2021/7/14 5:17 下午
     */
    public static <T, U> U beanCopyByJson(T source, Class<U> clazz) {
        return GSON_OBJECT.fromJson(GSON_OBJECT.toJson(source), clazz);
    }

    public static <T> T beanCopyByJson(T source) {
        return (T) GSON_OBJECT.fromJson(GSON_OBJECT.toJson(source), source.getClass());
    }

    /**
     * <dependency>
     * <groupId>com.esotericsoftware</groupId>
     * <artifactId>kryo-shaded</artifactId>
     * <version>4.0.2</version>
     * </dependency>
     */
    public static <T extends Serializable> T beanCopyBySerialize(T obj) {
        return (T) KryoUtils.deserialization(KryoUtils.serialization(obj), obj.getClass());
    }

    public static <T extends Serializable> List<T> beanCopyListBySerialize(List<T> list) {
        return list.stream().map(BeanSimpleUtils::beanCopyBySerialize).collect(Collectors.toList());
    }
}
