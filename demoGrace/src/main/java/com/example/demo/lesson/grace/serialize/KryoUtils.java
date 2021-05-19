package com.example.demo.lesson.grace.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.Cleanup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class KryoUtils {
    public static Kryo KRYO = new Kryo();

    //    https://www.jianshu.com/p/f56c9360936d
    public static <T extends Serializable> byte[] serialization(T obj) {
        KRYO.register(obj.getClass());
        try {
            @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
            @Cleanup Output output = new Output(baos);
            KRYO.writeClassAndObject(output, obj);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Serializable> T deserialization(byte[] b, Class<T> clazz) {
        KRYO.register(clazz);
        try {
            @Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(b);
            @Cleanup Input input = new Input(bais);
            return (T) KRYO.readClassAndObject(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
