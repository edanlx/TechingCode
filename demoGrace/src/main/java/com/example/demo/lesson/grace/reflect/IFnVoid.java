package com.example.demo.lesson.grace.reflect;

import java.io.Serializable;

/**
 * F 传入类型，T返回类型,适用于无返回值的set类方法
 * @author seal
 */
@FunctionalInterface
public interface IFnVoid<F,T> extends Serializable {
    void apply(F source,T arg);
}
