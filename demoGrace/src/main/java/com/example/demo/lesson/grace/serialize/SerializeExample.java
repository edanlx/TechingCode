package com.example.demo.lesson.grace.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.util.StopWatch;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class SerializeExample {
    @Data
    @ToString
    public static class SerializeTestObject implements Serializable {
        private String name;
    }

    public static void main(String[] args) throws JsonProcessingException {
        // compareLength();
//        compareSerialize();
         compareDeSerialize();
    }

    @SneakyThrows
    private static void compareDeSerialize() {
        SerializeTestObject serializeTestObject = new SerializeTestObject();
        serializeTestObject.name = "1";
        String jsonStr = JSON.toJSONString(serializeTestObject);
        byte[] serialize = SerializationUtils.serialize(serializeTestObject);
        byte[] serializeHe = Hessian2Utils.serialize(serializeTestObject);
        byte[] serializeHeKryo = KryoUtils.serialization(serializeTestObject);
        TypeReference<SerializeTestObject> fastJsonType = new TypeReference<SerializeTestObject>() {
        };
        com.fasterxml.jackson.core.type.TypeReference<SerializeTestObject> jackJsonType = new com.fasterxml.jackson.core.type.TypeReference<SerializeTestObject>() {
        };
        Type gsonType = new TypeToken<SerializeTestObject>() {
        }.getType();
        StopWatch sw = new StopWatch();
        sw.start("serialize");
        for (int i = 0; i < 10000; i++) {
            SerializeTestObject deserialize = SerializationUtils.<SerializeTestObject>deserialize(serialize);
        }
        sw.stop();
        sw.start("He");
        for (int i = 0; i < 10000; i++) {
            SerializeTestObject deserializeHe = Hessian2Utils.<SerializeTestObject>deserialize(serializeHe);
        }
        sw.stop();
        sw.start("Kryo");
        for (int i = 0; i < 10000; i++) {
            SerializeTestObject deserializeKryo = KryoUtils.deserialization(serializeHeKryo, SerializeTestObject.class);
        }
        sw.stop();
        sw.start("fastJson");
        for (int i = 0; i < 10000; i++) {
            SerializeTestObject fastJsonObject = JSON.parseObject(jsonStr, fastJsonType);
        }
        sw.stop();
        sw.start("jackson");
        // 线程安全
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < 10000; i++) {
            SerializeTestObject jacksonObject = objectMapper.readValue(jsonStr, jackJsonType);
        }
        sw.stop();
        // 线程安全
        Gson gson = new Gson();
        sw.start("Gson");
        for (int i = 0; i < 10000; i++) {
            SerializeTestObject gsonObject = gson.fromJson(jsonStr, gsonType);
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    @SneakyThrows
    public static void compareSerialize() {
        SerializeTestObject serializeTestObject = new SerializeTestObject();
        serializeTestObject.name = "1";
        StopWatch sw = new StopWatch();
        sw.start("serialize");
        for (int i = 0; i < 10000; i++) {
            SerializationUtils.serialize(serializeTestObject);
        }
        sw.stop();
        sw.start("He");
        for (int i = 0; i < 10000; i++) {
            Hessian2Utils.serialize(serializeTestObject);
        }
        sw.stop();
        sw.start("Kryo");
        for (int i = 0; i < 10000; i++) {
            KryoUtils.serialization(serializeTestObject);
        }
        sw.stop();
        sw.start("fastJson");
        for (int i = 0; i < 10000; i++) {
            JSON.toJSONString(serializeTestObject);
        }
        sw.stop();
        sw.start("jackson");
        // 线程安全
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < 10000; i++) {
            objectMapper.writeValueAsString(serializeTestObject);
        }
        sw.stop();
        // 线程安全
        Gson gson = new Gson();
        sw.start("Gson");
        for (int i = 0; i < 10000; i++) {
            gson.toJson(serializeTestObject);
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    public static void compareLength() {
        // 输出129，88，4，12,可以看出json格式的优秀，还有Kryo针对java格式的优秀
        SerializeTestObject serializeTestObject = new SerializeTestObject();
        serializeTestObject.name = "1";
        byte[] serialize = SerializationUtils.serialize(serializeTestObject);
        SerializeTestObject deserialize = SerializationUtils.<SerializeTestObject>deserialize(serialize);
        System.out.println(deserialize);
        System.out.println(serialize.length);

        byte[] serializeHe = Hessian2Utils.serialize(serializeTestObject);
        SerializeTestObject deserializeHe = Hessian2Utils.<SerializeTestObject>deserialize(serializeHe);
        System.out.println(deserializeHe);
        System.out.println(serializeHe.length);

        byte[] serializeHeKryo = KryoUtils.serialization(serializeTestObject);
        SerializeTestObject deserializeKryo = KryoUtils.deserialization(serializeHeKryo, SerializeTestObject.class);
        System.out.println(deserializeKryo);
        System.out.println(serializeHeKryo.length);

        System.out.println(JSON.toJSONString(serializeTestObject).getBytes(StandardCharsets.UTF_8).length);
    }
}
