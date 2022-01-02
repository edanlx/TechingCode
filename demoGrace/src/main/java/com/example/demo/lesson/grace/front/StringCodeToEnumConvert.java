package com.example.demo.lesson.grace.front;

import enums.ErrorCodeEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * 表单form/data格式或get请求实现Convert
 * 将stringCode直接转成enum
 *
 * @author seal 876651109@qq.com
 * @date 2022/1/2 12:57
 */
public class StringCodeToEnumConvert implements Converter<String, ErrorCodeEnum> {
    @Override
    public ErrorCodeEnum convert(String source) {
        return ErrorCodeEnum.MAPzh.get(source);
    }
}
