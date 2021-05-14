package com.example.demo.lesson.grace.front;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * 实现去空格反序列化
 *
 * @author 876651109@qq.com
 * @date 2021/5/14 1:46 下午
 */
public class TrimNotNullDeJson extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return StringUtils.trim(p.getText());
    }
}
