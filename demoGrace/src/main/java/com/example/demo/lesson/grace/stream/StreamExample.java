package com.example.demo.lesson.grace.stream;

import com.example.demo.bean.TestEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        ordinaryUsed();
    }

    public static void ordinaryUsed() {
        // 并行流，会乱序
        System.out.println("Step1");
        Stream.of(1, 2, 3, 4).parallel().forEach(System.out::print);
        System.out.println();
        System.out.println("Step2");
        // 并行流+收集保证不会乱序
        Stream.of(1, 2, 3, 4).parallel().collect(Collectors.toList()).forEach(System.out::print);
        System.out.println();
        System.out.println("Step3");
        // 获取唯一数据
        System.out.println(Stream.of(1, 2, 3, 4).filter(s -> s.equals(1)).findAny().get());
        System.out.println("Step4");
        // 条件过滤获取新数据-list/set
        System.out.println(Stream.of(1, 2, 3, 4).filter(s -> s > 3).collect(Collectors.toList()));
        // 条件过滤获取新数据-map
        System.out.println("Step5");
        System.out.println(new HashMap<Integer, Integer>() {{
            put(1, 1);
            put(2, 2);
            put(3, 3);
            put(4, 4);
        }}.entrySet().stream().filter(s -> s.getKey() > 3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        System.out.println("Step6");
        // list做物理分页
        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        int pageNum = 2;
        int pageSize = 3;
        System.out.println(list.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));
        // 循环创建连续数字list
        System.out.println("Step7");
        System.out.println(IntStream.range(0, 10).boxed().collect(Collectors.toList()));
        // list循环获取索引下标
        System.out.println("Step8");
        IntStream.range(0, list.size()).forEach(x -> System.out.print(list.get(x)));
        System.out.println();
        // list对象-转map，注意key不能相同，否则会报错
        System.out.println("Step9");
        System.out.println(Stream.of(TestEnum.values()).collect(Collectors.toMap(TestEnum::getCode, s -> s)));
        System.out.println("Step10");
        // list对象-转map嵌list,根据选择的属性进行合并
        System.out.println(Arrays.stream(TestEnum.values()).collect(Collectors.groupingBy(TestEnum::getDesc)));
    }

    public static void predicate() {
        Predicate<Integer> predicate = x -> x > 7;
        System.out.println(predicate.test(10)); //输出 true
        System.out.println(predicate.test(6));  //输出 fasle
        /**
         * 2、大于7并且
         */
        //在上面大于7的条件下，添加是偶数的条件
        predicate = predicate.and(x -> x % 2 == 0);
        System.out.println(predicate.test(6));  //输出 fasle
        System.out.println(predicate.test(12)); //输出 true
        System.out.println(predicate.test(13)); //输出 fasle
        /**
         * 3、add or 简化写法
         */
        predicate = x -> x > 5 && x < 9;
        System.out.println(predicate.test(10)); //输出 false
        System.out.println(predicate.test(6));  //输出 true
    }

    public static void reduce() {
        // 从40开始，迭代+2，共20个
        List<Integer> list3 = Stream.iterate(40, n -> n + 2).limit(20).collect(Collectors.toList());
        System.out.println(list3);
        // 从10的基础上全部相加
        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    return a + b;
                });
        System.out.println(reducedParams);
    }
}
