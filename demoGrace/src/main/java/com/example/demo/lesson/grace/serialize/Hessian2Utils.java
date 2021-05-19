package com.example.demo.lesson.grace.serialize;

import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import lombok.Cleanup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hessian2Utils {

    /**
     * JavaBean序列化.
     *
     * @param javaBean Java对象.
     * @throws Exception 异常信息.
     */
    public static <T> byte[] serialize(T javaBean) {
        try {
            @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
            @Cleanup Hessian2Output ho = new Hessian2Output(baos);
            ho.writeObject(javaBean);
            ho.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JavaBean反序列化.
     *
     * @param serializeData 序列化数据.
     * @throws Exception 异常信息.
     */
    public static <T> T deserialize(byte[] serializeData) {
        try {
            @Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(serializeData);
            @Cleanup Hessian2Input hi = new Hessian2Input(bais);
            return (T) hi.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
