package com.example.demo.lesson.jvm.loader;

public class A {
    public static final String STR_STATIC_FINAL = "strStrStaticFinal";
    public static String STR_STATIC = "strStrStatic";
    public final String strFinal = "strStrFinal";
    public String str = "strStr";
    static {
        System.out.println("*************loadStaticA**************");
    }

    public A() {
        System.out.println("*************loadProtoTypeA**************");
    }

    public void demoFun(){
        String a = "a";
    }

    public static void main(String[] args) {
        new A().demoFun();
    }
}
