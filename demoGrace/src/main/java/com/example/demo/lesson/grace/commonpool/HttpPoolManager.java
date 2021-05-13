package com.example.demo.lesson.grace.commonpool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.http.client.HttpClient;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/4 11:06 PM
 * @description
 */
public class HttpPoolManager extends GenericObjectPool<HttpClient> {
    private static HttpPoolManager httpPoolManager = new HttpPoolManager();

    public static HttpPoolManager getInstance() {
        // 将单例暴露出去
        return httpPoolManager;
    }
    private HttpPoolManager() {
        // 将配置注入到连接池内
        super(new HttpCoonFactory(), new HttpPoolConfig());
    }
}
