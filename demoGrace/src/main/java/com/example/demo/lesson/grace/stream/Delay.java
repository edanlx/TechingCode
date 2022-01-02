package com.example.demo.lesson.grace.stream;

import com.alibaba.fastjson.JSON;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Consumer ：
 * 1.主要适用于没有返回值的,
 * 2.可以在方法内部抽1个小方法，注意该方法只能传1个参数，且一定要简单，复杂的话还是直接抽方法，仅仅是处理代码重复问题
 * Supplier主要适用于有返回值的
 * predicate
 * 1.和consumer类似，可以在方法内抽小方法，也只接受一个参数
 * 2.接受传参方式的判断，可应用于多个复杂判断拆成小单元时再行组合，应用场景少
 * <p>
 * 虽然只接收一个参数但可以是对象
 *
 * @author 876651109@qq.com
 * @date 2021/2/10 9:25 上午
 */
public class Delay {
    /**
     * @param redisKey redisKey
     * @param method   调用数据库方法
     * @param <T>
     * @return
     */
    public static <T> T getFromRedisAndRefresh(String redisKey, Supplier<T> method) {
        // 模拟从redis获取数据
        T redisData = getKey(redisKey);
        if (redisData != null) {
            System.out.println("redis hit");
            return redisData;
        } else {
            System.out.println("redis not hit");
            // 执行查询数据库
            T data = method.get();
            System.out.println("redis refresh");
            return data;
        }
    }

    private static <T> T getKey(String redisKey) {
        if (redisKey.equals("1")) {
            return (T) new Integer(0);
        }
        return null;
    }

    public static void showLog(int level, Consumer method) {
        // 日志级别等于3的时候输出日志同时执行计算，避免了传统的先计算再判断
        if (level >= 4) {
            method.accept(String.format("current level is %s", JSON.toJSONString(level)));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 输出
        int data1 = 1;
        getFromRedisAndRefresh("1", () -> {
            // 从数据库拿到1
            System.out.println("get From database" + data1);
            return data1;
        });
        // 不输出
        int data2 = 2;
        getFromRedisAndRefresh("2", () -> {
            // 从数据库拿到2
            System.out.println("get From database" + data2);
            return data2;
        });


        // 不输出
        showLog(3, System.out::println);
        // 输出
        showLog(4, System.out::println);
    }
}
