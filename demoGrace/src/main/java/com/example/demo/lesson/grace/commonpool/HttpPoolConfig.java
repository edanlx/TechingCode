package com.example.demo.lesson.grace.commonpool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/4 11:11 PM
 * @description
 */
public class HttpPoolConfig extends GenericObjectPoolConfig {

    public HttpPoolConfig() {
        // 这里的配置和其它连接池基本一致，一脉相承的设计思路
        setMinIdle(5);
        setTestOnBorrow(true);
        setMaxTotal(50);
    }
}
