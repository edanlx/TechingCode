package com.example.demo.lesson.greenhand.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {
        mainExample();
    }

    void test() {

    }

    void test(String str) {

    }

    static void staticTest(int i) {

    }

    static void base() {
        Thread thread = new Thread(() -> System.out.println(1));
        // 情景1
        StreamExample streamExample = new StreamExample();
        Stream.of("1", "2", "3", "4").forEach(s -> streamExample.test(s));
        // 情景2
        Stream.of(1, 2, 3, 4).forEach(s -> StreamExample.staticTest(s));
        // 情景3
        Stream.of(new StreamExample(), new StreamExample()).forEach(s -> s.test());
        // 情景4
        Stream.of("1", "2", "3", "4").forEach(s -> new String(s));
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    @ToString(callSuper = true)
    @NoArgsConstructor
    @Builder
    static class StreamTestExample {
        int age;
        String name;
    }

    static void mainExample() {
        List<StreamTestExample> collectStreamTestExample = IntStream.range(0, 6).mapToObj(s -> new StreamTestExample(s, String.valueOf(s))).collect(Collectors.toList());
        System.out.println("filter");
        System.out.println(Stream.of(1, 2, 3, 4, 5).filter(s -> s > 3).count());
        System.out.println(collectStreamTestExample.stream().filter(s -> s.getAge() > 3).collect(Collectors.toList()));
        System.out.println("distinct");
        System.out.println(Stream.of(1, 1, 2).distinct().collect(Collectors.toList()));
        System.out.println("sorted");
        System.out.println(Stream.of(1, 2, 12, 7, 9).sorted().collect(Collectors.toList()));
        System.out.println(Stream.of(1, 2, 12, 7, 9).sorted((a, b) -> b - a).collect(Collectors.toList()));
        System.out.println("skip&limit");
        List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        int pageNum = 2;
        int pageSize = 3;
        System.out.println(list.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));

        // anyMatch 判断集合中的元素是否至少有一个满足某个条件
        System.out.println("anyMatch");
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).anyMatch(s -> s > 3));
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).anyMatch(s -> s > 10));

        // allMatch 判断集合中的元素是否都满足某个条件
        System.out.println("allMatch");
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).allMatch(s -> s > 3));
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).allMatch(s -> s > 0));

        // noneMatch 判断集合中的元素是否都不满足某个条件
        System.out.println("noneMatch");
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).noneMatch(s -> s > 3));
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).noneMatch(s -> s < 0));

        // findAny 快速获取一个通常第一个
        System.out.println("findAny");
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).findAny().get());

        System.out.println("findFirst");
        System.out.println(Stream.of(1, 2, 1, 3, 2, 5).findFirst().get());

        //对numbers中的元素求和 16
        System.out.println(Arrays.asList(1, 2, 1, 3, 3, 2, 4)
                .stream()
                .reduce(0, Integer::sum));
        //求集合中的最大值
        Arrays.asList(1,2,1,3,2,5)
                .stream()
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }
}
