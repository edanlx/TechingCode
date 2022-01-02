package com.example.demo.lesson.grace.idea;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Idea implements Serializable {

    public static void main(String[] args) {
//        test1();
        new Thread(() -> test1()).start();
        new Thread(() -> test1()).start();
    }

    static class TestForIdea {
        public String name;
    }


    public static int test1(int a, int b) {
        return 0;
    }

    public static int test1() {
        int z = 0;
        for (int i = 0; i < 10; i++) {
            log.info(String.valueOf(i));
            z = i;
        }
        List<Integer> collect = Stream.of(1, 4, 6).parallel().filter(a -> (a & 1) == 1).map(a -> a + a)
                .collect(Collectors.toList());
        TestForIdea testForIdea = new TestForIdea();
        testForIdea.name = "1";
        testForIdea.name = "2";
        return z;
    }
}
