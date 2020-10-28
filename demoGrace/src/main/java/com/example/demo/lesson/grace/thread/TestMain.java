package com.example.demo.lesson.grace.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * description
 *
 * @author seal 876651109@qq.com
 * @date 10/28/2020 11:25 PM
 */
@Slf4j
public class TestMain {
    public static void main(String[] args) {
//        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).parallel().map(l -> {
//            System.out.println(l);
//            return l;
//        }).collect(Collectors.toList()));
        threadEx2();
    }

    public static void threadEx1() {
        // 同步
        List<ThreadEntity> listEntity = IntStream.range(0, 1000).mapToObj(x -> new ThreadEntity(x)).collect(Collectors.toList());
        List<CompletableFuture<Integer>> listCompletableFuture = listEntity.stream().map(x -> {
            try {
                return CompletableFuture.supplyAsync(() -> x.countPrice(),
                        ThreadPoolManager.getInstance().getPool());
            } catch (RejectedExecutionException e) {
                System.out.println("reject" + x);
                log.error("", e);
                return null;
            }
        }).collect(Collectors.toList());
        List<Integer> result = listCompletableFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);
    }

    @SneakyThrows(InterruptedException.class)
    public static void threadEx2() {
        // 异步

        List<ThreadEntity> listEntity = IntStream.range(0, 10).mapToObj(x -> new ThreadEntity(x)).collect(Collectors.toList());
        List<CompletableFuture> listCompletableFuture = listEntity.stream().map(x -> {
            try {
                return CompletableFuture.runAsync(() -> x.countPrice(), ThreadPoolManager.getInstance().getPool());
            } catch (RejectedExecutionException e) {
                System.out.println("reject" + x);
                return null;
            }
        }).collect(Collectors.toList());
        listCompletableFuture.stream().map(CompletableFuture::join);
        System.out.println("1234");
        while (true) {
            Thread.sleep(1000);
        }
    }
}
