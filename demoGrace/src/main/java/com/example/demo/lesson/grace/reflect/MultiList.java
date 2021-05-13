package com.example.demo.lesson.grace.reflect;

import com.example.demo.bean.TestMultObject;
import com.example.demo.bean.TestMultObjectChild;
import com.example.demo.bean.TestMultObjectParent;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiList {

    public static class Handle {
        public static void handle(TestMultObjectParent obj1, TestMultObject obj2, TestMultObjectChild obj3,String a,int b) {
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
            add(Reflections.fnToMethod(TestMultObjectParent::getList));
            add(Reflections.fnToMethod(TestMultObject::getList));
        }};
        List<Object> extend = new ArrayList<Object>() {{
            add("1");
            add(1);
        }};
        handleMultiList(data, methodList, Handle.class, new MultiList(),extend);
        System.out.println();
    }

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

    public static List<Integer> handleMultiList(List data, List<Method> methodList, int step, List<List> result) {
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
