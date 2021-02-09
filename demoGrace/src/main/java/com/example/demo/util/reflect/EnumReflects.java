package com.example.demo.util.reflect;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumReflects {
    public static <E extends Enum<E>> List<Map<String, Object>> getListMap(Class<E> enumClass) {
        Field[] declaredFields = enumClass.getDeclaredFields();
        List<Field> listField = Arrays.stream(declaredFields).filter(f -> !Modifier.isStatic(f.getModifiers())).collect(Collectors.toList());
        listField.forEach(l -> l.setAccessible(true));
        List<Map<String, Object>> result = Arrays.stream(enumClass.getEnumConstants())
                .map(en -> listField.stream().collect(Collectors.toMap(Field::getName, field -> ReflectionUtils.getField(field, en))))
                .collect(Collectors.toList());
        return result;
    }

    public static <E extends Enum<E>> List<Map<String, Object>> getMap(Class<E> enumClass) {

        return null;
    }
}
