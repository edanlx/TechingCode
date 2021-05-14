package com.example.demo.lesson.grace.front;

import enums.ErrorCodeEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToCodeConvert implements Converter<String, ErrorCodeEnum> {
    @Override
    public ErrorCodeEnum convert(String source) {
        return ErrorCodeEnum.MAPS.get(source);
    }
}
