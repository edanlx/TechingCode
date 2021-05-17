package com.example.demo.lesson.grace.stream;

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
    public static <T> T showLog(int level, Supplier<T> method) {
        // 日志级别等于1的时候执行，实际场景为从redis获取数据为空时执行刷新缓存并返回
        if (level == 1) {
            return method.get();
        } else {
            return null;
        }
    }

    public static void showLog2(int level, Consumer method) {
        // 日志街边等于3的时候输出日志
        if (level == 3) {
            method.accept(level);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 输出
        showLog(1, () -> {
            System.out.println(1);
            return 1;
        });
        // 不输出
        showLog(2, () -> {
            System.out.println(2);
            return 1;
        });
        // 输出
        showLog2(3, System.out::println);
        // 不输出
        showLog2(4, System.out::println);
    }
}
