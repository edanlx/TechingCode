package com.example.demo.lesson.grace.guava;

import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GuavaExample {
    public static void main(String[] args) {
        // immutableOrdinary();
//         effectiveList();
//        newCollections();
//        hash();
//        collections();
        eventBus();
    }

    public static void eventBus() {
        EventBus eventBus = new EventBus("eventBusTest");
        eventBus.register(new eventBusObject());
        eventBus.post(100);
        eventBus.post("我是字符串");
    }

    public static class eventBusObject {
        @Subscribe
        public void listenStr1(String str) {
            System.out.println(str + "listenStr1");
        }

        @Subscribe
        public void listenStr2(String str) {
            System.out.println(str + "listenStr2");
        }

        @Subscribe
        public void listenObj(Object str) {
            System.out.println(str + "listenStr1");
        }

        @Subscribe
        public void listenInt1(Integer str) {
            System.out.println(str + "listenInt1");
        }

        public void listenInt2(Integer str) {
            System.out.println(str + "listenInt2");
        }
    }

    public static void collections() {
        // 个人觉得比较又用的有如下几个方法，前几个可以看做是redis交集、并集的内存实现。后面是数据库笛卡尔积的内存实现
        // 交集、并集、
        Set<Integer> set1 = Stream.of(1, 2, 3, 4).collect(Collectors.toSet());
        Set<Integer> set2 = Stream.of(3, 4, 5, 6).collect(Collectors.toSet());
        // 求set1的差集
        System.out.println(Sets.difference(set1, set2));
        // 求set1和set2差集的并集
        System.out.println(Sets.symmetricDifference(set1, set2));
        // 求交集
        System.out.println(Sets.intersection(set1, set2));
        // 笛卡尔积
        System.out.println(Sets.cartesianProduct(set1, set2));

        // 笛卡尔积
        List<Integer> list1 = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        List<Integer> list2 = Stream.of(2, 3, 4, 5).collect(Collectors.toList());
        System.out.println(Lists.cartesianProduct(list1, list2));
    }

    public static void hash() {
        // 存储格式，大小，误报率(如果判断出来不存在则一定不存在，如果判断出来存在则有可能存在有可能不存在，因为其机制和hash非常相似存在多个值对一个hash的情况)
        // 一般会根据订单号，如果不行的话可以使用byte数组
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 2000, 0.0001);
        IntStream.range(0, 10000).forEach(bloomFilter::put);
        System.out.println(bloomFilter.mightContain(1));
        // 只有-10、-7、-5、-2,而上面用2000个的长度存了一万个数据，有一定的误报率，当然你如果存10000个就会全都检测出来了，但也失去了布隆过滤的意义
        IntStream.range(-10, 0).forEach(s -> {
            if (!bloomFilter.mightContain(s)) {
                System.out.println(s);
            }
        });

    }

    public static void newCollections() {
        // 这里只介绍BiMap和Table。Multiset、Multimap这两个就是嵌了list进去
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "张三");
        biMap.put(2, "李四");
        biMap.put(3, "王五");
        biMap.put(4, "赵六");
        biMap.put(5, "李七");
        biMap.put(6, "小小");
        Integer result = biMap.inverse().get("赵六");
        // 输出结果4
        System.out.println(result);
        // ===========================================================
        // table是个很有意思的数据结构，很有启发性思维，虽然我也不知道这个有啥用
        /*
         *  Company: IBM, Microsoft, TCS
         *  IBM         -> {101:Mahesh, 102:Ramesh, 103:Suresh}
         *  Microsoft     -> {101:Sohan, 102:Mohan, 103:Rohan }
         *  TCS         -> {101:Ram, 102: Shyam, 103: Sunil }
         *
         * */
        //create a table
        Table<String, String, String> employeeTable = HashBasedTable.create();

        //initialize the table with employee details
        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");

        employeeTable.put("Microsoft", "111", "Sohan");
        employeeTable.put("Microsoft", "112", "Mohan");
        employeeTable.put("Microsoft", "113", "Rohan");

        employeeTable.put("TCS", "121", "Ram");
        employeeTable.put("TCS", "102", "Shyam");
        employeeTable.put("TCS", "123", "Sunil");

        //所有行数据
        System.out.println(employeeTable.cellSet());
        //所有公司
        System.out.println(employeeTable.rowKeySet());
        //所有员工编号
        System.out.println(employeeTable.columnKeySet());
        //所有员工名称
        System.out.println(employeeTable.values());
        //公司中的所有员工和员工编号
        System.out.println(employeeTable.rowMap());
        //员工编号对应的公司和员工名称
        System.out.println(employeeTable.columnMap());
    }

    public static void effectiveList() {
        StopWatch sw = new StopWatch();
        // sw.start("listAdd");
        Stream.Builder<Integer> builder1 = Stream.builder();
        for (int i = 0; i < 10000; i++) {
            builder1.add(i);
        }
        List<Integer> list = builder1.build().collect(Collectors.toList());
        // sw.stop();

        // sw.start("ImmutableListAdd");
        ImmutableList.Builder<Integer> builder = ImmutableList.builder();
        for (int i = 0; i < 10000; i++) {
            builder.add(i);
        }
        List<Integer> guava = builder.build();
        // sw.stop();

        List<Integer> listUn = Collections.unmodifiableList(list);
//        sw.start("listGet");
//        list.get(0);
//        sw.stop();
//        sw.start("listUnGet");
//        listUn.get(0);
//        sw.stop();
//        sw.start("guavaGet");
//        guava.get(0);
//        sw.stop();
        sw.start("listFor");
        IntStream.range(0, 10000).boxed().forEach(list::get);
        sw.stop();
        sw.start("listUnFor");
        IntStream.range(0, 10000).boxed().forEach(listUn::get);
        sw.stop();
        sw.start("guavaFor");
        IntStream.range(0, 10000).boxed().forEach(guava::get);
        sw.stop();
        System.out.println(sw.prettyPrint());

        // 常规list转不可变,值得注意的是Collections.unmodifiableList()如果原list被改变不可变是会被改变的
        list.remove(0);
        // 即list和unmodifiableList都会少一个
    }

    public static void immutableOrdinary() {
        // 常规创建
        Set<Integer> set = ImmutableSet.of(1, 2, 3, 1);
        List<Integer> list = ImmutableList.of(1, 2, 3, 1);
        // map的k-v连续的写法还是非常舒服的
        Map<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 1);

        // 循环创建
        ImmutableSet.Builder<Integer> builder = ImmutableSet.<Integer>builder();
        for (int i = 0; i < 10; i++) {
            builder.add(i);
        }
        Set<Integer> build = builder.build();

        List<Integer> collect = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        List<Integer> integers = ImmutableList.copyOf(collect);
    }
}
