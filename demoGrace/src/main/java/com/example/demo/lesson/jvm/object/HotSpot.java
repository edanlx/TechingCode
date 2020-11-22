package com.example.demo.lesson.jvm.object;

import com.example.demo.lesson.grace.optional.Child;
import com.example.demo.lesson.grace.optional.Parent;
import org.openjdk.jol.info.ClassLayout;

/**
* Hotspot 创建对象
* @author seal 876651109@qq.com
* @date 2020/11/21 8:24 PM
*/
public class HotSpot {
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println("------------------layout------------------");
        System.out.println(layout.toPrintable());
        ClassLayout layout1 = ClassLayout.parseInstance(new Parent());
        System.out.println("------------------layout1------------------");
        System.out.println(layout1.toPrintable());
        Parent parent = new Parent().setChild(new Child());
        ClassLayout layout2 = ClassLayout.parseInstance(parent);
        System.out.println("------------------layout2------------------");
        System.out.println(layout2.toPrintable());
        ClassLayout layout3 = ClassLayout.parseInstance(new Child());
        System.out.println("------------------layout3------------------");
        System.out.println(layout3.toPrintable());
        char c1 = '1';
        ClassLayout layout4 = ClassLayout.parseInstance(c1);
        System.out.println("------------------layout4------------------");
        System.out.println(layout4.toPrintable());
        char c2 = '你';
        ClassLayout layout5 = ClassLayout.parseInstance(c2);
        System.out.println("------------------layout5------------------");
        System.out.println(layout5.toPrintable());
        char[] arrC1 = new char[0];
        System.out.println("------------------layout6------------------");
        ClassLayout layout6 = ClassLayout.parseInstance(arrC1);
        System.out.println(layout6.toPrintable());


        char[] arrC2 = new char[]{'你','好'};
        ClassLayout layout7 = ClassLayout.parseInstance(arrC2);
        System.out.println("------------------layout7------------------");
        System.out.println(layout7.toPrintable());

        char[] arrC3 = new char[]{'你'};
        ClassLayout layout8 = ClassLayout.parseInstance(arrC3);
        System.out.println("------------------layout8------------------");
        System.out.println(layout8.toPrintable());
        while (true){
            parent.toString();
        }
    }
}
