package com.example.demo.lesson.grace.generic;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class genericChild extends genericPerent {

    public Object testMethod5(Object obj) {
        System.out.println("Test3" + obj);
        return (Integer) obj;
    }


    public Object testMethod(String obj) {
        System.out.println("Test3" + obj);
        return obj;
    }


//    public <T> Object testMethod5(Object obj) {
//        System.out.println("Test3" + obj);
//        return obj;
//    }


//    @Override
//    public Integer testMethod2(Object obj) {
//        System.out.println("Test3" + obj);
//        return (Integer) obj;
//    }

    @Override
    public Integer testMethod2(Object obj) {
        System.out.println("Test3" + obj);
        return (Integer) obj;
    }


    @Override
    public Long testMethod3(Object obj) {
        System.out.println("Test3" + obj);
        return (Long) obj;
    }

    public static void testMethodF(List<? extends Integer> obj) {
//        obj.add(1);
        Integer object = obj.get(0);
    }

    public static void testMethodF2(List<? super Integer> obj) {
        obj.add(1);
//        Integer object = obj.get(0);
    }

    public static void main(String[] args) {
        System.out.println(new genericChild().testMethod2(123));
        List list = Stream.of("1", "2", "3", "4", 5).collect(Collectors.toList());
        List<Byte> list2 = list;
        System.out.println(list2);
//        Byte aByte = list2.get(0);
//        System.out.println(aByte);
    }
}
