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
        // 对象头是普通对象头8个字节+4个字节的kclas，共计12个字节
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println("------------------layout------------------");
        // 输出12字节对象头+4字节补齐，共16字节
        System.out.println(layout.toPrintable());
        ClassLayout layout1 = ClassLayout.parseInstance(new Parent());
        System.out.println("------------------layout1------------------");
        // 输出12字节对象头+4字节指针child指向null，共计16字节
        System.out.println(layout1.toPrintable());
        Parent parent = new Parent().setChild(new Child());
        ClassLayout layout2 = ClassLayout.parseInstance(parent);
        System.out.println("------------------layout2------------------");
        // 输出12字节对象头+4字节指针child指向child对象(child对象内存不算入parent中)，共计16字节
        System.out.println(layout2.toPrintable());
        ClassLayout layout3 = ClassLayout.parseInstance(new Child());
        System.out.println("------------------layout3------------------");
        // 输出12字节对象头+4字节list指向null+4字节str指向null+4字节对象补齐，共计24字节
        System.out.println(layout3.toPrintable());
        char c1 = '1';
        ClassLayout layout4 = ClassLayout.parseInstance(c1);
        System.out.println("------------------layout4------------------");
        // 输出12字节对象头+2字节的值(1个char占2个字节)+2字节的对象补齐，共计16字节(这里应该是进行了自动装箱，可以看到和layout10部分是一样的输出)
        System.out.println(layout4.toPrintable());
        char c2 = '你';
        ClassLayout layout5 = ClassLayout.parseInstance(c2);
        System.out.println("------------------layout5------------------");
        // 输出12字节对象头+2字节的值(char无关乎存的是啥)+2字节的对象补齐，共计16字节
        System.out.println(layout5.toPrintable());
        char[] arrC1 = new char[0];
        System.out.println("------------------layout6------------------");
        ClassLayout layout6 = ClassLayout.parseInstance(arrC1);
        // 输出12字节对象头+4字节的计数,共计16字节
        System.out.println(layout6.toPrintable());


        char[] arrC2 = new char[]{'你','好'};
        ClassLayout layout7 = ClassLayout.parseInstance(arrC2);
        System.out.println("------------------layout7------------------");
        // 输出12字节对象头+4字节的计数+4字节的数据+4字节的对象补齐，共计24字节
        System.out.println(layout7.toPrintable());

        char[] arrC3 = new char[]{'你'};
        ClassLayout layout8 = ClassLayout.parseInstance(arrC3);
        System.out.println("------------------layout8------------------");
        // 输出12字节对象头+4字节的计数+2字节的数据+6字节的对象补齐，共计24字节
        System.out.println(layout8.toPrintable());
        String str1 = "1";
        ClassLayout layout9 = ClassLayout.parseInstance(str1);
        System.out.println("------------------layout9------------------");
        // 打开String源码，里面有两个属性 一个 private int hash，一个 private final char value[]
        // 输出12字节对象头+4字节指针指向hash+4字节指针指向value[]+4字节的对象补齐，共计24字节
        System.out.println(layout9.toPrintable());
        Character chr4 = '1';
        ClassLayout layout10 = ClassLayout.parseInstance(chr4);
        System.out.println("------------------layout10------------------");
        // 输出12字节对象头+2字节的值(1个char占2个字节)+2字节的对象补齐，共计16字节
        System.out.println(layout10.toPrintable());
        // 综上，一个长度为n的字符串，总占用即20的基础字节+n×2的字节+对象补齐
        while (true){
            parent.toString();
        }
    }
}
