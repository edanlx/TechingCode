package com.example.demo.lesson.grace.listspeed;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListSpeed {
    public static void main(String[] args) {
        addCompare();
    }

    public static void addCompare() {
        List<Integer> listArray = IntStream.range(0, 10000).boxed().collect(Collectors.toList());
        // 因为链表数组没有初始大小的所以不创建
        ArrayList<Integer> arraysListHeadNon = new ArrayList<>();
        ArrayList<Integer> arraysListHead = new ArrayList<>(listArray.size());
        ArrayList<Integer> arraysListTailNon = new ArrayList<>();
        ArrayList<Integer> arraysListTailHead = new ArrayList<>(listArray.size());
        LinkedList<Integer> linkedListHeadNon = new LinkedList<>();
        LinkedList<Integer> linkedListTailNon = new LinkedList<>();
        StopWatch sw = new StopWatch();
//        sw.start("arraysListHeadNon");
//        listArray.stream().forEach(s -> arraysListHeadNon.add(0, s));
//        sw.stop();
//        sw.start("arraysListHead");
//        listArray.stream().forEach(s -> arraysListHead.add(0, s));
//        sw.stop();
        sw.start("arraysListTailNon");
        listArray.stream().forEach(s -> arraysListTailNon.add(s));
        sw.stop();
        sw.start("arraysListTailHead");
        listArray.stream().forEach(s -> arraysListTailHead.add(s));
        sw.stop();
        sw.start("linkedListHeadNon");
        listArray.stream().forEach(s -> linkedListHeadNon.addFirst(s));
        sw.stop();
        sw.start("linkedListTailNon");
        listArray.stream().forEach(s -> linkedListTailNon.add(s));
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    public static void foreachCompare() {
        List<Integer> listArray = IntStream.range(0, 10000).boxed().collect(Collectors.toList());
        ArrayList<Integer> arrays = new ArrayList<>(listArray);
        LinkedList<Integer> linked = new LinkedList<>(listArray);
        StopWatch sw = new StopWatch();
        sw.start("arraysStream");
        arrays.forEach(Integer::getClass);
        sw.stop();

        sw.start("linkedStream");
        linked.forEach(Integer::getClass);
        sw.stop();

        sw.start("arraysForEach");
        for (Integer array : arrays) {
            array.getClass();
        }
        sw.stop();
        sw.start("linkedForEach");
        for (Integer array : linked) {
            array.getClass();
        }
        sw.stop();
        sw.start("arraysIterator");
        Iterator<Integer> iteratorArray = arrays.iterator();
        while (iteratorArray.hasNext()) {
            iteratorArray.next().getClass();
        }
        sw.stop();
        sw.start("arraysLinked");
        Iterator<Integer> iteratorLinked = arrays.iterator();
        while (iteratorLinked.hasNext()) {
            iteratorLinked.next().getClass();
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    public static void addAllCompare() {
        List<Integer> listArray = IntStream.range(0, 10000).boxed().collect(Collectors.toList());
        List<Integer> listLinked = new LinkedList<>(listArray);

        StopWatch sw = new StopWatch();
        sw.start("arrayListAddAllArray");
        new ArrayList<>(listArray);
        sw.stop();

        sw.start("linkedListAddAllArray");
        new LinkedList<>(listArray);
        sw.stop();

        sw.start("arrayListAddAllLinked");
        new ArrayList<>(listLinked);
        sw.stop();

        sw.start("linkedListAddAllLinked");
        new LinkedList<>(listLinked);
        sw.stop();

        // 个人认为addAll的情况出现主要源于，ArrayList走的是native的复制所以更快
        System.out.println(sw.prettyPrint());
    }
}
