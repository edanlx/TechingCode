package com.example.demo.lesson.grace.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.stream.IntStream;

public class HashExample {

    public static void main(String[] args) {
        hash();
    }
    public static void hash() {
        // 存储格式，大小，误报率(如果判断出来不存在则一定不存在，如果判断出来存在则有可能存在有可能不存在，因为其机制和hash非常相似存在多个值对一个hash的情况)
        // 一般会根据订单号，如果不行的话可以使用byte数组
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 2000, 0.0001);
        IntStream.range(0, 10000).forEach(bloomFilter::put);
        System.out.println(bloomFilter.mightContain(1));
        // 只有-10、-7、-5、-2,因为这里的预期数据与实际相差比较大，所以布隆过滤并不完全
        IntStream.range(-10, 0).forEach(s -> {
            if (!bloomFilter.mightContain(s)) {
                System.out.println(s);
            }
        });
    }
}
