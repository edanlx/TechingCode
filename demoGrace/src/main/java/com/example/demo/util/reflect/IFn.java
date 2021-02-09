package com.example.demo.util.reflect;

import java.io.Serializable;

/**
 * F 传入类型，T返回类型,适用于无参get类方法
 * <VO,String>
 * @author seal
 */
@FunctionalInterface
public interface IFn<F, T> extends Serializable {
    T apply(F source);
}
