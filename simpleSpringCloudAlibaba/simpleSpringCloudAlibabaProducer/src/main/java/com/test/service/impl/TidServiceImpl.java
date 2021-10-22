package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.Tid;
import com.test.dao.TidMapper;
import com.test.service.TidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TidServiceImpl extends ServiceImpl<TidMapper, Tid> implements TidService {
    @Autowired
    private  TidMapper tidMapper;
    @Override
    public Integer executeTestSql() {
        return tidMapper.executeTestSql();
    }
}
