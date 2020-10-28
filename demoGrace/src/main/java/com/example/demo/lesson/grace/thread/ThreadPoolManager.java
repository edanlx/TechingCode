package com.example.demo.lesson.grace.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * tasks 每秒的任务数，默认200,依据访问量及使用线程池的地方进行计算
 * taskCost：每个任务花费时间，默认0.1s
 * responseTime：最大响应时间，默认为1s，一般用户最大忍受时间为3秒
 *
 * @author seal email:876651109@qq.com
 * @date 2020/5/30 10:08 AM
 */
@Data
@Slf4j
@Configuration
public class ThreadPoolManager {
    /**
     * 平均响应时间默认2秒
     */
    private static final float ALL_COST_AVG = 2F;
    /**
     * 平均IO时间默认1.5秒
     */
    private static final float IO_COST_AVG = 1.5F;
    /**
     * 服务器核数
     */
    private static final int SIZE_PROCESSOR = Runtime.getRuntime().availableProcessors();
    /**
     * https://www.cnblogs.com/dennyzhangdd/p/6909771.html?utm_source=itdadao&utm_medium=referral
     * 阻塞系数=阻塞时间/（阻塞时间+计算时间）
     * 线程数=核心数/(1-阻塞系数)
     * 等同于CPU核心数*cpu使用率*(1+等待时间与计算时间的比率)
     * N+1通常为最优效率
     * <p>
     * https://blog.51cto.com/13527416/2056080
     */
    private static final int SIZE_CORE_POOL = SIZE_PROCESSOR + 1;

    /**
     * 线程池维护最大数量，默认会与核心线程数一致无意义，保守情况取2cpu
     * 或者使用简单计算 线程池大小 = （（线程 IO time + 线程 CPU time ）/线程 CPU time ） CPU数目**
     * 请求所消耗的时间 /(请求所消耗的时间-DB处理)*CPU数目,重点在于cpu等待时间，通常为数据库DB时间
     * 按照通常2秒展示界面，数据库运算1.5秒则(2/0.5)*n,其实就是优化等待时间
     * <p>
     * 默认4n即8核32线程
     */
    private static final int SIZE_MAX_POOL = (int) (SIZE_PROCESSOR * (ALL_COST_AVG / (ALL_COST_AVG - IO_COST_AVG)));
    /**
     * 线程池队列长度，默认为integer最大值,Dubbo使用1000，无限大会引起用户用户的任务一直排队，应选择适当性丢弃，
     * 可忍受时间6其它的则抛弃
     * SIZE_MAX_POOL/IO_COST_AVG=每秒可处理任务数,默认为
     * 可忍受时间6*每秒可处理任务数=X队列数
     */
    private static final int SIZE_QUEUE = (int) (6 * (SIZE_MAX_POOL / IO_COST_AVG));
    /**
     * 线程池具体类
     * LinkedBlockingDeque常用于固定线程，SynchronousQueue常用于cache线程池
     * Executors.newCachedThreadPool()常用于短期任务
     * <p>
     * 线程工厂选择,区别不大
     * 有spring的CustomizableThreadFactory,new CustomizableThreadFactory("springThread-pool-")
     * guava的ThreadFactoryBuilder,new ThreadFactoryBuilder().setNameFormat("retryClient-pool-").build();
     * apache-lang的BasicThreadFactory,new BasicThreadFactory.Builder().namingPattern("basicThreadFactory-").build()
     * <p>
     * 队列满了的策略默认AbortPolicy
     */
    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    private final ThreadPoolExecutor pool = new ThreadPoolExecutor(
            SIZE_CORE_POOL,
            SIZE_MAX_POOL,
            30L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(SIZE_QUEUE),
            new CustomizableThreadFactory("springThread-pool-"),
            new ThreadPoolExecutor.AbortPolicy()
    );

    private void prepare() {
        if (pool.isShutdown() && !pool.prestartCoreThread()) {
            int coreSize = pool.prestartAllCoreThreads();
            System.out.println("当前线程池");
        }
    }


    public static ThreadPoolManager getInstance() {
        if (threadPoolManager != null) {
            ThreadPoolExecutor pool = threadPoolManager.pool;
        }
        return threadPoolManager;
    }
}
