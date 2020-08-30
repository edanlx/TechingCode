package com.example.demo.bean;

import java.io.Serializable;

/**
 * F 传入类型，T返回类型
 * @author seal
 */
@FunctionalInterface
public interface IFn<F, T> extends Serializable {
    T apply(F source);
}
