package com.example.demo.lesson.grace.reflect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumReflects {

    @Getter
    @AllArgsConstructor
    private enum TestEnum {
        China("ZH", "中国"),
        English("EN", "英国");
        private String language;
        private String name;
    }

    /**
     * 将enum->转为List<Map<K,V>>形式输出，舍弃constant
     *
     * @param enumClass
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> List<Map<String, Object>> getListMap(Class<E> enumClass) {
        Field[] declaredFields = enumClass.getDeclaredFields();
        List<Field> listField = Arrays.stream(declaredFields).filter(f -> !Modifier.isStatic(f.getModifiers())).collect(Collectors.toList());
        listField.forEach(l -> l.setAccessible(true));
        List<Map<String, Object>> result = Arrays.stream(enumClass.getEnumConstants())
                .map(en -> listField.stream().collect(Collectors.toMap(Field::getName, field -> ReflectionUtils.getField(field, en))))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 将enum->Map<K,enum>的形式，k以传入的
     *
     * @param enumClass
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>, T> Map<T, E> enumToMap(Class<E> enumClass, Function<E, T> keyFn) {
        return Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toMap(keyFn, (l) -> (l)));
    }

    public static void main(String[] args) {
        System.out.println(getListMap(TestEnum.class));
        System.out.println(enumToMap(TestEnum.class, TestEnum::getLanguage));
    }
}
