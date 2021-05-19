package com.example.demo.lesson.grace.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class KryoUtils {
    // 这个东西线程不安全
    private static final ThreadLocal<Kryo> KRYO = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy());
        return kryo;
    });

    //    newKryoPool() {
//        return new KryoPool.Builder(() -> {
//            final Kryo kryo = new Kryo();
//            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
//                    new StdInstantiatorStrategy()));
//            return kryo;
//        }).softReferences().build();
//    }
    //    https://www.jianshu.com/p/f56c9360936d
    public static <T extends Serializable> byte[] serialization(T obj) {
        KRYO.get().register(obj.getClass());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        // KRYO.get().writeClassAndObject(output, obj);
        KRYO.get().writeObject(output, obj);
        output.close();
        return baos.toByteArray();
    }

    public static <T extends Serializable> T deserialization(byte[] b, Class<T> clazz) {
        KRYO.get().register(clazz);
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        Input input = new Input(bais);
        // return (T) KRYO.get().readClassAndObject(input);
        return (T) KRYO.get().readObject(input, clazz);
    }
}
