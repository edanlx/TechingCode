package com.example.demo.bean;

import com.example.demo.entity.ValidatedRequestVO;

/**
 * @author seal 876651109@qq.com
 * @description
 * @date 7/12/2020 7:20 PM
 */
public class FnConverter<F, T> {
    /**
     * 传入方法返回字段名
     *
     * @param fn 方法
     * @return 字段名
     * @author seal 876651109@qq.com
     * @date 7/12/2020 7:32 PM
     */
    public String fnToFieldName(IFn<F, T> fn) {
        return Reflections.fnToFieldName(fn);
    }

    /**
     * 传入方法返回方法名
     *
     * @param fn 方法
     * @return 方法名
     * @author seal 876651109@qq.com
     * @date 7/12/2020 7:32 PM
     */
    public String fnToFnName(IFn<F, T> fn) {
        return Reflections.fnToFnName(fn);
    }

    /**
     * 传入方法返回注解
     *
     * @param fn 方法
     * @return mongo注解
     * @author seal 876651109@qq.com
     * @date 7/12/2020 7:32 PM
     */
    public String fnToMongoName(IFn<F, T> fn) {
        return Reflections.fnToMongoName(fn);
    }

    public static void main(String[] args) {
        FnConverter<ValidatedRequestVO, Object> fnConverter = new FnConverter();
        String fieldName = fnConverter.fnToFieldName(ValidatedRequestVO::getStr);
        System.out.println("字段名：" + fieldName);
        String fnName = fnConverter.fnToFnName(ValidatedRequestVO::getStr);
        System.out.println("方法名：" + fnName);

        FnConverter<String, Object> fnConverter2 = new FnConverter();
        String fieldName2 = fnConverter2.fnToFieldName(new ValidatedRequestVO()::setStr);
        System.out.println("字段名：" + fieldName2);
        String fnName2 = fnConverter2.fnToFnName(new ValidatedRequestVO()::setStr);
        System.out.println("方法名：" + fnName2);

        FnConverter<ValidatedRequestVO, Object> fnConverter3 = new FnConverter();
        String fieldName3 = fnConverter3.fnToMongoName(ValidatedRequestVO::getStartDate);
        System.out.println("字段名：" + fieldName3);
    }
}
