package com.example.demo.util.exmaple.apache;

import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class LangExample {
    public static void main(String[] args) throws ParseException {
        objectUtilsExample();// 非常好用的几个方法
        new SerializationUtils();// 序列化算是不错的方法
        new DateFormatUtils();// 日期处理类非常好用
        new DateUtils();// 日期处理类非常好用
        new ObjectUtils();// 最常用的defaultIfNull
        new ArrayUtils();// 对CollectionUtils的补充

        // 以下方法不怎么常用
        new AnnotationUtils();
        new ArchUtils();
        new BooleanUtils();
        new CharSetUtils();
        new CharUtils();
        new ClassUtils();
        new ClassPathUtils();
        new EnumUtils();
        new LocaleUtils();
        new RandomStringUtils();
        new RandomUtils();
        new RegExUtils();
        new ThreadUtils();
        new StringUtils();
        new SystemUtils();
        Serializable a = SerializationUtils.serialize(new String("123"));
        System.out.println(a);
        System.out.println(SerializationUtils.deserialize(SerializationUtils.serialize(new String("123"))).toString());
        System.out.println(NumberUtils.min(1,2));
        System.out.println(DateFormatUtils.format(new Date(),"yyyy/MM/dd"));
        System.out.println(DateUtils.parseDate("2020/05/29", "",""));
    }

    public static void arrayUtilsExample(){
        int[] a = {1, 5, 6, 8};
        // 01.数组转换成字符串
        String string = ArrayUtils.toString(a);
        // 02.在一个数组中查找某个元素是否存在
        System.out.println("intArray contains '8'?" + ArrayUtils.contains(a, 9));
        System.out.println("intArray index of '8'?" + ArrayUtils.indexOf(a, 9));
        System.out.println("intArray last index of '8'?" + ArrayUtils.contains(a, 9));
        // 03.原始类型转换成包装类
        Integer[] object = ArrayUtils.toObject(a);
        System.out.println(object[2]);
    }

    public static void stringUtilsExample(){
        //实现=======的效果，用于打日志
        StringUtils.repeat("=", 50);
        //实现 %%%%%%%%Customised Header%%%%%%%%效果
        String msg = StringUtils.center(" Customised Header ", 50, "%");

        //将一个array中的String连接起来，用分隔符隔开
        StringUtils.join(msg, ",");
        //相反，把用分隔符隔开的string转为数组
        StringUtils.split(msg, ",");

        //加强代码可读性，减少if判断
        StringUtils.defaultString(msg, msg);
        //缩写一个长string，若不足则不干任何事，否则截断并在末尾添加”…”
        StringUtils.abbreviate(msg,5);
    }
    public static void objectUtilsExample(){
        //增强代码可读性，如果obj为null返回defaultObj，这一点在common-lang包中一脉相承
         ObjectUtils.defaultIfNull(null, "");

        //是否相等，等价于obj.equals(obj2)，省略了null判断
         ObjectUtils.equals(null, null);
    }
}
