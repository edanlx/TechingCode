package com.example.demo.lesson.grace.front;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 自定义序列化
 *
 * @author 876651109@qq.com
 * @date 2021/5/14 1:46 下午
 */
public class TrimNotNullJson extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(StringUtils.trim(value));
    }
}
