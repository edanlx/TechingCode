package com.example.demo.lesson.grace.serialize;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class SerializeExample {
    @Data
    @ToString
    public static class SerializeTestObject implements Serializable{
        private String name;
    }

    public static void main(String[] args) {
        SerializeTestObject serializeTestObject = new SerializeTestObject();
        serializeTestObject.name = "1";
        byte[] serialize = SerializationUtils.serialize(serializeTestObject);
        SerializeTestObject deserialize = SerializationUtils.<SerializeTestObject>deserialize(serialize);
        System.out.println(deserialize);

        byte[] serializeHe = Hessian2Utils.serialize(serializeTestObject);
        SerializeTestObject deserializeHe = Hessian2Utils.<SerializeTestObject>deserialize(serializeHe);
        System.out.println(deserializeHe);

        byte[] serializeHeKryo = KryoUtils.serialization(serializeTestObject);
        SerializeTestObject deserializeKryo = KryoUtils.deserialization(serializeHeKryo, SerializeTestObject.class);
        System.out.println(deserializeKryo);
    }
}
