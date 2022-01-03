package com.example.demo.lesson.grace.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class CacheExample {

    public static void main(String[] args) {
        cache();
    }
    @SneakyThrows
    public static void cache() {
        // 注意两个如果一起用有时候会有bug
        // 访问后x秒后删除
        Cache<Integer, Integer> accessBuild = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.SECONDS).build();
        // 写后x秒后删除
        Cache<Integer, Integer> writeBuild = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build();

        accessBuild.put(1, 1);
        accessBuild.put(2, 2);
        writeBuild.put(1, 1);
        writeBuild.put(2, 2);
        // 输出1
        System.out.println(accessBuild.getIfPresent(1));
        // 输出1
        System.out.println(writeBuild.getIfPresent(1));
        Thread.sleep(500);
        // 输出2
        System.out.println(accessBuild.getIfPresent(2));
        Thread.sleep(600);
        // 输出null
        System.out.println(accessBuild.getIfPresent(1));
        // 输出2
        System.out.println(accessBuild.getIfPresent(2));
        // 输出null
        System.out.println(writeBuild.getIfPresent(1));
    }
}
