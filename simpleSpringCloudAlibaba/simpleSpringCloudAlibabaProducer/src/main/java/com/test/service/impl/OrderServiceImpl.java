package com.test.service.impl;

import com.test.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class OrderServiceImpl implements OrderService {

    @Override
    public String getHello(String str) {
        return "hello dubbo" + str;
    }
}
