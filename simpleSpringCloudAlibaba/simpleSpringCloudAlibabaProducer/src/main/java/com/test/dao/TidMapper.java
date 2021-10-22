package com.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.bean.Tid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TidMapper extends BaseMapper<Tid> {
    Integer executeTestSql();
}
