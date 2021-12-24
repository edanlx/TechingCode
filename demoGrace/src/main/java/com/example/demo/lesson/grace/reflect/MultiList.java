package com.example.demo.lesson.grace.reflect;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiList {

    @Data
    @AllArgsConstructor
    @ToString(callSuper = true)
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class TestMultObjectParent {
        private List<TestMultObject> list;
    }

    @Data
    @AllArgsConstructor
    @ToString(callSuper = true)
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class TestMultObject {
        private List<TestMultObjectChild> list;
    }

    @Data
    @AllArgsConstructor
    @ToString(callSuper = true)
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class TestMultObjectChild {
        private int id;
    }

    public static class Handle {
        /**
         * 按照树结构和传参要求自行创建
         */
        public static void handle(TestMultObjectParent obj1, TestMultObject obj2, TestMultObjectChild obj3, String a, int b) {
            System.out.println(obj3);
        }
    }


    public static void main(String[] args) {
        List<TestMultObjectParent> data = new ArrayList<TestMultObjectParent>() {{
            add(TestMultObjectParent.builder().list(new ArrayList<TestMultObject>() {{
                add(TestMultObject.builder().list(new ArrayList<TestMultObjectChild>() {{
                    add(new TestMultObjectChild(1));
                    add(new TestMultObjectChild(2));
                }}).build());
                add(TestMultObject.builder().list(new ArrayList<TestMultObjectChild>() {{
                    add(new TestMultObjectChild(4));
                    add(new TestMultObjectChild(5));
                }}).build());
                add(TestMultObject.builder().list(new ArrayList<TestMultObjectChild>() {{
                    add(new TestMultObjectChild(7));
                    add(new TestMultObjectChild(8));
                }}).build());
                add(null);
            }}).build());
            add(TestMultObjectParent.builder().list(new ArrayList<TestMultObject>() {{
                add(TestMultObject.builder().list(new ArrayList<TestMultObjectChild>() {{
                    add(new TestMultObjectChild(11));
                    add(new TestMultObjectChild(12));
                }}).build());
                add(TestMultObject.builder().list(new ArrayList<TestMultObjectChild>() {{
                    add(new TestMultObjectChild(14));
                    add(new TestMultObjectChild(15));
                }}).build());
                add(TestMultObject.builder().list(new ArrayList<TestMultObjectChild>() {{
                    add(new TestMultObjectChild(17));
                    add(new TestMultObjectChild(18));
                }}).build());
            }}).build());
        }};
        List<Method> methodList = new ArrayList<Method>() {{
            // 这个是18节介绍过自己封装的工具类，方法返回method对象
            add(Reflections.fnToMethod(TestMultObjectParent::getList));
            add(Reflections.fnToMethod(TestMultObject::getList));
        }};
        List<Object> extend = new ArrayList<Object>() {{
            add("1");
            add(1);
        }};
        handleMultiList(data, methodList, Handle.class, null, extend);
    }

    /**
     * @param data       数据
     * @param methodList 按顺序封装每一层获取下一层子集的方式
     * @param clazz      最终回调的类需要按固定格式写唯一一个方法用于回调，前N个参数分别是每一层的对象，最后传入extend扩展对象
     * @param obj        反射方法执行对象，用不上但需要传
     * @param extend     需要参与底层调用的参数,可以将需要的返回值传入等等操作
     */
    public static void handleMultiList(List data, List<Method> methodList, Class clazz, Object obj, List extend) {
        Method invoke = clazz.getMethods()[0];
        List<List> objects = new ArrayList<>();
        handleMultiList(data, methodList, 0, objects);
        for (List object : objects) {
            object.addAll(extend);
            ReflectionUtils.invokeMethod(invoke, obj, object.toArray());
            object.clear();
        }
    }

    private static List<Integer> handleMultiList(List data, List<Method> methodList, int step, List<List> result) {
        List<Integer> intList = new ArrayList<>();
        if (CollectionUtils.isEmpty(data)) {
            return intList;
        }
        data.forEach(l -> {
            if (l == null) {
                return;
            }
            if (step < methodList.size()) {
                List newData = (List) ReflectionUtils.invokeMethod(methodList.get(step), l);
                List<Integer> integers = handleMultiList(newData, methodList, step + 1, result);
                for (Integer integer : integers) {
                    result.get(integer).add(0, l);
                }
                intList.addAll(integers);
            } else {
                result.add(Stream.of(l).collect(Collectors.toList()));
                intList.add(result.size() - 1);
            }
        });
        return intList;
    }
}
