package com.example.demo.lesson.grace.commonpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/4 10:53 PM
 * @description
 */
public class HttpCoonFactory extends BasePooledObjectFactory<HttpClient> {

    @Override
    public HttpClient create() throws Exception {
        // 和线程池一样的设计思路创建对象
        return HttpClients.createDefault();
    }

    @Override
    public PooledObject<HttpClient> wrap(HttpClient httpClient) {
        // 和线程池一样的设计思路，包装对象
        return new DefaultPooledObject<HttpClient>(httpClient);
    }
}
