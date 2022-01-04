package com.example.demo.lesson.grace.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.SneakyThrows;

public class RateExample {
    public static void main(String[] args) {
        rate();
    }
    @SneakyThrows
    public static void rate() {
        // 这里直接设置的就QPS(每秒查询率)
        RateLimiter rateLimiter = RateLimiter.create(1);
        while (true) {
            System.out.println(rateLimiter.tryAcquire());
            Thread.sleep(300);
        }
    }
}
